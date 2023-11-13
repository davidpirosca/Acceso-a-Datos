package librossax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TitulosYAutorSAXhandler extends DefaultHandler {

    private String etiqueta;
    private StringBuilder buffer;

    public TitulosYAutorSAXhandler() {
        etiqueta = "";
        buffer = new StringBuilder();
    }

    @Override
    public void startDocument() {
        System.out.println("LISTADO DE TITULOS Y AUTORES\n");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        etiqueta = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String contenido = buffer.toString().trim();

        if (!contenido.isEmpty()) {
            if (etiqueta.equals("Titulo")) {
                System.out.print(contenido + ", ");
            } else if (etiqueta.equals("Autor")) {
                System.out.println(contenido);
            }
        }
        buffer.setLength(0); // Limpiar el buffer después de procesar cada elemento
    }
}