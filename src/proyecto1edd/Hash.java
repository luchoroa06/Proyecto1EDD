/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

/**
 *
 * @author LuchoPC
 */
    public class Hash {
    
    ListaHash hash[];   // Arreglo de listas enlazadas (cada posición es una lista)

    /**
     * Constructor: inicializa los 200 espacios con listas vacías
     */
    public Hash() {
        this.hash = new ListaHash[200];
        for (int i = 0; i < 200; i++) {
            this.hash[i] = new ListaHash();   // Cada espacio tiene su propia lista
        }
    }
    
    /**
     * Función hash que calcula el índice a partir del ID
     * Multiplica cada letra por 73 y suma, luego se obtiene el residuo de 200
     * 73 elegido por el numero favorito de Sheldon Cooper de la serie de TV The Big Bang Teory,
     * para el, el mejor numero.
     * @param id Identificador del neurotransmisor
     * @return Índice entre 0 y 199
     */
    public int hash(String id){
        int nombre = 0;
        for (int i = 0; i < id.length(); i++) {
            nombre += id.charAt(i) * 73;   // Se multiplica el valor de cada letra por 73
        }
        return nombre % 200;   // Se asegura que el índice esté dentro del arreglo
    }
    
    /**
     * Busca un neurotransmisor por su ID
     * @param id Identificador del neurotransmisor
     * @return El neurotransmisor encontrado o null si no existe
     */
    public Neurotransmisor buscar(String id){
        int indice = this.hash(id);           // Se calcula en qué espacio puede estar
        return this.hash[indice].buscar(id);  // Se busca dentro de la lista de ese espacio
    }
    
    /**
     * Agrega un nuevo neurotransmisor a la tabla hash
     * @param ID Identificador único
     * @param nombre Nombre del neurotransmisor
     * @param efecto Efecto que produce
     * @param velocidad Velocidad de transmisión
     * @param descripcion Descripción adicional
     */
    public void agregar(String ID, String nombre, String efecto, float velocidad, String descripcion){
        int indice = this.hash(ID);   // Se calcula el espacio donde va
        // Se crea el neurotransmisor y se inserta en la lista del espacio correspondiente
        this.hash[indice].insertar(new Neurotransmisor(ID, nombre, efecto, velocidad, descripcion));
    }
    
    /**
     * Elimina un neurotransmisor de la tabla hash
     * @param id Identificador del neurotransmisor a eliminar
     */
    public void eliminar(String id){
        int indice = this.hash(id);                    // Se calcula en qué espacio está
        Neurotransmisor e = this.hash[indice].buscar(id);  // Se busca el elemento
        this.hash[indice].eliminar(e);                 // Se elimina de la lista
    }
    
    /**
     * Retorna una cadena con todos los neurotransmisores almacenados
     * @return String con la información de todos los neurotransmisores
     */
    public String imprimir(){
        String neurotransmisores = "";
        for(int i = 0; i < 200; i++){
            if(!this.hash[i].imprimir().equals("")){
                neurotransmisores += this.hash[i].imprimir() + "\n";      
            }      // Se conectan todos los espacios
        }
        return neurotransmisores;
    }
}
    
