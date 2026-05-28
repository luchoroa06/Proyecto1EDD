/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

/**
 * Nodo para lista doblemente enlazada de sinapsis
 *
 * @author LuchoPC
 */
public class NodoLista {

    public NodoLista sig;      // Referencia al siguiente nodo
    public NodoLista ant;      // Referencia al nodo anterior
    public Sinapsis sinapsis;  // Dato almacenado (una sinapsis)

    /**
     * Constructor que crea un nodo con una sinapsis
     *
     * @param sinapsis La sinapsis a almacenar
     */
    public NodoLista(Sinapsis sinapsis) {
        this.sig = null;    // Inicialmente no tiene siguiente
        this.ant = null;    // Inicialmente no tiene anterior
        this.sinapsis = sinapsis;
    }
}
