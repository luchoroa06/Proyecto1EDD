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
 * Clase encargada de leer y guardar archivos CSV para el grafo neuronal y los
 * neurotransmisores
 *
 * @author LuchoPC
 */
public class GestorCSV {

    /**
     * Lee un archivo CSV y construye el grafo con las conexiones El archivo
     * debe tener: origen, destino, distancia, neurotransmisor, eficiencia
     * Ejemplo: 1,2,0.85,GLU,1
     *
     * @param archivo El archivo que se va a leer
     * @param grafo El grafo donde se guardarán los datos
     */
    public static void leerArchivo(File archivo, Grafo grafo) {
        String linea;               // Aquí se guarda cada línea leída
        String separador = ",";    // El CSV usa coma para separar los datos
        int con = 0;               // Contador para saltar la primera línea (cabecera)

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            while ((linea = br.readLine()) != null) {   // Se lee línea por línea hasta el final
                System.out.println(linea);               // Se muestra la línea en consola

                if (con != 0) {                          // Se salta la primera línea (la de los títulos)
                    String[] datos = linea.split(separador);   // Se divide la línea por las comas

                    if (datos.length >= 5) {             // Se verifica que tenga al menos 5 campos
                        int s1 = Integer.parseInt(datos[0].trim());   // ID de la neurona origen
                        int s2 = Integer.parseInt(datos[1].trim());   // ID de la neurona destino
                        float s3 = Float.parseFloat(datos[2].trim()); // distancia sináptica
                        String s4 = datos[3].trim();                  // ID del neurotransmisor
                        int s5 = Integer.parseInt(datos[4].trim());   // eficiencia sináptica

                        grafo.insertar(s1);                // Se inserta la neurona origen si no existe
                        grafo.insertar(s2);                // Se inserta la neurona destino si no existe
                        grafo.insertarArista(s1, s2, s3, s4, s5);  // Se crea la sinapsis
                    }
                } else {
                    con += 1;    // Se incrementa el contador después de leer la primera línea
                }
            }

        } catch (IOException e) {
            // Error cuando el archivo no existe o no se puede abrir
            System.out.println("Error al leer archivo: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "Error al leer el archivo CSV");
        } catch (NumberFormatException e) {
            // Error cuando hay letras donde deberían ir números
            System.out.println("Error de formato numérico: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "El archivo contiene números inválidos");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Error cuando a alguna línea le faltan columnas
            System.out.println("Error: línea con formato incorrecto - " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "El archivo CSV tiene columnas insuficientes");
        }
    }

    /**
     * Guarda todo el grafo en un archivo CSV Recorre cada neurona y sus
     * sinapsis, escribiendo una línea por cada sinapsis
     *
     * @param archivo El archivo donde se va a guardar
     * @param grafo El grafo que se quiere guardar
     */
    public static void guardarArchivo(File archivo, Grafo grafo) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))) {  // false significa que sobrescribe el archivo

            Neurona aux = grafo.primero;   // Se empieza desde la primera neurona del grafo

            // Se recorren todas las neuronas una por una
            while (aux != null) {
                NodoLista aux2 = aux.Lista_sinapsis.primero;   // Se obtiene la primera sinapsis de esta neurona

                // Se recorren todas las sinapsis de la neurona actual
                while (aux2 != null) {
                    // Se arma la línea CSV con los datos
                    String linea = String.format("%d,%d,%f,%s,%d",
                            aux2.sinapsis.ID_Neurona_Origen.id, // ID neurona origen
                            aux2.sinapsis.ID_Neurona_Destino.id, // ID neurona destino
                            aux2.sinapsis.distancia_sináptica, // distancia
                            aux2.sinapsis.ID_Neurotransmisor, // neurotransmisor
                            aux2.sinapsis.eficiencia_sináptica // eficiencia
                    );

                    pw.println(linea);   // Se escribe la línea en el archivo
                    aux2 = aux2.sig;     // Se avanza a la siguiente sinapsis
                }
                aux = aux.sig;           // Se avanza a la siguiente neurona
            }

            javax.swing.JOptionPane.showMessageDialog(null, "Archivo guardado exitosamente");

        } catch (IOException e) {
            // Error cuando no se puede crear o escribir en el archivo
            System.out.println("Error al guardar archivo: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage());
        } catch (NullPointerException e) {
            // Error cuando el grafo está vacío o alguna lista es nula
            System.out.println("Error: el grafo está vacío o contiene datos nulos - " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "No hay datos para guardar en el archivo");
        }
    }

    /**
     * Lee un archivo CSV de neurotransmisores y los guarda en la tabla hash El
     * archivo debe tener: ID, Nombre, Efecto, Velocidad, Descripción
     *
     * @param archivo El archivo CSV que se va a leer
     * @param hash La tabla hash donde se guardarán los neurotransmisores
     */
    public static void leerNeurotransmisores(File archivo, Hash hash) {
        String linea;               // Guarda cada línea leída
        String separador = ",";    // Separador del CSV
        int con = 0;               // Contador para saltar la primera línea

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);

                if (con != 0) {    // Se salta la primera línea (la de los títulos)
                    String[] datos = linea.split(separador);

                    if (datos.length >= 5) {
                        String s1 = datos[0].trim();   // ID
                        String s2 = datos[1].trim();   // Nombre
                        String s3 = datos[2].trim();   // Efecto
                        float s4 = Float.parseFloat(datos[3].trim());   // Velocidad
                        String s5 = datos[4].trim();   // Descripción

                        hash.agregar(s1, s2, s3, s4, s5);   // Se agrega a la tabla hash
                    }
                } else {
                    con += 1;
                }
            }
        } catch (IOException e) {
            // Error cuando el archivo no existe o no se puede abrir
            System.out.println("Error al leer archivo de neurotransmisores: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "Error al leer el archivo de neurotransmisores");
        } catch (NumberFormatException e) {
            // Error cuando la velocidad no es un número válido
            System.out.println("Error de formato numérico en velocidad: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "El campo 'velocidad' debe ser un número válido");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Error cuando alguna línea tiene menos de 5 columnas
            System.out.println("Error: línea con formato incorrecto - " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "El archivo CSV de neurotransmisores tiene columnas insuficientes");
        }
    }
}
