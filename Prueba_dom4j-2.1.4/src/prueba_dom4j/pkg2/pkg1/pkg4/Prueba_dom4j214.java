/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba_dom4j.pkg2.pkg1.pkg4;

import static java.nio.file.Files.size;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class Prueba_dom4j214 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read("Libros.xml");
        
        Element root = document.getRootElement();
        
        for (int i = 0; size=root.nodeCount(); i < size; i++) {
            
        }
    }
}
