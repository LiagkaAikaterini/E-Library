package models;

import java.util.List;


public class Library {
    private static List<Admin> allAdmins;
    private static List<User> allUsers;
    private static List<Book> allBooks;
    private static List<Category> allCategories;
    private static List<Borrowed> allActiveBorrows;
    private UserBase loggedUser;
    /*
    private User currUser;
    private Admin currAdmin;
    
    public Library(User logUser){
        this.loggedUser = logUser;
        this.currUser = logUser;
        this.currAdmin = null;
    }

    public Library(Admin logUser){
        this.loggedUser = logUser;
        this.currUser = null;
        this.currAdmin = logUser;
    }
     */

    
    public Library() {
        this.loggedUser = null;
        // deserialize data from each data file and populate the corresponding list
        Library.allAdmins = DataManagement.deserialize("src/medialab/admins.ser");
        Library.allUsers = DataManagement.deserialize("src/medialab/users.ser");
        Library.allBooks = DataManagement.deserialize("src/medialab/books.ser");
        Library.allCategories = DataManagement.deserialize("src/medialab/categories.ser");
        Library.allActiveBorrows = DataManagement.deserialize("src/medialab/borrows.ser");
    }

    public void saveData() {
        // serialize data from each data file and populate the corresponding list
        DataManagement.serialize("src/medialab/admins.ser", allAdmins);
        DataManagement.serialize("src/medialab/users.ser", allUsers);
        DataManagement.serialize("src/medialab/books.ser", allBooks);
        DataManagement.serialize("src/medialab/categories.ser", allCategories);
        DataManagement.serialize("src/medialab/borrows.ser", allActiveBorrows);
    }


    // Retrieve User or Admin seperately
    // check in frontend
    public User getCurrUser() {
        for (User user : Library.allUsers) {
            if ( (user.getUsername()).equals(this.loggedUser.getUsername()) ) {
                return user;
            }
        }
        return null;
    }

    public Admin getCurrAdmin() {
        for (Admin admin : Library.allAdmins) {
            if ( (admin.getUsername()).equals(this.loggedUser.getUsername()) ) {
                return admin;
            }
        }
        return null;
    }

    public UserBase getLoggedUser() {
        return loggedUser;
    }
    public void setLoggedUser(UserBase loggedUser) {
        this.loggedUser = loggedUser;
    }

    /*
    public Admin getCurrAdmin() {
        return currAdmin;
    }
    public void setCurrAdmin(Admin currAdmin) {
        this.currAdmin = currAdmin;
    }

    public User getCurrUser() {
        return currUser;
    }
    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }
 */
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
