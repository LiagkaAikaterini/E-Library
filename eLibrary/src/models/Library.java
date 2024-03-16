package models;

import java.util.List;

import exceptions.InvalidUserInfoException;
import exceptions.NewCategoryException;
import exceptions.NotFoundException;
import exceptions.UserNotFoundException;

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
    public static UserBase authenticateUser(String username, String password) throws UserNotFoundException {
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

        throw new UserNotFoundException();
    }

    public static List<Book> getTop5Books() {

        List<Book> top5Books = allBooks;
        
        top5Books.sort((book1, book2) -> {
            if (book1.getAvgRating() > book2.getAvgRating()) {
                // sort book1 before book2
                return -1;
            }
            else if (book1.getAvgRating() < book2.getAvgRating()) {
                // book1 after book2
                return 1;
            }
            else{
                // leave order unchanged
                return 0;
            }
        });

        if (top5Books.size() < 5) {
            return top5Books;
        }
        else {
            return top5Books.subList(0, 5);
        }

    }


    public static Book findBook(String isbn) throws NotFoundException {
        List<Book> books = getAllBooks();

        for (Book book : books) {
            if ( (book.getISBN()).equals(isbn)) {
                return book;
            }
        }

        throw new NotFoundException("Book not found");
    }

    public static Category findCategory(String name) throws NotFoundException {
        for (Category category : allCategories) {
            if ( (category.getName()).equals(name) ) {
                return category;
            }
        }

        throw new NotFoundException("Category not found");
    }

    public static User findUser(String username) throws UserNotFoundException {
        for (User user : allUsers) {
            if ( (user.getUsername()).equals(username)) {
                return user;
            }
        }

        throw new UserNotFoundException();
    }

    public static Admin findAdmin(String username) throws UserNotFoundException {
        for (Admin admin : allAdmins) {
            if ( (admin.getUsername()).equals(username)) {
                return admin;
            }
        }

        throw new UserNotFoundException();
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

    public static boolean isUsernameOccupied(String username) throws InvalidUserInfoException {
        try {
            Library.findAdmin(username);
            return true;
        }
        catch (UserNotFoundException e) {}

        try {
            Library.findUser(username);
            return true;
        }
        catch (UserNotFoundException e) {}

        return true;
    }

    public static boolean isIdNumOccupied(String idNum) {
        for (User user : allUsers) {
            if ( idNum.equals(user.getIdNum()) ) {
                return false;
            }
        }

        return true;
    }

    public static boolean isEmailOccupied(String email) {
        for (User user : allUsers) {
            if ( email.equals(user.getEmail()) ) {
                return false;
            }
        }

        return true;
    }

    public static Category categoryOfBook(String isbn) throws NotFoundException {
        for (Category category : allCategories) {
            List<String> categoryBooks = category.getBooksISBN();
            if ( categoryBooks.contains(isbn) ) {
                return category;
            }
        }

        throw new NotFoundException("This Book belongs to no category. Please add category for this book.");
    }
    

    public static void createCategory(String newCategoryName) throws NewCategoryException {
        // if category already exists
        for (Category category : allCategories) {
            if (newCategoryName.equals(category.getName())) {
                throw new NewCategoryException();
            }
        }

        Category cat = new Category(newCategoryName);
        addCategory(cat);
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
