package librossax;

import java.io.File;

public class UsaAccesoXMLSAX {

    public static void main(String[] args) {
        File f = new File("Libros.xml");//pasamos el archivo que queremos parsear
        AccesoXMLSAX a = new AccesoXMLSAX();//creamos un objeto de la clase
        //para todos los libros y sus datos
        //a.parsearXMLconLibrosSAXhandler(f);
        //para todos los libros pero solo titulo
        a.parsearXMLconTitulosSAXhandler(f);
        //para todos los libros pero titulo y autor
        //a.parsearXMLconTitulosYAutorSAXhandler(f);
    }
}
