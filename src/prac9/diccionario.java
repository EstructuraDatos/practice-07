package prac9;

/**
 *
 * @author dorian
 */
public interface diccionario {
    /**
     * Inserta el elemento
     * @param elem 
     */
    public void insertar(Telemento elem);
    /**
     * Borra el elemento
     * @param elem 
     */
    public void borrar(Telemento elem);
    /**
     * Busca una palabra y retorna el elemento
     * @param palabra
     * @return 
     */
    public Telemento buscar(String palabra);
    /**
     * función hash que se usará para guardar los elementos
     * @param palabra
     * @return 
     */
    public int hash(String palabra);
}
