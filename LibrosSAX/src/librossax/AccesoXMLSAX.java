package librossax;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AccesoXMLSAX {

    SAXParser parser;

    //metodo para parsear el archivo que se le pasa como parametro
    public int parsearXMLconLibrosSAXhandler(File f) {
        try {
            //se crea una instancia y se le pasa el parser al SAXParser creado antes
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();

            LibrosSAXhandler sh = new LibrosSAXhandler();//objeto de la clase creado antes para sacar los datos que nos interesan del fichero
            
            parser.parse(f, sh);//hace el parseo del archivo pasandole el fichero y el handler

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //metodo para parsear el archivo con los titulos
    public int parsearXMLconTitulosSAXhandler(File f) {
        try {
            //se crea una instancia y se le pasa el parser al SAXParser creado antes
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            
            TitulosSAXhandler sh = new TitulosSAXhandler();//objeto de la clase creado antes para sacar los datos que nos interesan del fichero

            parser.parse(f, sh);//hace el parseo del archivo pasandole el fichero y el handler

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //metodo para parsear el archivo con titulos y autores
    public int parsearXMLconTitulosYAutorSAXhandler(File f) {
        try {
            //se crea una instancia y se le pasa el parser al SAXParser creado antes
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();

            TitulosYAutorSAXhandler sh = new TitulosYAutorSAXhandler();//objeto de la clase creado antes para sacar los datos que nos interesan del fichero

            parser.parse(f, sh);//hace el parseo del archivo pasandole el fichero y el handler

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
