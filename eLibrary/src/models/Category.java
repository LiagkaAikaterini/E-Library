package models;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import exceptions.NotFoundException;


public class Category implements Serializable {
    private String name;
    private List<String> booksISBN;

    public Category(String name) {
        this.name = name.toLowerCase().replaceAll("\\s+", "");
        this.booksISBN = new ArrayList<String>();
    }

    public List<Book> getAllCategoryBooks() {
        List<Book> books = new ArrayList<Book>();

        for (String isbn : booksISBN) {
            try {
                Book book = Library.findBook(isbn);
                books.add(book);
            }
            catch (NotFoundException e) {}
        }

        return books;
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
        this.name = name.toLowerCase().replaceAll("\\s+", "");
    }

    public List<String> getBooksISBN() {
        return booksISBN;
    }
    public void setBooksISBN(List<String> booksISBN) {
        this.booksISBN = booksISBN;
    }

}
