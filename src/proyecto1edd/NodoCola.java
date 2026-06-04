/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

import proyecto1edd.Neuro.Neurona;

/**
 *
 * @author LuchoPC
 */
public class NodoCola {

    public NodoCola sig;       // Siguiente nodo en la cola
    public Neurona dato;       // Vértice almacenado

    public NodoCola(Neurona dato) {
        this.sig = null;
        this.dato = dato;
    }

}
