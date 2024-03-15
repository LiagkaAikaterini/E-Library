package models;

import java.util.List;
import java.util.ArrayList;


public class Library {
    private static List<Admin> allAdmins;
    private static List<User> allUsers;
    private static List<Book> allBooks;
    private static List<Category> allCategories;
    private static List<Borrowed> allActiveBorrows;

    public static void initializeData() {
        Library.allAdmins = DataStorageManager.deserialize("src/medialab/admins.ser");
        Library.allUsers = DataStorageManager.deserialize("src/medialab/users.ser");
        Library.allBooks = DataStorageManager.deserialize("src/medialab/books.ser");
        Library.allCategories = DataStorageManager.deserialize("src/medialab/categories.ser");
        Library.allActiveBorrows = DataStorageManager.deserialize("src/medialab/borrows.ser");
    }

    public static void saveData() {
        // serialize data from each data file and populate the corresponding list
        DataStorageManager.serialize("src/medialab/admins.ser", allAdmins);
        DataStorageManager.serialize("src/medialab/users.ser", allUsers);
        DataStorageManager.serialize("src/medialab/books.ser", allBooks);
        DataStorageManager.serialize("src/medialab/categories.ser", allCategories);
        DataStorageManager.serialize("src/medialab/borrows.ser", allActiveBorrows);
    }


    // Retrieve User or Admin seperately
    // check in frontend
    // Exception not logged in yet !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static User getCurrUser(UserBase loggedUser) {
        for (User user : allUsers) {
            if ( (user.getUsername()).equals(loggedUser.getUsername()) ) {
                return user;
            }
        }
        return null;
    }

    public static Admin getCurrAdmin(UserBase loggedUser) {
        for (Admin admin : allAdmins) {
            if ( (admin.getUsername()).equals(loggedUser.getUsername()) ) {
                return admin;
            }
        }
        return null;
    }

    public static UserBase authenticateUser(String username, String password) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }

        for (Admin admin : allAdmins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }

        return null;
    }


    public static Book findBook(String isbn) {
        List<Book> books = getAllBooks();

        for (Book book : books) {
            if ( (book.getISBN()).equals(isbn)) {
                return book;
            }
        }

        return null;
    }

    public static Category findCategory(String name) {
        for (Category category : allCategories) {
            if ( (category.getName()).equals(name) ) {
                return category;
            }
        }

        return null;
    }

    public static User findUser(String username) {
        for (User user : allUsers) {
            if ( (user.getUsername()).equals(username)) {
                return user;
            }
        }

        return null;
    }

    public static Admin findAdmin(String username) {
        for (Admin admin : allAdmins) {
            if ( (admin.getUsername()).equals(username)) {
                return admin;
            }
        }

        return null;
    }


    public static List<Borrowed> findUsersActiveBorrows(String username) {
        List<Borrowed> result = new ArrayList<Borrowed>();

        for (Borrowed borrow : allActiveBorrows) {
            if ( (borrow.getUsername()).equals(username)) {
                result.add(borrow);
            }
        }

        return result;
    }

    public static boolean isUsernameAvailable(String username) {
        for (User user : allUsers) {
            if ( username.equals(user.getUsername()) ) {
                return false;
            }
        }

        return true;
    }

    public static boolean isIsbnAvailable(String isbn) {
        for (Book book : allBooks) {
            if ( isbn.equals(book.getISBN()) ) {
                return false;
            }
        }

        return true;
    }

    public static String categoryOfBook(String isbn) {
        for (Category category : allCategories) {
            List<String> categoryBooks = category.getBooksISBN();
            if ( categoryBooks.contains(isbn) ) {
                return category.getName();
            }
        }

        return null;
    }
    

    public static void createCategory(String newCategoryName) {
        try {
            for (Category category : allCategories) {
                if (category.getName().equals(newCategoryName)) {
                    throw new Exception("This category already exists, please enter a new unique category name");
                }
            }
            Category cat = new Category(newCategoryName);
            addCategory(cat);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    // getters setters - add - remove
    public static List<Admin> getAllAdmins() {
        return allAdmins;
    }
    public static void setAllAdmins(List<Admin> allAdmins) {
        Library.allAdmins = allAdmins;
    }
    public static void addAdmins(Admin admin) {
        Library.allAdmins.add(admin);
    }
    public static void removeAdmins(Admin admin) {
        Library.allAdmins.remove(admin);
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }
    public static void setAllUsers(List<User> allUsers) {
        Library.allUsers = allUsers;
    }
    public static void addUsers(User user) {
        Library.allUsers.add(user);
    }
    public static void removeUser(User user) {
        Library.allUsers.remove(user);
    }

    public static List<Book> getAllBooks() {
        return allBooks;
    }
    public static void setAllBooks(List<Book> allBooks) {
        Library.allBooks = allBooks;
    }
    public static void addBook(Book book) {
        Library.allBooks.add(book);
    }
    public static void removeBook(Book book) {
        Library.allBooks.remove(book);
    }

    public static List<Category> getAllCategories() {
        return allCategories;
    }
    public static void setAllCategories(List<Category> allCategories) {
        Library.allCategories = allCategories;
    }
    public static void addCategory(Category category) {
        Library.allCategories.add(category);
    }
    public static void removeCategory(Category category) {
        Library.allCategories.remove(category);
    }


    public static List<Borrowed> getAllActiveBorrows() {
        return allActiveBorrows;
    }
    public static void setAllActiveBorrows(List<Borrowed> allActiveBorrows) {
        Library.allActiveBorrows = allActiveBorrows;
    }
    public static void addActiveBorrow(Borrowed borrow) {
        Library.allActiveBorrows.add(borrow);
    }
    public static void removeActiveBorrow(Borrowed borrow) {
        Library.allActiveBorrows.remove(borrow);
    }

}
