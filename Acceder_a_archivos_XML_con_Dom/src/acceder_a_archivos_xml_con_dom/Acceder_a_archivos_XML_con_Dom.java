package acceder_a_archivos_xml_con_dom;

import java.io.File;

public class Acceder_a_archivos_XML_con_Dom {
    
    public static void main(String[] args) {
        	AccesoDOM AccesoDOM=new AccesoDOM(); //Creamos el objeto de la clase AccesoDOM
		File f=new File("Libros.xml");//necesitamos Libros.xml en la ruta correcta
		AccesoDOM.abriXMLaDOM(f);//Abrimos el fichero
                System.out.println("\n\n\n\n");
                AccesoDOM.recorreDOMyMuestra();//Leemos el DOM para ver los nodos
                
                System.out.println("\n\n\n\n");
                AccesoDOM.insertarLibroEnDOM("El Misterio de la Isla Perdida", "Jane Doe", "2020-07-15");//Agregar un Libro
                System.out.println("\n\n\n\n");
                AccesoDOM.recorreDOMyMuestra();//Leemos el DOM para ver los nodos
                
                System.out.println("\n\n\n\n");
                AccesoDOM.deleteNode("El Misterio de la Isla Perdida");//Borrar el Libro
                System.out.println("\n\n\n\n");
                AccesoDOM.recorreDOMyMuestra();//Leemos el DOM para ver los nodos
               
	}
}
