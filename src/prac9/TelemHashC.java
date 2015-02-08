package prac9;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dorian
 */
class TelemHashC {
    /**
     * dato
     */
    private Telemento dato;
    /**
     * si está borrado
     */
    private boolean borrado;

    /**
     * Constructor
     * @param dato el dato del elemento 
     */
    public TelemHashC(Telemento dato) {
        this.dato = dato;
        this.borrado = false;
    }
    
    /**
     * Devuelve el dato
     * @return el dato
     */
    public Telemento getDato() {
        return dato;
    }

    /**
     * Devuelve si está borrado
     * @return true si está borrado, false al contrario
     */
    public boolean isBorrado() {
        return borrado;
    }

    /**
     * Establece si lo borra o no
     * @param borrado true si está borrado o false si no 
     */
    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object) 
     */
    public int compareTo(TelemHashC elem) {
        return elem.getDato().compareTo(this.getDato());
    }
}
