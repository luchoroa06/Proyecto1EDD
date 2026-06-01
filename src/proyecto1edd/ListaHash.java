/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

/**
 * Lista enlazada simple para manejar colisiones en la tabla hash
 * Cada espacio del Hash contiene una de estas listas
 * @author user
 */
public class ListaHash {
    NodoHash primero;   // Primer nodo de la lista
    
    /**
     * Constructor: inicializa la lista vacía
     */
    public ListaHash(){
        this.primero = null;
    }
    
    /**
     * Inserta un nuevo neurotransmisor al final de la lista
     * @param nuevo Neurotransmisor a insertar
     */
    public void insertar(Neurotransmisor nuevo){
        NodoHash neuronuevo = new NodoHash(nuevo);   // Se crea el nodo con el neurotransmisor
        
        if (this.primero == null){   // Si la lista está vacía
            this.primero = neuronuevo;   // El nuevo nodo se convierte en el primero
        } else {
            NodoHash aux = primero;
            while(aux.siguiente != null){   // Se recorre hasta el final de la lista
                aux = aux.siguiente;
            }
            aux.siguiente = neuronuevo;   // Se conecta el nuevo nodo al final
        }
    }
    
    /**
     * Elimina un neurotransmisor de la lista
     * @param neurotransmisor Referencia al neurotransmisor a eliminar
     */
    public void eliminar(Neurotransmisor neurotransmisor){ 
        if(this.primero != null){
            NodoHash aux = primero;
            // Se busca el nodo anterior al que se quiere eliminar
            while(aux.siguiente != null && aux.siguiente.neurotransmisor != neurotransmisor){
                aux = aux.siguiente;
            }
            if(aux.siguiente != null){
                aux.siguiente = aux.siguiente.siguiente;   // Se salta el nodo a eliminar
            }
        }
    }
    
    /**
     * Busca un neurotransmisor por su ID
     * @param id Identificador del neurotransmisor
     * @return El neurotransmisor encontrado o null si no existe
     */
    public Neurotransmisor buscar(String id){
        NodoHash aux = primero;
        // Se recorre la lista mientras no se llegue al final y no se encuentre el ID
        while(aux != null && !aux.neurotransmisor.ID.equals(id)){
            aux = aux.siguiente;
        }
        if(aux != null){
            return aux.neurotransmisor;   // Se retorna el neurotransmisor encontrado
        } else {
            return null;   // No se encontró
        }
    }
    
    /**
     * Concatena la información de todos los neurotransmisores en la lista
     * @return String con los datos en formato CSV
     */
    public String imprimir(){
        NodoHash aux = primero;
        String info = "";
        while(aux != null){
            // Formato: ID,Nombre,Efecto,Velocidad,Descripción
            info += aux.neurotransmisor.ID + "," + aux.neurotransmisor.nombre + "," 
                    + aux.neurotransmisor.efecto + "," + aux.neurotransmisor.velocidad 
                    + "," + aux.neurotransmisor.descripcion + "\n";
            aux = aux.siguiente;
        }
        return info;
    }
}