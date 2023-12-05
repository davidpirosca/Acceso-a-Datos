package acceder_a_archivos_xml_con_dom;

import java.io.File;

public class Acceder_a_archivos_XML_con_Dom {

    public static void main(String[] args) {
        AccesoDOM acceso = new AccesoDOM();//objeto con el acceso a dom
        File archivoXML = new File("MiArchivo.xml");//necesitamos Libros.xml en la ruta correcta
        acceso.abriXMLaDOM(archivoXML);//abrimos el archivo dom

        //mostrar el dom
        System.out.println("\n\n\n\n");
        acceso.recorreDOMyMuestra();

        //insertar libro en dom
        System.out.println("\n\n\n\n");
        acceso.insertarLibroEnDOM("La Sombra del Viento", "Carlos Ruiz Zafón", "2001-09-25");
        System.out.println("\n\n\n\n");

        //mostrar el dom
        acceso.recorreDOMyMuestra();
        System.out.println("\n\n\n\n");

        //borrar libro del dom
        acceso.deleteNode("Harry Potter y la Piedra Filosofal");
        System.out.println("\n\n\n\n");

        //mostrar el dom
        acceso.recorreDOMyMuestra();
        System.out.println("\n\n\n\n");

        //guardamos el dom en un archivo
        acceso.guardarDOMcomoArchivo("NuevoArchivo.xml");
        System.out.println("\n\n\n\n");
    }
}
