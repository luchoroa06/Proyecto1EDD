/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

/**
 *
 * @author user
 */
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;

import proyecto1edd.Neuro.Grafo;
import proyecto1edd.Neuro.Neurona;

import javax.swing.*;
import java.awt.*;

public class VisualizarGrafo {

    private Grafo grafoEstructura;
    private Graph graphStream;

    // Paleta de colores en formato Hexadecimal para pintar zonas aisladas
    private String[] coloresZonas = {
        "#3498db", "#e74c3c", "#2ecc71", "#f1c40f", "#9b59b6", "#1abc9c", "#e67e22"
    };

    public VisualizarGrafo(Grafo grafoEstructura) {
        this.grafoEstructura = grafoEstructura;
        // Configurar GraphStream para que use el renderizador Swing
        System.setProperty("org.graphstream.ui", "swing");
    }

    /**
     * Construye y retorna la vista del grafo integrada en un componente JPanel
     */
    public JPanel construirGrafo() {
        graphStream = new SingleGraph("Red Neuronal");

        // Habilitar auto-layout físico y estilos de calidad
        graphStream.setAttribute("ui.quality");
        graphStream.setAttribute("ui.antialias");
        
        // CORRECCIÓN DEL CSS: Se cambiaron las propiedades 'label' por 'text'
        String estiloCss = 
            "node {" +
            "   size: 30px;" +
            "   fill-color: #7f8c8d;" + 
            "   text-mode: normal;" +              // Corregido
            "   text-background-mode: rounded-box;" + // Corregido
            "   text-background-color: white;" +   // Corregido
            "   text-padding: 3px;" +              // Corregido
            "   text-size: 14px;" +
            "   text-alignment: center;" +
            "}" +
            "edge {" +
            "   shape: cubic-curve;" +
            "   arrow-size: 10px, 4px;" +
            "   text-size: 11px;" +
            "   text-background-mode: rounded-box;" +
            "   text-background-color: #f5f5f5;" +
            "}";
        graphStream.setAttribute("ui.stylesheet", estiloCss);

        // 1. Agregar todos los nodos (Neuronas)
        Neurona auxNeurona = grafoEstructura.primero;
        while (auxNeurona != null) {
            Node n = graphStream.addNode(String.valueOf(auxNeurona.id));
            n.setAttribute("ui.label", "Neurona " + auxNeurona.id);
            auxNeurona = auxNeurona.sig;
        }

        // 2. Agregar todas las aristas (Sinapsis)
        auxNeurona = grafoEstructura.primero;
        while (auxNeurona != null) {
            NodoLista auxSinapsis = auxNeurona.Lista_sinapsis.primero;
            while (auxSinapsis != null) {
                Sinapsis s = auxSinapsis.sinapsis;
                String idOrigen = String.valueOf(s.ID_Neurona_Origen.id);
                String idDestino = String.valueOf(s.ID_Neurona_Destino.id);
                String idArista = idOrigen + "->" + idDestino;

                if (graphStream.getEdge(idArista) == null) {
                    Edge e = graphStream.addEdge(idArista, idOrigen, idDestino, true);
                    e.setAttribute("ui.label", String.format("%s (d:%.1f)", s.ID_Neurotransmisor, s.distancia_sináptica));
                }
                auxSinapsis = auxSinapsis.sig;
            }
            auxNeurona = auxNeurona.sig;
        }

        // 3. Colorear zonas aisladas (Componentes Conexos con BFS)
        identificarYColorearZonas();

        // Crear el SwingViewer e integrar la vista en la aplicación
        SwingViewer viewer = new SwingViewer(graphStream, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout(); // Permite que los nodos se distribuyan automáticamente al inicio
        
        // Agrega el DefaultView. El parámetro true permite interactuar con el ratón (arrastrar nodos)
        View view = viewer.addDefaultView(false); 
        
        return (JPanel) view;
    }

    private void identificarYColorearZonas() {
        Neurona aux = grafoEstructura.primero;
        while (aux != null) {
            aux.visitada = false;
            aux = aux.sig;
        }

        int zonaContador = 0;
        aux = grafoEstructura.primero;

        while (aux != null) {
            if (!aux.visitada) {
                String colorHex = coloresZonas[zonaContador % coloresZonas.length];
                
                proyecto1edd.Cola cola = new proyecto1edd.Cola();
                cola.encolar(aux);
                aux.visitada = true;

                while (cola.primero != null) {
                    proyecto1edd.NodoCola nodoActual = cola.desencolar();
                    Neurona actual = nodoActual.dato;
                    
                    Node nodeUI = graphStream.getNode(String.valueOf(actual.id));
                    if (nodeUI != null) {
                        nodeUI.setAttribute("ui.style", "fill-color: " + colorHex + ";");
                    }

                    NodoLista sinapsisActual = actual.Lista_sinapsis.primero;
                    while (sinapsisActual != null) {
                        Neurona destino = sinapsisActual.sinapsis.ID_Neurona_Destino;
                        if (!destino.visitada) {
                            destino.visitada = true;
                            cola.encolar(destino);
                        }
                        sinapsisActual = sinapsisActual.sig;
                    }
                }
                zonaContador++;
            }
            aux = aux.sig;
        }
        
        // Limpieza final de banderas para futuros análisis
        aux = grafoEstructura.primero;
        while (aux != null) {
            aux.visitada = false;
            aux = aux.sig;
        }
    }
}
