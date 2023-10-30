/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generararhivonotasmedias;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 3rWaZzZa
 */
public class GenerarArhivoNotasMedias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String ruta = "src/generararhivonotasmedias/";
        String ficheroEntrada = ruta+"alumnosNotas.txt";
        String ficheroSalida = ruta+"alumnosMedias.txt";
        String linea;
        try {
            BufferedReader in = new BufferedReader(new FileReader(ficheroEntrada));
            while ((linea = in.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 4) {
                    String nombre = partes[0].substring(0, 1).toUpperCase() + partes[0].substring(1);
                    double nota1 = Double.parseDouble(partes[1]);
                    double nota2 = Double.parseDouble(partes[2]);
                    double nota3 = Double.parseDouble(partes[3]);
                    double promedio = (nota1 + nota2 + nota3) / 3;
                    System.out.println("Mi Nombre es: " + nombre + "\nNota Media: " + promedio);
                    System.out.println("====================");
                    String lineaDatos = nombre+":"+promedio;
                    
                    guardarDatos(ficheroSalida,lineaDatos);
                } else {
                    System.out.println("Error: La línea no contiene datos separados por :");
                }
                //System.out.println(linea);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    private static void guardarDatos(String nombreFichero, String linea) {

        try ( BufferedWriter out = new BufferedWriter(new FileWriter(nombreFichero, true))) {
            out.write(linea);
            out.newLine();
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo: " + ex.getMessage());
        }
    }
}
