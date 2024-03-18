package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import exceptions.InvalidUserInfoException;

/**
 * Represents a general user in the system. 
 * This is the base class that both User and Admin extends.
 * It contains all the common fields and methods that both user and admin should have.
 */
public class UserBase implements Serializable {
    private String username;
    private String password;
    private boolean isAdmin;

    /**
     * Class Constructor
     * Constructs a new UserBase object. 
     * Note that a UserBase object is never constructed by itself in our system. 
     * This constructor is only called in the constructors of its children, User and Admin.
     * 
     * @param username The username of the user. It must be unique, it must contain only characters A-Z, a-z, 0-9, _ and start with a letter.
     * @param password The password of the user. It can't be too short, it must contain 5 or more characters.
     * @param isAdmin  Boolean that shows if this user is an Admin or not. True in this field indicates this user is an admin.
     * @throws InvalidUserInfoException If the username or password do not satisfy the necessary constraints, so that an incorrect object will not be created.
     * 
     */
    public UserBase(String username, String password, boolean isAdmin) throws InvalidUserInfoException {
        
        Pattern allowedUsernamePattern = Pattern.compile("^[A-Za-z]\\w*$");
        if (!allowedUsernamePattern.matcher(username).matches()) {
            throw new InvalidUserInfoException("Invalid Username: Please use only letters (A-Z, a-z), numbers (0-9), and underscores (_). The username must start with letter");
        }

        if (Library.isUsernameOccupied(username)) {
            throw new InvalidUserInfoException("This username is already used, please choose another unique username");
        }

        if (password.length() < 5) {
            throw new InvalidUserInfoException("Too short password. Please create a password with 5 or more characters.");
        }

        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Searches for books published in a specific year.
     * This method is static because it is not dependent on the state of any particular UserBase object
     * 
     * @param year The year to search for.
     * @return A list of books published in the specified year. Returns empty List<Book> if there are no matches.
     */
    public static List<Book> searchByYear(Integer year) {
        List<Book> books = Library.getAllBooks();
        List<Book> searchResult = new ArrayList<Book>();
        
        for (Book book : books) {
            if (book.getPublicationYear().equals(year)) {
                searchResult.add(book);
            }
        }

        return searchResult;
    }

    /**
     * Searches for books that contain a specific string in their title. 
     * The search is not case sensitive. That is why both the specific string and the book title are converted to lowercase.
     * This method is static because it is not dependent on the state of any particular UserBase object
     * 
     * @param title The string to search for in the books' titles.
     * @return A list of books that contain the specified string in their title. Returns empty List<Book> if there are no matches.
     */
    public static List<Book> searchByTitle(String title) {
        List<Book> books = Library.getAllBooks();
        List<Book> searchResult = new ArrayList<Book>();
        
        for (Book book : books) {
            if ( (book.getTitle().toLowerCase()).contains(title.toLowerCase()) ) {
                searchResult.add(book);
            }
        }
        
        return searchResult;
    }

    /**
     * Searches for books written by specific author. 
     * The search is not case sensitive. That is why both the specific author string and the book author are converted to lowercase.
     * This method is static because it is not dependent on the state of any particular UserBase object
     * 
     * @param author The author string to search for.
     * @return A list of books written by the an author that contains the specified author string. Returns empty List<Book> if there are no matches.
     */
    public static List<Book> searchByAuthor(String author) {
        List<Book> books = Library.getAllBooks();
        List<Book> searchResult = new ArrayList<Book>();
        
        for (Book book : books) {
            if ( (book.getAuthor().toLowerCase()).contains(author.toLowerCase()) ) {
                searchResult.add(book);
            }
        }
        
        return searchResult;
    }

    /**
     * Combines the results of two book searches.
     * Although the NullPointerException is handled it will never get thrown in our current system.
     * 
     * @param booksRes1 The result list of books of the first search.
     * @param booksRes2 The result list of books of the second search.
     * @return A combined list of books that contains only the books that are common to both input lists. If one of the input lists is null the other one is returned. If both input lists are null then null is returned.
     */
    public static List<Book> combineSearches(List<Book> booksRes1, List<Book> booksRes2) {
        try {
            List<Book> searchResult = new ArrayList<Book>();

            searchResult = booksRes1;
            searchResult.retainAll(booksRes2);
            
            return searchResult;
        }
        catch (NullPointerException n) {
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

    /**
     * Combines the results of three book searches.
     * 
     * @param res1 The result list of books of the first search.
     * @param res2 The result list of books of the second search.
     * @param res3 The result list of books of the third search.
     * @return A combined list of books that contains only the books that are common to all three input lists.
     */
    public static List<Book> combineThreeSearches(List<Book> res1, List<Book> res2, List<Book> res3) {
        return combineSearches(res1, combineSearches(res2, res3));
    }

    /**
     * Gets the password of this UserBase object.
     * 
     * @return The password of this UserBase object.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of this UserBase object.
     * 
     * @param password The new password for this UserBase object. If it is the same as the old password nothing is changed.
     * @throws InvalidUserInfoException If the password is too short and more specifically less than 5 characters. 
     */
    public void setPassword(String password) throws InvalidUserInfoException {
        if (password.equals(this.password)) {
            return;
        }

        if (password.length() < 5) {
            throw new InvalidUserInfoException("Too short password. Please create a password with 5 or more characters.");
        }
        this.password = password;
    }

    /**
     * Gets the username of this UserBase object.
     * 
     * @return The username of this UserBase object.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of this UserBase object.
     * 
     * @param username The new username for this UserBase object. If it is the same as the old username nothing is changed.
     * @throws InvalidUserInfoException If the username is not unique or if it contain characters other than the expected A-Z, a-z, 0-9, _ or if it does not start with a letter. 
     */
    public void setUsername(String username) throws InvalidUserInfoException {
        if ( username.equals(this.username) ) {
            return;
        }

        Pattern allowedUsernamePattern = Pattern.compile("^[a-zA-Z0-9_]*$");
        if ( !allowedUsernamePattern.matcher(username).matches() ) {
            throw new InvalidUserInfoException("Invalid Username: Please use only letters (A-Z, a-z), numbers (0-9), and underscores (_)");
        }

        if ( Library.isUsernameOccupied(username) ) {
            throw new InvalidUserInfoException("This username is not available. Please choose a different username");
        }
        
        this.username = username;
    }

    /**
     * Checks if this UserBase object is an admin.
     * 
     * @return true if this UserBase object is an admin, false if it is a User.
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets if this UserBase object is an Admin.
     * 
     * @param isAdmin true if this UserBase object is an Admin, false if it is a User.
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
}
