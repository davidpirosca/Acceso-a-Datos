package librossax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TitulosSAXhandler extends DefaultHandler {

    private String etiqueta; //varaible con la etiqueta actual

    public TitulosSAXhandler() {
        etiqueta = ""; //tambien se puede hacer con int etiqueta
    }

    @Override
    public void startDocument() {//inicio del documento
        System.out.println("LISTADO DE TITULOS");
        System.out.println("==================");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {//inicio del elemento
        
        if (qName.equals("Libro")) {
            etiqueta = "Libro"; //sie s un libro
        } else if (qName.equals("Titulo")) {
            etiqueta = "Titulo"; //si es un titulo
        } else if (qName.equals("Autor")) {
            etiqueta = "Autor"; //si es un autor
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {//mostrar los datos de dentro de las etiquetas
        
        if (etiqueta.equals("Titulo")) {//si es un titulo lo muestra
            String car = new String(ch, start, length);
            car = car.replaceAll("\t", "");//quita tabulaciones
            car = car.replaceAll("\n", "");//quita saltos de linea
            System.out.println(car);//muestra titulo
        }
    }
}
