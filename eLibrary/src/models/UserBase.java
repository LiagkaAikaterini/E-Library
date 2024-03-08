package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UserBase implements Serializable {
    private String username;
    private String password;
    private boolean isAdmin;

    public UserBase(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public static List<Book> searchByYear(int year) {
        List<Book> books = Library.getAllBooks();
        List<Book> searchResult = new ArrayList<Book>();
        
        for (Book book : books) {
            if (book.getPublicationYear() == year) {
                searchResult.add(book);
            }
        }

        return searchResult;
    }

    public static List<Book> searchByTitle(String title) {
        List<Book> books = Library.getAllBooks();
        List<Book> searchResult = new ArrayList<Book>();
        
        for (Book book : books) {
            if ( (book.getTitle()).contains(title) ) {
                searchResult.add(book);
            }
        }
        
        return searchResult;
    }

    public static List<Book> searchByAuthor(String author) {
        List<Book> books = Library.getAllBooks();
        List<Book> searchResult = new ArrayList<Book>();
        
        for (Book book : books) {
            if ( (book.getAuthor()).contains(author) ) {
                searchResult.add(book);
            }
        }
        
        return searchResult;
    }

    public static List<Book> searchByPublisher(String publisher) {
        List<Book> books = Library.getAllBooks();
        List<Book> searchResult = new ArrayList<Book>();
        
        for (Book book : books) {
            if ( (book.getPublisher()).contains(publisher) ) {
                searchResult.add(book);
            }
        }
        
        return searchResult;
    }

    
    public static List<Book> combineSearches(List<Book> booksRes1, List<Book> booksRes2) {
        try {
            List<Book> searchResult = new ArrayList<Book>();

            searchResult = booksRes1;
            searchResult.retainAll(booksRes2);
            
            return searchResult;
        }
        catch (NullPointerException n) {
            // if any of the lists is null return empty list
            return new ArrayList<Book>();
        }
        
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) throws Exception{
        if ( username.equals(this.username) ) {
            return;
        }

        // check if username already exists - Usernames Must be unique
        if ( !Query.isUsernameAvailable(username) ) {
            throw new Exception("This username is not available. Please try a different username.");
        }
        
        this.username = username;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
}
