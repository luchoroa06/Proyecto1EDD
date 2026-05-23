/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1edd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import proyecto1edd.Neuro.Grafo;
import proyecto1edd.Neuro.Neurona;

/**
 *
 * @author LuchoPC
 */
public class GestorCSV {

    /**
     * Lee un archivo CSV y construye el grafo Formato esperado:
     *ID_Neurona_Origen, ID_Neurona_Destino, distancia_sináptica, ID_Neurotransmisor, coheficiente_eficiencia_sináptica.
     * ejem 1,2,0.85,GLU, 1
     * @param archivo Archivo CSV a leer
     * @param grafo Grafo donde se insertarán los datos
     */

    public void leerArchivo(File archivo, Grafo grafo) {
        String linea;
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {     
            

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(separador);

                if (datos.length >= 5) {
                    int s1 = Integer.parseInt(datos[0].trim());  //
                    int s2 = Integer.parseInt(datos[1].trim());     //
                    float s3 = Integer.parseInt(datos[2].trim());   //
                    String s4 = datos[3].trim();    //
                    int s5 = Integer.parseInt(datos[4].trim()); //
                    grafo.insertar(s1);                     
                    grafo.insertar(s2);
                    grafo.insertarArista(grafo.buscar(s1), grafo.buscar(s2), s3, s4, s5);

                }
            }

            System.out.println(grafo.mostrar());  // Muestra resultado

        } catch (Exception e) {
            System.out.println("Error al leer: " + e.getMessage());
            System.out.println(javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Guarda el grafo en un archivo CSV
     *
     * @param archivo Archivo donde guardar
     * @param grafo Grafo a guardar
     */
    public void guardarArchivo(File archivo, Grafo grafo) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))) {

            Neurona aux = grafo.primero;
            while (aux != null) {
                NodoLista aux2 = aux.Lista_sinapsis.primero;
                while (aux2 != null) {
                    String linea = String.format("%d,%d,%f,%s,%d", //int origen, int destino, float distancia, String neurotransmisor, int eficiencia
                            aux2.sinapsis.ID_Neurona_Origen.id,
                            aux2.sinapsis.ID_Neurona_Destino.id,
                            aux2.sinapsis.distancia_sináptica,
                            aux2.sinapsis.ID_Neurotransmisor,
                            aux2.sinapsis.eficiencia_sináptica
                    );

                    pw.println(linea);
                    aux2 = aux2.sig;
                }
                aux = aux.sig;
            }

            javax.swing.JOptionPane.showMessageDialog(null, "Archivo guardado exitosamente");

        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "Error al guardar el archivo");
        }
    }
}

}
