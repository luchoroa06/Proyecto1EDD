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
public class Cola {
    public NodoCola primero;       // Primer nodo de la cola 
    public NodoCola ultimo;        // Último nodo de la cola (para encolar)

    public Cola() {
        this.primero = null;
        this.ultimo = null;
    }
   /**
     * Agrega un Neurona al final de la cola
     * @param a Neurona a encolar
     */
    public void encolar(Neurona a){
        NodoCola nuevo = new NodoCola(a);
        if(primero == null){
            primero = ultimo = nuevo;       // Si está vacía, el nuevo es el primero y el último
            return;
        }
        ultimo.sig = nuevo;
        ultimo = nuevo;
    }
     /**
     * Remueve y retorna el primer Neurona de la cola
     * @return Neurona removido o null si cola vacía
     */   
    public NodoCola desencolar(){
        if(primero!= null){
            NodoCola denigrado = primero;
            primero = primero.sig;
            return denigrado;
        }
        return null;
    
    } 
    
    
}
    

