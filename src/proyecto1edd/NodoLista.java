/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

/**
 *
 * @author LuchoPC
 */
public class NodoLista {
    NodoLista sig;
    NodoLista ant;
    int dato;
    String nombre_neurona;

    public NodoLista(int dato, String nombre_neurona) {
        this.sig = null;
        this.ant = null;
        this.dato = dato;
        this.nombre_neurona = nombre_neurona;
    }

    
}
    
    

