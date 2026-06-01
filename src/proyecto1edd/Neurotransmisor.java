/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

/**
 * Clase que representa un neurotransmisor con todas sus propiedades
 *
 * @author user
 */
public class Neurotransmisor {

    // Atributos públicos para acceso directo
    public String ID;           // Identificador único del neurotransmisor
    public String nombre;       // Nombre del neurotransmisor
    public String efecto;       // Tipo de efecto (excitador, inhibidor, etc.)
    public float velocidad;     // Velocidad de transmisión sináptica
    public String descripcion;  // Descripción detallada

    /**
     * Constructor que inicializa todas las propiedades del neurotransmisor
     *
     * @param Id Identificador único
     * @param nombre Nombre del neurotransmisor
     * @param efecto Efecto que produce
     * @param velocidad Velocidad de transmisión
     * @param descripcion Descripción adicional
     */
    public Neurotransmisor(String Id, String nombre, String efecto, float velocidad, String descripcion) {
        this.ID = Id;
        this.nombre = nombre;
        this.efecto = efecto;
        this.velocidad = velocidad;
        this.descripcion = descripcion;
    }
}
