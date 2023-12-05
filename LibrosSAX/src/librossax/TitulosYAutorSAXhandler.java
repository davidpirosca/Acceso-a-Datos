package librossax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TitulosYAutorSAXhandler extends DefaultHandler {

    private String etiqueta;//varaible con la etiqueta actual
    private StringBuilder buffer;//buffer para quitar los espacios raros

    public TitulosYAutorSAXhandler() {
        etiqueta = "";//tambien se puede hacer con int etiqueta
        buffer = new StringBuilder();//inicializa el buffer
    }

    @Override
    public void startDocument() {//inicio del documento
        System.out.println("LISTADO DE TITULOS Y AUTORES\n");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {//inicio del elemento
        etiqueta = qName;//cuando se encuentra un nombre lo almacena en la etiqueta
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {//mostrar los datos de dentro de las etiquetas
        buffer.append(ch, start, length);//mete los datos en un buffer, basicamente para quitar los caracteres raros
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {//finald el ducumento 

        String contenido = buffer.toString().trim();//lo que contiene el buffer lo pasa al string, .trim() quita los espacios al principio y final

        if (!contenido.isEmpty()) {//mientras el contenido no este vacio
            if (etiqueta.equals("Titulo")) {//si la etiqueta es titulo
                System.out.print(contenido + ", ");
            } else if (etiqueta.equals("Autor")) {//si la etiqueta es autor
                System.out.println(contenido);
            }
        }
        buffer.setLength(0);//limpiar el buffer despues de cada elemento para que no tenga nada para el siguiente
    }
}