/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

/**
 *
 * @author user
 */
public class ListaHash {
    NodoHash primero;
public ListaHash(){
    this.primero= null;
    
}
public void insertar(Neurotransmisor nuevo){
  
    NodoHash neuronuevo = new NodoHash(nuevo);
    
    if (this.primero ==null){
        this.primero = neuronuevo ;
         }
    else {
        NodoHash aux = primero;
        while(aux.siguiente != null){
            aux = aux.siguiente; 
        }
            aux.siguiente = neuronuevo;
        }
   
        
 }
  public void eliminar(Neurotransmisor neurotransmisor){ 
      if(this.primero !=null){
           NodoHash aux= primero;
      while(aux.siguiente != null && aux.siguiente.neurotransmisor !=neurotransmisor){
          aux = aux.siguiente;
          
      }
      if(aux.siguiente != null){
      aux.siguiente = aux.siguiente.siguiente;
      }
  }     
   

        

        
}
}
