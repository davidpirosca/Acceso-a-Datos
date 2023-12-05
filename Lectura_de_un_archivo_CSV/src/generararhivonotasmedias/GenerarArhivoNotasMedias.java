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

public class GenerarArhivoNotasMedias {

    public static void main(String[] args) {

        String ruta = "src/generararhivonotasmedias/";//ruta
        String ficheroEntrada = ruta + "alumnosNotas.txt";//archivo entrada
        String ficheroSalida = ruta + "alumnosMedias.txt";//archivo salida
        String linea;//datos de cada linea

        try {
            //creamos un bufferedreader para poder ller el archivo linea  linea
            BufferedReader in = new BufferedReader(new FileReader(ficheroEntrada));
            while ((linea = in.readLine()) != null) {//mientras las lineas no sean nulas sigue leyendo
                String[] partes = linea.split(":");//dividimos la linea en partes separadas por :
                if (partes.length == 4) {//mientras tenga 4 partes
                    String nombre = partes[0].substring(0, 1).toUpperCase() + partes[0].substring(1);//primera parte es el nombre
                    double nota1 = Double.parseDouble(partes[1]);//las siguientes son numeros
                    double nota2 = Double.parseDouble(partes[2]);
                    double nota3 = Double.parseDouble(partes[3]);
                    
                    double promedio = (nota1 + nota2 + nota3) / 3;//sacamos el promedio de las notas

                    System.out.println("Mi Nombre es: " + nombre + "\nNota Media: " + promedio);
                    System.out.println("====================");

                    String lineaDatos = nombre + ":" + promedio;//cremaos otra linea nueva con el nombre y promedio separado por :

                    guardarDatos(ficheroSalida, lineaDatos);//guardamos en el fichero de salida
                } else {
                    System.out.println("Error: La línea no contiene datos separados por ':'");
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    //metodo para guardar los datos en el fichero
    private static void guardarDatos(String nombreFichero, String linea) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(nombreFichero, true))) {//creamos el buffered para escribir cone l nombre 
                                                                                            //del fichero yle true para que no sobrescriba los datos
            out.write(linea);//escribe la linea en el archivo
           //out.newLine(); //nueva linea para el proximo dato
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo: " + ex.getMessage());
        }
    }

}
