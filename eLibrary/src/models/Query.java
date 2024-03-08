package models;

import java.util.ArrayList;
import java.util.List;

// general queries used by all classes 
public class Query {
    
    public static Book findBook(String isbn) {
        List<Book> books = Library.getAllBooks();

        for (Book book : books) {
            if ( (book.getISBN()).equals(isbn)) {
                return book;
            }
        }

        return null;
    }

    public static Category findCategory(String name) {
        List<Category> categories = Library.getAllCategories();

        for (Category cat : categories) {
            if ( (cat.getName()).equals(name) ) {
                return cat;
            }
        }

        return null;
    }

    public static User findUser(String username) {
        List<User> users = Library.getAllUsers();

        for (User user : users) {
            if ( (user.getUsername()).equals(username)) {
                return user;
            }
        }

        return null;
    }

    public static Admin findAdmin(String username) {
        List<Admin> admins = Library.getAllAdmins();

        for (Admin admin : admins) {
            if ( (admin.getUsername()).equals(username)) {
                return admin;
            }
        }

        return null;
    }


    public static List<Borrowed> findUsersActiveBorrows(String username) {
        List<Borrowed> activeBorrows = Library.getAllActiveBorrows();
        List<Borrowed> result = new ArrayList<Borrowed>();

        for (Borrowed borrow : activeBorrows) {
            if ( (borrow.getUsername()).equals(username)) {
                result.add(borrow);
            }
        }

        return result;
    }

    public static boolean isUsernameAvailable(String username) {
        List<User> users = Library.getAllUsers();

        for (User user : users) {
            if ( username.equals(user.getUsername()) ) {
                return false;
            }
        }

        return true;
    }

    public static boolean isIsbnAvailable(String isbn) {
        List<Book> books = Library.getAllBooks();

        for (Book book : books) {
            if ( isbn.equals(book.getISBN()) ) {
                return false;
            }
        }

        return true;
    }


}
