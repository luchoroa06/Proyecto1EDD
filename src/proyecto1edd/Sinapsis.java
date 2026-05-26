/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

import proyecto1edd.Neuro.Neurona;

/**
 * Clase que representa una sinapsis (conexión entre dos neuronas) Contiene toda
 * la información de la conexión neuronal
 *
 * @author LuchoPC
 */
public class Sinapsis {

    // Atributos públicos para acceso directo
    public Neurona ID_Neurona_Origen;    // Neurona que envía la señal
    public Neurona ID_Neurona_Destino;   // Neurona que recibe la señal
    public float distancia_sináptica;    // Distancia entre neuronas
    public String ID_Neurotransmisor;    // Identificador del neurotransmisor usado
    public int eficiencia_sináptica;     // Eficiencia de la transmisión

    /**
     * Constructor que crea una nueva sinapsis
     *
     * @param ID_Neurona_Origen Neurona origen
     * @param ID_Neurona_Destino Neurona destino
     * @param distancia_sináptica Distancia entre neuronas
     * @param ID_Neurotransmisor Tipo de neurotransmisor
     * @param eficiencia_sináptica Eficiencia de la transmisión
     */
    public Sinapsis(Neurona ID_Neurona_Origen, Neurona ID_Neurona_Destino,
            float distancia_sináptica, String ID_Neurotransmisor, int eficiencia_sináptica) {
        this.ID_Neurona_Origen = ID_Neurona_Origen;
        this.ID_Neurona_Destino = ID_Neurona_Destino;
        this.distancia_sináptica = distancia_sináptica;
        this.ID_Neurotransmisor = ID_Neurotransmisor;
        this.eficiencia_sináptica = eficiencia_sináptica;
    }
}
