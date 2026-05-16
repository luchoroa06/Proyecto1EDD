/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd.Neuro;

/**
 *
 * @author LuchoPC
 */
public class Grafo {
    Neurona primero;
    
   public Grafo(){
       this.primero = null;
      
   }
     public void insertar(String dato) {

        Neurona nuevo = new Neurona(dato);

        if (primero == null) {
            primero = nuevo;
        } else {
            Neurona aux = primero;

            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevo;
        }

    }
}
