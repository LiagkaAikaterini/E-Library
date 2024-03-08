package models;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;


public class Category implements Serializable {
    private String name;
    private List<String> booksISBN;

    public Category(String name) {
        this.name  = name;
        this.booksISBN  = new ArrayList<String>();
    }

    public void addToCategoryBooks(String bookIsbn) {
        this.booksISBN.add(bookIsbn);
    }

    public void removeFromCategoryBooks(String bookIsbn) {
        this.booksISBN.remove(bookIsbn);
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBooksISBN() {
        return booksISBN;
    }
    public void setBooksISBN(List<String> booksISBN) {
        this.booksISBN = booksISBN;
    }

}
