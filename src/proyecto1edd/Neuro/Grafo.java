/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd.Neuro;

import proyecto1edd.Cola;
import proyecto1edd.Hash;
import proyecto1edd.Lista;
import proyecto1edd.Neurotransmisor;
import proyecto1edd.NodoCola;
import proyecto1edd.NodoLista;
import proyecto1edd.Sinapsis;

/**
 * Clase que representa un grafo de neuronas usando una lista enlazada simple
 *
 * @author LuchoPC
 */
public class Grafo {

    public Neurona primero;   // Primera neurona del grafo (cabeza de la lista)

    /**
     * Constructor que crea un grafo vacío
     */
    public Grafo() {
        this.primero = null;   // El grafo comienza sin neuronas
    }

    /**
     * Inserta una nueva neurona en el grafo si no existe
     *
     * @param dato ID de la neurona a insertar
     */
    public void insertar(int dato) {
        // Se verifica que la neurona no exista antes de insertar
        if (this.buscar(dato) == null) {
            Neurona nuevo = new Neurona(dato);   // Se crea la nueva neurona
            if (primero == null) {
                primero = nuevo;   // Si el grafo está vacío, la nueva neurona es la primera
            } else {
                Neurona aux = primero;
                while (aux.sig != null) {   // Se recorre hasta el final de la lista
                    aux = aux.sig;
                }
                aux.sig = nuevo;   // Se conecta la nueva neurona al final
            }
        }
    }

    /**
     * Elimina una neurona del grafo por su ID
     *
     * @param dato ID de la neurona a eliminar
     */
    public void eliminar(int dato) {
        if (primero != null) {
            // Caso especial: la neurona a eliminar es la primera
            if (primero.id == dato) {
                primero = primero.sig;   // La segunda neurona pasa a ser la primera
                return;
            }
            Neurona aux = primero;

            // Se busca la neurona anterior a la que se quiere eliminar
            while (aux.sig != null && aux.sig.id != dato) {
                aux = aux.sig;
            }
            // Si se encontró, se salta el nodo
            if (aux.sig != null) {
                aux.sig = aux.sig.sig;   // Se elimina la referencia a la neurona
            }
        }
    }

    /**
     * Busca una neurona por su ID
     *
     * @param dato ID de la neurona a buscar
     * @return La neurona encontrada o null si no existe
     */
    public Neurona buscar(int dato) {
        Neurona aux = primero;
        // Se recorre la lista mientras no se llegue al final y no se encuentre el ID
        while (aux != null && aux.id != dato) {
            aux = aux.sig;
        }
        return aux;   // Si no se encuentra, retorna null
    }

    /**
     * Inserta una nueva sinapsis entre dos neuronas
     *
     * @param origen ID de la neurona que envía la señal
     * @param destino ID de la neurona que recibe la señal
     * @param distancia Distancia sináptica
     * @param neurotransmisor ID del neurotransmisor usado
     * @param eficiencia Eficiencia de la transmisión
     */
    public void insertarArista(int origen, int destino, float distancia, String neurotransmisor, int eficiencia) {
        Neurona a = this.buscar(origen);   // Se busca la neurona origen
        Neurona b = this.buscar(destino);  // Se busca la neurona destino

        // Solo se crea la sinapsis si ambas neuronas existen
        if (a != null && b != null) {
            Sinapsis s = new Sinapsis(a, b, distancia, neurotransmisor, eficiencia);
            a.Lista_sinapsis.insertar(s);   // Se agrega la sinapsis a la lista de la neurona origen
        }
    }

    /**
     * Muestra todas las neuronas y sus conexiones sinápticas
     *
     * @return String con la información del grafo
     */
    public String mostrar() {
        String salida = "";
        Neurona aux = primero;

        while (aux != null) {
            // Se muestra la neurona y todas sus sinapsis
            salida += "[" + aux.id + "] ---> " + aux.Lista_sinapsis.mostrar() + "\n";
            aux = aux.sig;   // Se avanza a la siguiente neurona
        }
        return salida;
    }

    public void multiplicadorK() {
        Neurona aux = primero;
        while (aux != null) {
            NodoLista auxadyacente = aux.Lista_sinapsis.primero;
            while (auxadyacente != null) {
                auxadyacente.sinapsis.eficiencia_sináptica *= 1.2;
                auxadyacente = auxadyacente.sig;
            }
            aux = aux.sig;
        }

    }

    public String[] dfsrecursivo() {
        String[] recorrido = new String[20];
        Neurona aux = primero;
        int cont = 0;
        while (aux != null) {
            if (!aux.visitada) {
                recorrido[cont] = this.dfs(aux);
                cont++;

            }
            aux = aux.sig;
            aux.visitada = false;
        }
        aux = primero;
        while (aux != null) {
            aux.visitada = false;
        }
        aux = aux.sig;
        return recorrido;
    }

    private String dfs(Neurona aux) {
        String recorrido = aux.id + "";
        NodoLista aux2 = primero.Lista_sinapsis.primero;
        aux.visitada = true;

        while (aux != null && aux2 != null) {

            if (aux2.sinapsis.ID_Neurona_Destino.visitada == false) {

                String recorrido2 = this.dfs(aux2.sinapsis.ID_Neurona_Destino);
                recorrido += recorrido2;
            }

        }
        return recorrido;
    }

    public String BFS() {
        String resultado = "Componentes Conexos (BFS):\n";

        // Reiniciar banderas de visitados
        Neurona aux = primero;
        while (aux != null) {
            aux.visitada = false;
            aux = aux.sig;
        }

        int contador = 1;
        aux = primero;

        // Recorremos todas las neuronas
        while (aux != null) {
            // Si la neurona no ha sido visitada, empezamos un nuevo componente
            if (!aux.visitada) {
                resultado += "Componente " + (contador++) + ": ";

                // Creamos una cola para el BFS (usando tu clase Cola)
                Cola cola = new Cola();
                cola.encolar(aux);
                aux.visitada = true;

                // Mientras la cola no esté vacía
                while (cola.primero != null) {
                    NodoCola nodoActual = cola.desencolar();
                    Neurona actual = nodoActual.dato;
                    resultado += actual.id + " ";

                    // Recorremos todas las sinapsis de la neurona actual
                    NodoLista sinapsisActual = actual.Lista_sinapsis.primero;
                    while (sinapsisActual != null) {
                        Neurona destino = sinapsisActual.sinapsis.ID_Neurona_Destino;
                        // Si el destino no ha sido visitado, lo encolamos
                        if (!destino.visitada) {
                            destino.visitada = true;
                            cola.encolar(destino);
                        }
                        sinapsisActual = sinapsisActual.sig;
                    }
                }
                resultado += "\n";
            }
            aux = aux.sig;
        }
        aux = primero;
        //se reinicia otra vez para un proximo uso
        while (aux != null) {
            aux.visitada = false;
            aux = aux.sig;
        }
        return resultado;
    }

    /**
     * @param inicio ID de la neurona de inicio
     * @param fin ID de la neurona de destino
     * @param tablaHash Tabla hash que contiene los neurotransmisores con sus
     * velocidades
     * @param k factor de atenuación (fatiga neuronal, normalmente 1.0 si no hay
     * fatiga)
     * @return String con la ruta y el tiempo total
     */
    public String dijkstra(int inicio, int fin, Hash tablaHash, float k) {
        // 1. Obtener todas las neuronas en un arreglo
        Neurona[] neuronas = obtenerArregloNeuronas();
        int n = neuronas.length;

        // 2. Encontrar índices de inicio y fin
        int startIdx = -1, endIdx = -1;
        for (int i = 0; i < n; i++) {
            if (neuronas[i].id == inicio) {
                startIdx = i;
            }
            if (neuronas[i].id == fin) {
                endIdx = i;
            }
        }

        if (startIdx == -1) {
            return "Neurona de inicio " + inicio + " no encontrada.";
        }
        if (endIdx == -1) {
            return "Neurona de destino " + fin + " no encontrada.";
        }

        // 3. Inicializar estructuras de Dijkstra
        float[] tiempos = new float[n];      // Tiempo mínimo desde inicio a cada neurona
        int[] padres = new int[n];           // Neurona anterior en la ruta óptima
        boolean[] visitados = new boolean[n];

        for (int i = 0; i < n; i++) {
            tiempos[i] = Float.MAX_VALUE;
            padres[i] = -1;
            visitados[i] = false;
        }

        tiempos[startIdx] = 0;  // Tiempo de inicio a inicio es 0

        // 4. Algoritmo de Dijkstra
        for (int i = 0; i < n - 1; i++) {
            // Encontrar la neurona no visitada con el tiempo mínimo
            int u = -1;
            float minTiempo = Float.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visitados[j] && tiempos[j] <= minTiempo) {
                    minTiempo = tiempos[j];
                    u = j;
                }
            }

            // Si no hay más neuronas alcanzables o ya llegamos al destino, terminamos
            if (u == -1 || tiempos[u] == Float.MAX_VALUE) {
                break;
            }
            if (u == endIdx) {
                break;  // Optimización: ya encontramos el camino más corto al destino
            }
            visitados[u] = true;
            Neurona neuronaU = neuronas[u];

            // Recorrer todas las sinapsis SALIENTES de la neurona U
            NodoLista sinapsisActual = neuronaU.Lista_sinapsis.primero;
            while (sinapsisActual != null) {
                Sinapsis sinapsis = sinapsisActual.sinapsis;
                Neurona destino = sinapsis.ID_Neurona_Destino;

                // Encontrar índice del destino
                int v = -1;
                for (int j = 0; j < n; j++) {
                    if (neuronas[j].id == destino.id) {
                        v = j;
                        break;
                    }
                }

                if (v != -1 && !visitados[v]) {
                    // Buscar el neurotransmisor en la tabla hash
                    String idNeurotransmisor = sinapsis.ID_Neurotransmisor;
                    Neurotransmisor neurotransmisor = tablaHash.buscar(idNeurotransmisor);

                    float velocidad;
                    if (neurotransmisor != null) {
                        velocidad = neurotransmisor.velocidad;
                    } else {
                        System.err.println("Advertencia: Neurotransmisor " + idNeurotransmisor + " no encontrado en la tabla hash. Usando velocidad por defecto 1.0");
                        velocidad = 1.0f;
                    }

                    // Calcular peso de la arista usando la fórmula: W = d / (v * k)
                    float peso = sinapsis.distancia_sináptica / (velocidad * k);
                    float nuevoTiempo = tiempos[u] + peso;

                    // Si encontramos un camino más rápido, actualizamos
                    if (nuevoTiempo < tiempos[v]) {
                        tiempos[v] = nuevoTiempo;
                        padres[v] = u;  // u es el predecesor de v
                    }
                }
                sinapsisActual = sinapsisActual.sig;
            }
        }

        // 5. Verificar si hay camino
        if (tiempos[endIdx] == Float.MAX_VALUE) {
            return "No hay camino desde la neurona " + inicio + " hasta la neurona " + fin;
        }

        // 6. Reconstruir el camino
        String camino = "";
        int temp = endIdx;
        while (temp != -1) {
            camino = neuronas[temp].id + (camino.isEmpty() ? "" : " → ") + camino;
            temp = padres[temp];
        }

        return String.format("Ruta más rápida: %s\nTiempo total de transmisión: %.4f unidades de tiempo",
                camino, tiempos[endIdx]);
    }

    /**
     * Convierte la lista enlazada de neuronas en un arreglo para facilitar el
     * acceso por índice
     */
    private Neurona[] obtenerArregloNeuronas() {
        // Contar cuántas neuronas hay
        int count = 0;
        Neurona aux = primero;
        while (aux != null) {
            count++;
            aux = aux.sig;
        }

        // Llenar el arreglo
        Neurona[] arreglo = new Neurona[count];
        aux = primero;
        for (int i = 0; i < count; i++) {
            arreglo[i] = aux;
            aux = aux.sig;
        }
        return arreglo;
    }
}
