package prac9;


import prac9.ElemRepetidoException;
import prac9.Telemento;
import prac9.DiccionarioHashCerrado;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;

import org.junit.Test;




public class TestHashCerrado{

	DiccionarioHashCerrado d=null;
	
	@Before
	public void setUp() {
            d=new DiccionarioHashCerrado();
            Telemento elem=null;
            for (int i=1; i<14; i++){
                    elem=new Telemento("pal"+i,"significa "+i);
                    d.insertar(elem);
            }
	}
		
	@Test
	public void testInsertar() {
            assertTrue(d.getNElem()==13);
	  assertTrue(d.getTamanoTabla()==17);
	  Telemento elem=new Telemento("pal14","significa 14");
	  d.insertar(elem);
	  // al insertar el 14 expande capacidad
	  assertTrue(d.getNElem()==14);
	  assertTrue(d.getTamanoTabla()==37);
	  for (int i=15; i<30; i++){
		  elem=new Telemento("pal"+i,"significa "+i);
		  d.insertar(elem);
	  }
	 
      assertTrue(d.getNElem()==29);
	  assertTrue(d.getTamanoTabla()==37);
	
	  // al insertar el 30 expande capacidad
	  elem=new Telemento("pal30","significa 30");
	  d.insertar(elem);
	  assertTrue(d.getNElem()==30);
	  assertTrue(d.getTamanoTabla()==79);
	  
	  for (int i=31; i<64; i++){
		  elem=new Telemento("pal"+i,"significa "+i);
		  d.insertar(elem);
	  }
	  assertTrue(d.getNElem()==63);
	  assertTrue(d.getTamanoTabla()==79);
	  
	  // al insertar el 64 expande capacidad
	  elem=new Telemento("pal64","significa 64");
	  d.insertar(elem);
	  assertTrue(d.getNElem()==64);
	  assertTrue(d.getTamanoTabla()==163);
	 
	  for (int i=65; i<131; i++){
		  elem=new Telemento("pal"+i,"significa "+i);
		  d.insertar(elem);
	  }
	  assertTrue(d.getNElem()==130);
	  assertTrue(d.getTamanoTabla()==163);
	  
	  // al insertar el 131 expande capacidad
	  elem=new Telemento("pal131","significa 131");
	  d.insertar(elem);
	  assertTrue(d.getNElem()==131);
	  assertTrue(d.getTamanoTabla()==331);
	  
	  for (int i=132; i<265; i++){
		  elem=new Telemento("pal"+i,"significa "+i);
		  d.insertar(elem);
	  }
	  assertTrue(d.getNElem()==264);
	  assertTrue(d.getTamanoTabla()==331);
	  
	  // al insertar el 265 expande capacidad
	  elem=new Telemento("pal265","significa 265");
	  d.insertar(elem);
	  assertTrue(d.getNElem()==265);
	  assertTrue(d.getTamanoTabla()==673);
	
	  for (int i=266; i<539; i++){
		  elem=new Telemento("pal"+i,"significa "+i);
		  d.insertar(elem);
	  }
	  assertTrue(d.getNElem()==538);
	  assertTrue(d.getTamanoTabla()==673);
	  
	  // al insertar el 539 expande capacidad
	  elem=new Telemento("pal539","significa 539");
	  d.insertar(elem);
	  assertTrue(d.getNElem()==539);
	  assertTrue(d.getTamanoTabla()==1361);
	 
	  for (int i=540; i<1089; i++){
		  elem=new Telemento("pal"+i,"significa "+i);
		  d.insertar(elem);
	  }
	  assertTrue(d.getNElem()==1088);
	  assertTrue(d.getTamanoTabla()==1361);
	  
	  // al insertar el 1089 expande capacidad
	  elem=new Telemento("pal1089","significa 1089");
	  d.insertar(elem);
	  assertTrue(d.getNElem()==1089);
	  assertTrue(d.getTamanoTabla()==2729);
	 
	}
	
	@Test(expected=ElemRepetidoException.class)
	public void testInsertarElemRep() {

	  assertTrue(d.getNElem()==13);
	  Telemento elem=new Telemento("pal5","significa 5");
	  d.insertar(elem);
	}
        
        @Test
        public void testBorrarInsertar(){
            DiccionarioHashCerrado dic=new DiccionarioHashCerrado();
            //creo dos elementos con el mismo hash para un array de 17 celdas
            Telemento elem = new Telemento("pal11", "significa 11");
            Telemento elem2 = new Telemento("pal113", "probando");
            
            //inserto el primero
            dic.insertar(elem);
            //se le ve en una posición
            assertEquals("0=>\n" +
                            "1=>\n" +
                            "2=>\n" +
                            "3=>\n" +
                            "4=>\n" +
                            "5=>\n" +
                            "6=>\n" +
                            "7=> pal11: significa 11\n" +
                            "8=>\n" +
                            "9=>\n" +
                            "10=>\n" +
                            "11=>\n" +
                            "12=>\n" +
                            "13=>\n" +
                            "14=>\n" +
                            "15=>\n" +
                            "16=>\n", dic.toString());
            //lo borro
            dic.borrar(elem);
            //inserto el otro
            dic.insertar(elem2);
            //veo que pilla su misma posición (mismo hash)
            assertEquals("0=>\n" +
                        "1=>\n" +
                        "2=>\n" +
                        "3=>\n" +
                        "4=>\n" +
                        "5=>\n" +
                        "6=>\n" +
                        "7=> pal113: probando\n" +
                        "8=>\n" +
                        "9=>\n" +
                        "10=>\n" +
                        "11=>\n" +
                        "12=>\n" +
                        "13=>\n" +
                        "14=>\n" +
                        "15=>\n" +
                        "16=>\n", dic.toString());
            //introduzco la otra palabra
            dic.insertar(elem);
            //vemos las dos introducidas
            assertEquals("0=>\n" +
                        "1=>\n" +
                        "2=>\n" +
                        "3=>\n" +
                        "4=>\n" +
                        "5=>\n" +
                        "6=>\n" +
                        "7=> pal113: probando\n" +
                        "8=> pal11: significa 11\n" +
                        "9=>\n" +
                        "10=>\n" +
                        "11=>\n" +
                        "12=>\n" +
                        "13=>\n" +
                        "14=>\n" +
                        "15=>\n" +
                        "16=>\n", dic.toString());
            //borro el primero
            dic.borrar(elem2);
            //busco el segundo, mirará en la primera posición y deberá ir a mirar el siguiente
            Telemento el=dic.buscar("pal11");
            assertEquals(el, elem);
        }

	@Test
	public void testBorrar() {
            Telemento elem = new Telemento("pal11", "significa 11");
            assertTrue(d.getNElem() == 13);
            d.borrar(elem);
            assertTrue(d.getNElem()==12);	    
	  
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testBorrarConPrueba() {
            Telemento elem = new Telemento("pal11", "significa 11");
            assertTrue(d.getNElem() == 13);
            d.borrar(elem);
            assertTrue(d.getNElem() == 12);
            
            // Forzamos excepcion pidiendo que elimine el elem que ya elimino
            d.borrar(elem);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testBorrarNOEXISTE() {

	Telemento elem=new Telemento("pal500","significa 500");
	
	d.borrar(elem);
	//SALTA LA EXCEPCION PORQUE NO ESTA EL ELEMENTO
	assertTrue(elem!=null);
	}
	
	@Test()
	public void testBorrarTodos() {
		Telemento elem=null;
		for (int i=13; i>0; i--){
			elem=new Telemento("pal"+i,"significa "+i);
			d.borrar(elem);
		}
   
		
	  System.out.println(d.getNElem());
	  assertTrue(d.getNElem()==0);
	}


	@Test
	public void testBuscar() {
		Telemento elem=null;
		assertTrue(d.getNElem()==13);
		for (int i=1; i<13; i++){
		    elem=d.buscar("pal"+i);
                    assertTrue(elem.getPalabra().equals("pal"+i));
		}
		 
	}


        @Test
        public void testBuscarTablaGrande() {
            Telemento elem;
            Telemento elem2;

            for (int i=14; i<1089; i++){
                      elem=new Telemento("pal"+i,"significa "+i);
                      d.insertar(elem);
              }
              assertTrue(d.getNElem()==1088);
              assertTrue(d.getTamanoTabla()==1361);
            for (int i=1; i>1089; i++){
                    elem=new Telemento("pal"+i,"significa "+i);

                elem2=d.buscar(elem.getPalabra());
                assertTrue(elem2.compareTo(elem)==0);
            }
        }

        @Test(expected=NoSuchElementException.class)
        public void testBuscarNoEncon() {
                Telemento elem=null;
                for (int i=14; i<50; i++){
                        elem=new Telemento("pal"+i,"significa "+i);
                        d.insertar(elem);
                }
                assertTrue(d.getNElem()==49);
                elem=new Telemento("pal100","significa 100");

                elem=d.buscar("pal99");
                assertTrue(elem.getPalabra().equals("pal9"));
        }

        @Test
        public void testInsertWithCollision() {

                DiccionarioHashCerrado D1 = new DiccionarioHashCerrado();
                Telemento elem= null;

                D1.insertar(new Telemento("55555","n/a"));
                D1.insertar(new Telemento("55564","n/a"));
                D1.insertar(new Telemento("55654","n/a"));
                D1.insertar(new Telemento("56554","n/a"));
                D1.insertar(new Telemento("65554","n/a"));
                D1.insertar(new Telemento("55573","n/a"));
                D1.insertar(new Telemento("55753","n/a"));
                D1.insertar(new Telemento("57553","n/a"));
                D1.insertar(new Telemento("75553","n/a"));
                assertTrue(D1.getNElem()==9);
                elem=D1.buscar("75553");
                assertTrue(elem.getPalabra().compareTo("75553")==0);

        }

}
