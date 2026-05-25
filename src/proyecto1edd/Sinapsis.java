/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

/**
 *
 * @author LuchoPC
 */
public class Sinapsis {
    int ID_Neurona_Origen; 
    int ID_Neurona_Destino;
    int distancia_sináptica;
    String ID_Neurotransmisor; 
    int eficiencia_sináptica;

    public Sinapsis(int ID_Neurona_Origen, int ID_Neurona_Destino, int distancia_sináptica, String ID_Neurotransmisor, int eficiencia_sináptica) {
        this.ID_Neurona_Origen = ID_Neurona_Origen;
        this.ID_Neurona_Destino = ID_Neurona_Destino;
        this.distancia_sináptica = distancia_sináptica;
        this.ID_Neurotransmisor = ID_Neurotransmisor;
        this.eficiencia_sináptica = eficiencia_sináptica;
    }
    
    
    
}
