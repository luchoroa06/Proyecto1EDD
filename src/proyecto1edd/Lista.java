/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

/**
 *
 * @author LuchoPC
 */
public class Lista {
    NodoLista primero;       //Primero nodo de la lista de aristas
    int t;              // Tamaño de la lista
/**
 * constructor que crea la lista vacia
 */
    public Lista() {
        this.primero = null;
        this.t = 0;

    }

    public void insertar(int dato, String nombre_neurona) {
        NodoLista nuevo = new NodoLista(dato, nombre_neurona);
        if (primero == null) {          //por si la lsita esta vacia
            primero = nuevo;
            t++;
        } else {
            NodoLista aux = primero;
            while (aux.sig != null) {
                aux = aux.sig;        //recorre hasta el final de la lista
            }
            aux.sig = nuevo;          // conecta el nuevo nodo
            nuevo.ant = aux;
            t++;
        }

    }
}   
    /**


    public void eliminar(String dato) {
        if (primero != null) {
            NodoLista aux = primero;
            if (aux.dato.equals(dato)) {        // Si es el primero
                primero = primero.sig;
                primero.ant = null;
                t--;
                
                return;
            }
            while (aux.sig != null && !aux.sig.dato.equals(dato)) {
                aux = aux.sig;                  //busca el nodo a eliminar
            }
            if (aux.sig != null) {
                aux.sig = aux.sig.sig;          //reasigna los punteros 
                aux.sig.ant = aux;
                t--;
            }

        }
    }
 

    public NodoLista buscar(String dato) {
        if (primero != null) {
            NodoLista aux = primero;
            while (aux != null && !aux.dato.equals(dato)) {
                aux = aux.sig;

            }
            return aux;
        }
        return null;
    }


    public String mostrar() {
        String salida = "";
        NodoLista aux = primero;

        while (aux != null) {
            salida += "[" + aux.dato + "] ";
            aux = aux.sig;
        }
        return salida; 
    }

}
**/
