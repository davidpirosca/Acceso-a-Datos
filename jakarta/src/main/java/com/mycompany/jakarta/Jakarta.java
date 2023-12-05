/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.jakarta;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class Jakarta {

    public static void main(String[] args) {
        try {
            // Inicializa el contexto de JAXB para la clase 'Catalog'
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);

            // Crea un deserializador (Unmarshaller) para convertir XML a objetos Java
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Deserializa el archivo XML 'books.xml' en un objeto 'Catalog'
            Catalog catalogo = (Catalog) jaxbUnmarshaller.unmarshal(new File("books.xml"));

            // Itera a través de cada libro en el catálogo e imprime sus detalles
            for (Book libro : catalogo.getBook()) {
                System.out.println("ID: " + libro.getId());
                System.out.println("Author: " + libro.getAuthor());
                System.out.println("Title: " + libro.getTitle());
                System.out.println("Categoria: " + libro.getGenre());
                System.out.println("Precio: " + libro.getPrice());
                System.out.println("Fecha Publicación: " + libro.getPublish_date());
                System.out.println("Descripción: " + libro.getDescription());
                System.out.println("----------------------------------");
            }

        } catch (JAXBException e) {
            // Si ocurre un error durante la deserialización, imprime la traza de la excepción
            e.printStackTrace();
        }
    }
}
