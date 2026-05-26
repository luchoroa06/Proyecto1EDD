/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

/**
 * Lista doblemente enlazada para almacenar las sinapsis de una neurona
 *
 * @author LuchoPC
 */
public class Lista {

    NodoLista primero;   // Primer nodo de la lista
    int t;               // Tamaño de la lista (cantidad de sinapsis)

    /**
     * Constructor que crea la lista vacía
     */
    public Lista() {
        this.primero = null;
        this.t = 0;
    }

    /**
     * Inserta una nueva sinapsis al final de la lista
     *
     * @param sinapsis La sinapsis a insertar
     */
    public void insertar(Sinapsis sinapsis) {
        NodoLista nuevo = new NodoLista(sinapsis);
        if (primero == null) {          // Si la lista está vacía
            primero = nuevo;
            t++;
        } else {
            NodoLista aux = primero;
            while (aux.sig != null) {   // Se recorre hasta el final
                aux = aux.sig;
            }
            aux.sig = nuevo;            // Se conecta el nuevo nodo al final
            nuevo.ant = aux;            // Se establece la referencia hacia atrás
            t++;
        }
    }

    /**
     * Elimina una sinapsis de la lista
     *
     * @param sinapsis La sinapsis a eliminar
     */
    public void eliminar(Sinapsis sinapsis) {
        if (primero != null) {
            NodoLista aux = primero;

            // Caso especial: el elemento a eliminar es el primero
            if (aux.sinapsis.equals(sinapsis)) {
                primero = primero.sig;   // El segundo nodo pasa a ser primero
                if (primero != null) {
                    primero.ant = null;  // El nuevo primero no tiene anterior
                }
                t--;
                return;
            }

            // Se busca el nodo a eliminar
            while (aux.sig != null && !aux.sig.sinapsis.equals(sinapsis)) {
                aux = aux.sig;
            }

            // Si encontró el nodo, lo elimina
            if (aux.sig != null) {
                aux.sig = aux.sig.sig;   // Se salta el nodo a eliminar
                if (aux.sig != null) {
                    aux.sig.ant = aux;   // Se actualiza la referencia hacia atrás
                }
                t--;
            }
        }
    }

    /**
     * Busca una sinapsis en la lista
     *
     * @param sinapsis La sinapsis a buscar
     * @return El nodo que contiene la sinapsis, o null si no existe
     */
    public NodoLista buscar(Sinapsis sinapsis) {
        if (primero != null) {
            NodoLista aux = primero;
            while (aux != null && !aux.sinapsis.equals(sinapsis)) {
                aux = aux.sig;   // Se avanza al siguiente nodo
            }
            return aux;   // Se retorna el nodo encontrado (o null)
        }
        return null;
    }

    /**
     * Muestra los IDs de las neuronas destino de todas las sinapsis
     *
     * @return String con los IDs separados
     */
    public String mostrar() {
        String salida = "";
        NodoLista aux = primero;

        while (aux != null) {
            salida += "[" + aux.sinapsis.ID_Neurona_Destino.id + "] ";
            aux = aux.sig;
        }
        return salida;
    }
}
