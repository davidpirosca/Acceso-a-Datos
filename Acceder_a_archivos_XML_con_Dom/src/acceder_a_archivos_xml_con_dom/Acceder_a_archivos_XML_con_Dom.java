package acceder_a_archivos_xml_con_dom;

import java.io.File;

public class Acceder_a_archivos_XML_con_Dom {
    
    public static void main(String[] args) {
        	AccesoDOM a=new AccesoDOM();
		File f=new File("Libros.xml");//necesitamos Libros.xml en la ruta correcta
		a.abriXMLaDOM(f);
                System.out.println("\n\n\n\n");
                a.recorreDOMyMuestra();
	}
}
