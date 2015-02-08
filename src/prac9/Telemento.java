package prac9;

/**
 *
 * @author dorian
 */
public class Telemento {
    /**
     * palabra
     */
    private String palabra;
    /**
     * significado de la palabra
     */
    private String significado;

    /**
     * COnstructor
     * @param palabra
     * @param significado 
     */
    public Telemento(String palabra, String significado) {
        this.palabra = palabra;
        this.significado = significado;
    }

    /**
     * Devuelve la palabra
     * @return 
     */
    public String getPalabra() {
        return palabra;
    }

    /**
     * Devuelve el significado
     * @return 
     */
    public String getSignificado() {
        return significado;
    }

    @Override
    public String toString() {
        return palabra + ": " + significado;
    }

    /**
     * Compara por la palabra
     *  @see java.lang.Comparable#compareTo(java.lang.Object) 
     */
    public int compareTo(Telemento elem) {
        int comparacion=elem.getSignificado().compareTo(this.getSignificado());
        comparacion=Math.abs(comparacion);
        comparacion=comparacion + Math.abs(elem.getPalabra().compareTo(this.getPalabra()));
        return comparacion;
    }
}
