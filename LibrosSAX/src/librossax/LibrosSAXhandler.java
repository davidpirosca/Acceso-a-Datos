/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librossax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author 3rWaZzZa
 */
public class LibrosSAXhandler extends DefaultHandler {

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (qName.equals("Libro")) {
            System.out.println("Publicado en: " + atts.getValue(atts.getQName(0))); // extrae el primer atributo
        } else if (qName.equals("Titulo")) {
            System.out.print("\n " + "El título es: "); // aún no sabemos cuál es el título, eso lo sabremos en el evento characters
        } else if (qName.equals("Autor")) {
            System.out.print("\n " + "El autor es: ");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("Libro")) {
            System.out.println("\n-----------------------");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String car = new String(ch, start, length);
        car = car.replaceAll("\t", ""); // quita todos los tabuladores
        car = car.replaceAll("\n", "");
        System.out.print(car);
    }
}
