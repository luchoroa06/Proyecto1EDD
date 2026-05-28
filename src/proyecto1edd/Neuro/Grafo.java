/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd.Neuro;

import proyecto1edd.Lista;
import proyecto1edd.NodoLista;
import proyecto1edd.Sinapsis;

/**
 * Clase que representa un grafo de neuronas usando una lista enlazada simple
 *
 * @author LuchoPC
 */
public class Grafo {

    public Neurona primero;   // Primera neurona del grafo (cabeza de la lista)

    /**
     * Constructor que crea un grafo vacío
     */
    public Grafo() {
        this.primero = null;   // El grafo comienza sin neuronas
    }

    /**
     * Inserta una nueva neurona en el grafo si no existe
     *
     * @param dato ID de la neurona a insertar
     */
    public void insertar(int dato) {
        // Se verifica que la neurona no exista antes de insertar
        if (this.buscar(dato) == null) {
            Neurona nuevo = new Neurona(dato);   // Se crea la nueva neurona
            if (primero == null) {
                primero = nuevo;   // Si el grafo está vacío, la nueva neurona es la primera
            } else {
                Neurona aux = primero;
                while (aux.sig != null) {   // Se recorre hasta el final de la lista
                    aux = aux.sig;
                }
                aux.sig = nuevo;   // Se conecta la nueva neurona al final
            }
        }
    }

    /**
     * Elimina una neurona del grafo por su ID
     *
     * @param dato ID de la neurona a eliminar
     */
    public void eliminar(int dato) {
        if (primero != null) {
            // Caso especial: la neurona a eliminar es la primera
            if (primero.id == dato) {
                primero = primero.sig;   // La segunda neurona pasa a ser la primera
                return;
            }
            Neurona aux = primero;

            // Se busca la neurona anterior a la que se quiere eliminar
            while (aux.sig != null && aux.sig.id != dato) {
                aux = aux.sig;
            }
            // Si se encontró, se salta el nodo
            if (aux.sig != null) {
                aux.sig = aux.sig.sig;   // Se elimina la referencia a la neurona
            }
        }
    }

    /**
     * Busca una neurona por su ID
     *
     * @param dato ID de la neurona a buscar
     * @return La neurona encontrada o null si no existe
     */
    public Neurona buscar(int dato) {
        Neurona aux = primero;
        // Se recorre la lista mientras no se llegue al final y no se encuentre el ID
        while (aux != null && aux.id != dato) {
            aux = aux.sig;
        }
        return aux;   // Si no se encuentra, retorna null
    }

    /**
     * Inserta una nueva sinapsis entre dos neuronas
     *
     * @param origen ID de la neurona que envía la señal
     * @param destino ID de la neurona que recibe la señal
     * @param distancia Distancia sináptica
     * @param neurotransmisor ID del neurotransmisor usado
     * @param eficiencia Eficiencia de la transmisión
     */
    public void insertarArista(int origen, int destino, float distancia, String neurotransmisor, int eficiencia) {
        Neurona a = this.buscar(origen);   // Se busca la neurona origen
        Neurona b = this.buscar(destino);  // Se busca la neurona destino

        // Solo se crea la sinapsis si ambas neuronas existen
        if (a != null && b != null) {
            Sinapsis s = new Sinapsis(a, b, distancia, neurotransmisor, eficiencia);
            a.Lista_sinapsis.insertar(s);   // Se agrega la sinapsis a la lista de la neurona origen
        }
    }

    /**
     * Muestra todas las neuronas y sus conexiones sinápticas
     *
     * @return String con la información del grafo
     */
    public String mostrar() {
        String salida = "";
        Neurona aux = primero;

        while (aux != null) {
            // Se muestra la neurona y todas sus sinapsis
            salida += "[" + aux.id + "] ---> " + aux.Lista_sinapsis.mostrar() + "\n";
            aux = aux.sig;   // Se avanza a la siguiente neurona
        }
        return salida;
    }
    public void multiplicadorK(){
        Neurona aux = primero;
        while(aux != null){
            NodoLista auxadyacente = aux.Lista_sinapsis.primero;
            while(auxadyacente != null){
                auxadyacente.sinapsis.eficiencia_sináptica *= 1.2;
                auxadyacente = auxadyacente.sig;
            }
            aux = aux.sig;
        }
        
    }
}
