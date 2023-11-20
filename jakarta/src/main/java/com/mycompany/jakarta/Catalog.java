package com.mycompany.jakarta;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Catalog {
    
    @XmlElement(name = "book")
    private List<Book> listaBooks = null;

    public List<Book> getBook() {
        return listaBooks;
    }

    public void setListaBooks(List<Book> listaBooks) {
        this.listaBooks = listaBooks;
    }
}
