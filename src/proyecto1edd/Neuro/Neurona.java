/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd.Neuro;

import proyecto1edd.Lista;

/**
 *
 * @author LuchoPC
 */
public class Neurona {
    String id;
    Neurona sig;
    Lista Lista_sinapsis;
    public Neurona(String id) {
        this.id = id;
        this.sig = null;
        this.Lista_sinapsis = new Lista();
    }
    
    
    
    
}
