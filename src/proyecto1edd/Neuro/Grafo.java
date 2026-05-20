/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd.Neuro;

import proyecto1edd.Sinapsis;

/**
 *
 * @author LuchoPC
 */
public class Grafo {

    public Neurona primero;

    public Grafo() {
        this.primero = null;

    }

    public void insertar(int dato) {
        if (this.buscar(dato) == null) {
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

    public void eliminar(int dato) {
        if (primero != null) {
            if (primero.id != dato) {
                primero = primero.sig;
                return;
            }
            Neurona aux = primero;

            while (aux.sig != null && aux.sig.id != dato) {
                aux = aux.sig;
            }
            if (aux.sig != null) {
                aux.sig = aux.sig.sig;
            }
        }
    }

    public Neurona buscar(int dato) {
        Neurona aux = primero;
        while (aux != null && aux.id != dato) {
            aux = aux.sig;
        }
        return aux;

    }

    public void insertarArista(int origen, int destino, float distancia, String neurotransmisor, int eficiencia) {
        Neurona a = this.buscar(origen);
        Neurona b = this.buscar(destino);
        if (a != null && b != null) {
            Sinapsis s = new Sinapsis(a, b, distancia, neurotransmisor, eficiencia);
            a.Lista_sinapsis.insertar(s);
        }

    }
    public String mostrar() {
        String salida = "";
        Neurona aux = primero;

        while (aux != null) {
            salida += "[" + aux.id + "] ---> " + aux.Lista_sinapsis.mostrar() + "\n";
            aux = aux.sig;
        }
        return salida;
    }
}
