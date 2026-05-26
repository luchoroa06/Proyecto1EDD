/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

/**
 * Nodo para la lista enlazada de la tabla hash Cada nodo contiene un
 * neurotransmisor y una referencia al siguiente
 *
 * @author user
 */
public class NodoHash {

    Neurotransmisor neurotransmisor;   // Dato almacenado en el nodo
    NodoHash siguiente;                // Referencia al siguiente nodo

    /**
     * Constructor que crea un nodo con un neurotransmisor
     * @param neurotransmisor El neurotransmisor a almacenar
     */
    public NodoHash(Neurotransmisor neurotransmisor) {
        this.neurotransmisor = neurotransmisor;
        this.siguiente = null;   
    }
}
