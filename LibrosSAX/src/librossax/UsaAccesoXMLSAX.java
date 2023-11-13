package librossax;

import java.io.File;

public class UsaAccesoXMLSAX {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File f = new File("Libros.xml");
        AccesoXMLSAX a = new AccesoXMLSAX();
        //a.parsearXMLconLibrosSAXhandler(f);
        //a.parsearXMLconTitulosSAXhandler(f);
        a.parsearXMLconTitulosYAutorSAXhandler(f);
        
    }
}
