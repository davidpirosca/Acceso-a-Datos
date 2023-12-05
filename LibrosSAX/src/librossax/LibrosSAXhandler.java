package librossax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LibrosSAXhandler extends DefaultHandler {

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {//el inicio del libro
        if (qName.equals("Libro")) {
            System.out.println("Publicado en: " + atts.getValue(atts.getQName(0)));//extrae el primer atributo
        } else if (qName.equals("Titulo")) {
            System.out.print("\n " + "El título es: ");//aún no sabemos cúal es el título, eso lo sabremos en el evento characters
        } else if (qName.equals("Autor")) {
            System.out.print("\n " + "El autor es: ");//por ultimo imprime el de autor
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {//el final del libro
        if (qName.equals("Libro")) {
            System.out.println("\n-----------------------");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {//muestra los datos de dentro de las etiquetas
        String car = new String(ch, start, length);
        car = car.replaceAll("\t", ""); //quita las tabulaciones
        car = car.replaceAll("\n", ""); //quita saltos de línea.
        System.out.print(car);
    }
}
