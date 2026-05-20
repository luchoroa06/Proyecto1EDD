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
public class Sinapsis {
    public Neurona ID_Neurona_Origen; 
    public Neurona ID_Neurona_Destino;
    public float distancia_sináptica;
    public String ID_Neurotransmisor; 
    public int eficiencia_sináptica;

    public Sinapsis(Neurona ID_Neurona_Origen, Neurona ID_Neurona_Destino, float distancia_sináptica, String ID_Neurotransmisor, int eficiencia_sináptica) {
        this.ID_Neurona_Origen = ID_Neurona_Origen;
        this.ID_Neurona_Destino = ID_Neurona_Destino;
        this.distancia_sináptica = distancia_sináptica;
        this.ID_Neurotransmisor = ID_Neurotransmisor;
        this.eficiencia_sináptica = eficiencia_sináptica;
    }
    
    
    
}
