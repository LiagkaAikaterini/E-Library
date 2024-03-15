package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import exceptions.InvalidUserInformationException;


public class UserBase implements Serializable {
    private String username;
    private String password;
    private boolean isAdmin;

    public UserBase(String username, String password, boolean isAdmin) throws Exception{
        // Before creating the object we make sure the username is unique 
        //and has the appropriate caharacters A-Z, a-z, 0-9, _, starts with letter and has length between 6 and 30
        Pattern allowedUsernamePattern = Pattern.compile("^[A-Za-z]\\w{5,29}$");

        if (!allowedUsernamePattern.matcher(username).matches()) {
            throw new InvalidUserInformationException("Invalid Username: Please use only letters (A-Z, a-z), numbers (0-9), and underscores (_). The username must start with a letter and contain between 6-30 characters");
        }

        if ( Library.findAdmin(username) != null || Library.findUser(username) != null ) {
            throw new InvalidUserInformationException("This username is already used, please choose another unique username");
        }

        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    
    public static List<Book> searchByYear(Integer year) {
        // if no input null will be returned - if no matches empty ArrayList will be returned
        if (year == null) {
            return null;
        }

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
        // if no input null will be returned - if no matches empty ArrayList will be returned
        if (title.isEmpty()) {
            return null;
        }

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
        // if no input null will be returned - if no matches empty ArrayList will be returned
        if (author.isEmpty()) {
            return null;
        }

        List<Book> books = Library.getAllBooks();
        List<Book> searchResult = new ArrayList<Book>();
        
        for (Book book : books) {
            if ( (book.getAuthor()).contains(author) ) {
                searchResult.add(book);
            }
        }
        
        return searchResult;
    }

    public static List<Book> search(List<Book> res1, List<Book> res2, List<Book> res3) {
        return combineSearches(res1, combineSearches(res2, res3));
    }

    
    public static List<Book> combineSearches(List<Book> booksRes1, List<Book> booksRes2) {
        try {
            // if any of the 2 lists empty an empty list will be returned
            List<Book> searchResult = new ArrayList<Book>();

            searchResult = booksRes1;
            searchResult.retainAll(booksRes2);
            
            return searchResult;
        }
        catch (NullPointerException n) {
            // if any of the lists is null return the list that is not null
            // if both null return null
            if (booksRes1 == null) {
                if (booksRes2 == null) {
                    return null;
                }
                else {
                    return booksRes2;
                }
            }
            else {
                return booksRes1;
            }
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
    public void setUsername(String username) throws Exception {
        if ( username.equals(this.username) ) {
            return;
        }

        // check if username already exists - Usernames Must be unique
        // make sure the username has the appropriate caharacters A-Z, a-z, 0-9, _
        Pattern allowedUsernamePattern = Pattern.compile("^[a-zA-Z0-9_]*$");

        if (!allowedUsernamePattern.matcher(username).matches()) {
            throw new InvalidUserInformationException("Invalid Username: Please use only letters (A-Z, a-z), numbers (0-9), and underscores (_)");
        }

        // if there is an admin or a user with this username - username not available
        if ( Library.findAdmin(username) != null || Library.findUser(username) != null ) {
            throw new InvalidUserInformationException("This username is not available. Please choose a different username");
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
