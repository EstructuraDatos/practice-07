package prac9;

import java.util.NoSuchElementException;

/**
 *
 * @author dorian
 */
public class DiccionarioHashCerrado implements diccionario{
    /**
     * Factor de sobrecarga
     */
    private static float sobrecarga=80;
    
    /**
     * Numero de elementos
     */
    private int nelemen;
    /**
     * Tabla
     */
    private TelemHashC tabla[];
    /**
     * Tamaño de la tabla
     */
    private int tamanoTabla;
    /**
     * Tamaño inicial predeterminado de la tabla
     */
    private static int tamanoPredeterminado = 17;

    
    /**
     * Constructor predeterminado
     */
    public DiccionarioHashCerrado() {
        tamanoTabla=tamanoPredeterminado;
        tabla=new TelemHashC[tamanoTabla];
        nelemen=0;
    }
    
    /**
     * Contructor al que se le especifica un tamaño de tabla
     * @param tamanoTabla 
     */
    public DiccionarioHashCerrado(int tamanoTabla) {
        this.tamanoTabla = tamanoTabla;
        this.tabla = new TelemHashC[tamanoTabla];
        this.nelemen = 0;
    }
    
    /**
     * Devuelve el número de elementos
     * @return número de elementos de la tabla
     */
    public int getNElem() {
        return nelemen;
    }

    /**
     * Devuelve el tamaño actual de la tabla
     * @return Tamaño de la tabla
     */
    public int getTamanoTabla() {
        return tamanoTabla;
    }
    
    /**
     * Redimensiona la tabla
     */
    private void redimensionado(){
        //obtengo el nuevo tamaño que ha de tener
        int nuevoTamano=tamanoRedimensionado();
        //guardo dicho tamaño
        tamanoTabla=nuevoTamano;
        //guardo la tabla actual en una variable auxiliar
        TelemHashC viejaTabla[]=tabla;
        //creo la nueva tabla en la variable global
        tabla=new TelemHashC[nuevoTamano];
        //reseteo el número de elementos
        nelemen = 0;
        
        //reintroduzco de nuevo los elementos que había en la tabla
        for (int i = 0; i < viejaTabla.length; i++) {
            //saco el elemento de la vieja tabla
            TelemHashC telemHashC = viejaTabla[i];
            //si hay un elemento...
            if(telemHashC!=null){
                //y no está borrado...
                if(!telemHashC.isBorrado()){
                    //añado el elemento a la nueva tabla
                    this.insertar(telemHashC.getDato());
                }
            }
        }
    }
    
    /**
     * Comprueba si la tabla se sobrecargaría si se añadiera un nuevo elemento
     * @return true si sobrecargado, false al contrario
     */
    private boolean isOverload(){
        //sumo uno al número de elementos y lo divido por el tamaño de la tabla
        float division=((float)nelemen+1)/(float)tamanoTabla;
        //ajusto para tener un porcentaje
        division=division*100;
        //devuelvo si supera la sobrecarga o lo iguala
        return division>=sobrecarga;
    }
    
    /**
     * Calcula el nuevo tamaño que debe tener la tabla redimensionada, que es
     * el primer primo del doble del tamaño actual mas uno
     * @return el nuevo tamaño
     */
    private int tamanoRedimensionado(){
        //doblo el tamaño
        int tamanoMinimo=tamanoTabla*2;
        boolean found=false;
        boolean isPrimo;
        //el primo debe ser mayor del doble
        int primo=tamanoMinimo+1;
        
        //un while para probar números primos
        while(found==false){
            //establezco que es primo
            isPrimo=true;
            int i;
            //pruebo con todos los números anterior al supuesto primo
            for(i=primo-1; i>1 && isPrimo; i--){
                //si el primo es divisible por alguno...
                if(primo%i==0){
                    //ya no es primo
                    isPrimo=false;
                }
            }
            
            //si al terminar el for, el índice es 1, significa que ya dividió con
            //todos sin que isPrimo se pusiera a false y saliera
            if(i==1){
                //establezco que lo encontré
                found=true;
            }else{
                //pruebo el siguiente número a ver si es primo
                primo++;
            }
        }
        
        return primo;
    }
    
    /**
     * Aplico la fórmula de colisiones (prueba cuadrática)
     * @param indice indice del hash
     * @param colisiones colisiones que sufrió
     * @return el siguiente índice según la fórmula
     */
    private int colision(int indice, int colisiones){
        //hago la división truncada
        int truncatedDivision=(colisiones+1)/2;
        //aplico la fórmula cuadrática
        int index=(int)(indice + Math.pow(-1, colisiones-1)*(Math.pow(truncatedDivision, 2)));
        //si el número me sale negativo, sumo el tamaño de la tabla varias veces para quedar
        //positivo y aplicar el módulo sin que haya cambios del índice que le corresponde
        while(index<0){
            index=index+tamanoTabla;
        }
        //devuelvo el índice
        return index%tamanoTabla;
    }

    /**
     * 
     * @see diccionario#insertar(prac9.Telemento) 
     */
    @Override
    public void insertar(Telemento elem) {
        //creo el nuevo elemento a meter
        TelemHashC newElem=new TelemHashC(elem);
        //compruebo que no vaya a ser sobrecargado o lo redimensiono
        if(isOverload()){
            redimensionado();
        }
        //contador de colisiones
        int numberColisions=0;
        //indice que le corresponde por el hash
        int firstIndex=hash(elem.getPalabra());
        //indice donde quedará guardado
        int index=firstIndex;
        //variable para controlar si se inserta o no
        boolean inserted=false;
        
        //busco si ya está introducida la palabra
        int indice=this.indexBuscar(elem.getPalabra());
        if(indice!=-1){
            throw new ElemRepetidoException("Elemento ya introducido");
        }
        
        //mientras no se haya insertado...
        while(!inserted){
            //si hay hueco en el índice, lo guardo
            if(tabla[index]==null){
                tabla[index]=newElem;
                inserted=true;
                
            //si no hay hueco, pero es un elemento borrado, lo incluyo
            }else if(tabla[index].isBorrado()){
                tabla[index]=newElem;
                inserted=true;
                
            //si no hay hueco y no está borrado...
            }else{
                //incremento las colisiones
                numberColisions++;
                //hallo el nuevo índice con la prueba cuadrática
                index=colision(firstIndex, numberColisions);
            }
            
            //si el elemento no está insertado, y el índice obtenido después
            //de colisionar es el mismo del principio...
            if(!inserted && index==firstIndex && numberColisions>=1){
                //redimensiono
                redimensionado();
                //reinicio el contador de colisiones
                numberColisions=0;
                //recalculo el índice y modifico el índice inicial
                index=colision(firstIndex, numberColisions);
                firstIndex=index;
            }
        }
        //incremento el número de elementos
        nelemen++;
    }

    /**
     * 
     * @see diccionario#borrar(prac9.Telemento) 
     */
    @Override
    public void borrar(Telemento elem) {
        //busco el índice del elemento buscado
        int indice=this.indexBuscar(elem.getPalabra());
        //si no existe, salto excepción
        if(indice==-1){
            throw new NoSuchElementException();
        }
        //establezco el elemento como borrado
        tabla[this.indexBuscar(elem.getPalabra())].setBorrado(true);
        //decremento el número de elementos
        nelemen--;
    }
    
    /**
     * Obtengo el índice de la palabra que busco
     * @param palabra la palabra que busco
     * @return el índice de la palabra o -1 si no lo encuentro
     */
    private int indexBuscar(String palabra){
        //contador de colisiones e indice donde estará el elemento
        int colisiones=0, index;
        //obtengo el índice obtenido por el hash
        int firstIndex=hash(palabra);
        int found=-1;
        boolean finish=false;
        
        //mientras no se haya encontrado o finalizado por otra causa...
        while(found==-1 && finish==false){
            //obtengo el índice donde estará
            index=colision(firstIndex, colisiones);
            //si el hueco está vacío o el índice coincide con el primero después 
            //de colisionar, he terminado de buscar
            if(tabla[index]==null  || (index==firstIndex && colisiones>0)){
                finish=true;
                
            }else{
                //si hay algo y es la palabra buscada sin que esté borrado, establezco el índice
                if(tabla[index].getDato().getPalabra().compareTo(palabra)==0 && !tabla[index].isBorrado()){
                    found=index;
                    
                //si no, incremento el número de colisiones
                }else{
                    colisiones++;
                }
            }
        }
        //devuelvo los resultados
        return found;
    }

    /**
     * 
     * @see diccionario#buscar(java.lang.String)  
     */
    @Override
    public Telemento buscar(String palabra) {
        //busco el índice
        int indice=this.indexBuscar(palabra);
        //si no existe lanzo excepción
        if(indice==-1){
            throw new NoSuchElementException();
        }
        //devuelvo el elemento
        return tabla[indice].getDato();
    }

    /**
     * 
     * @see diccionario#hash(java.lang.String) 
     */
    @Override
    public int hash(String palabra) {
        int suma=0;
        //sumo todos los caracteres
        for (int i = 0; i < palabra.length(); i++) {
            suma+=palabra.charAt(i);
        }
        //aplico el módulo
        return suma%tamanoTabla;
    }

    /**
     * 
     * @see Object#toString() 
     */
    @Override
    public String toString() {
        StringBuilder string=new StringBuilder();
        
        for (int i = 0; i < tabla.length; i++) {
            //recojo el elemento
            TelemHashC telemHashC = tabla[i];
            //si no está vacío...
            if(telemHashC!=null){
                //y no está borrado...
                if(!telemHashC.isBorrado()){
                    //imprimo los datos
                    string.append(i + "=> " + telemHashC.getDato().toString() + "\n");
                }
            }else{
                //si no, dejo sólo el índice
                string.append(i + "=>\n");
            }
        }
        
        //devuelvo el resultado
        return string.toString();
    }
    
}
