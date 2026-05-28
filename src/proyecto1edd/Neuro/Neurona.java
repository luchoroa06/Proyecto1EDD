/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd.Neuro;

import proyecto1edd.Lista;

/**
 * Clase que representa una neurona dentro del grafo Cada neurona tiene un ID,
 * una lista de sinapsis, y una referencia a la siguiente neurona
 *
 * @author LuchoPC
 */
public class Neurona {

    public int id;                  // Identificador único de la neurona
    public Neurona sig;            // Referencia a la siguiente neurona en el grafo
    public Lista Lista_sinapsis;   // Lista de sinapsis que salen de esta neurona
    /**
     * Constructor que crea una neurona con un ID específico
     *
     * @param id Identificador de la neurona
     */
    public Neurona(int id) {
        this.id = id;
        this.sig = null;                     // Inicialmente no tiene siguiente
        this.Lista_sinapsis = new Lista();   // Se crea una lista vacía de sinapsis
    }
}
