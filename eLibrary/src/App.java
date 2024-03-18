import java.time.LocalDate;
import java.util.ArrayList;

import controllers.NavigationController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import models.*;
import models.Book.Review;

public class App extends Application  {

    // MAYBE NOT IN INIT BUT IN START!!!!
    @Override
    public void init() {
        Library.initializeData();
    }

    @Override
    public void start(Stage primaryStage) {
        /*
        Library.setAllAdmins(new ArrayList<>());
        Library.setAllUsers(new ArrayList<>());
        Library.setAllActiveBorrows(new ArrayList<>());
        Library.setAllBooks(new ArrayList<>());
        Library.setAllCategories(new ArrayList<>());
        
        
        try {
            Admin a = new Admin("admin1", "STYLESHEET_CASPIAN");
            Book b1 = new Book("book1", "author1", "publisher1",  "111", java.time.LocalDate.now(), 3);
            Book b2 = new Book("book2", "author2", "publisher2", "222", java.time.LocalDate.now(), 5);
            Category c1 = new Category("category1");
            Category c2 = new Category("category2");
            c1.addToCategoryBooks(b1.getISBN());
            c2.addToCategoryBooks(b2.getISBN());
            User user = new User("username", "STYLESHEET_CASPIAN", "STYLESHEET_CASPIAN", "STYLESHEET_CASPIAN", "STYLESHEET_CASPIAN", "STYLESHEET_MODENA", "STYLESHEET_CASPIAN", java.time.LocalDate.now());
            Library.addAdmins(a);
            Library.addUsers(user);
            Library.addBook(b2);
            Library.addBook(b1);
            Library.addCategory(c2);
            Library.addCategory(c1);

            user.borrowBook(b2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        Platform.exit();
        

        
        /*
        System.out.println();
        System.out.println("ADMINS");
        System.out.println();
        for (Admin user : Library.getAllAdmins()) {
            System.out.println(user.getUsername());
        }

        System.out.println();
        System.out.println("USERS");
        System.out.println();
        for (User user : Library.getAllUsers()) {
            System.out.println(user.getUsername());
            for (String bo : user.getBorrowHistory()) {
                System.out.println("History books");
            }
        }

        System.out.println();
        System.out.println("Books");
        System.out.println();
        for (Book b : Library.getAllBooks()) {
            System.out.println(b.getTitle());
            System.out.println(b.getCopiesAvailable());
            System.out.println(b.getAvgRating());
            for (Review r : b.getReviews()) {
                System.out.println("Review of book");
                System.out.println(r.getRating());
                System.out.println(r.getComment());
            }
        }
         


         System.out.println();
         System.out.println("Books");
         System.out.println();
         for (Category b : Library.getAllCategories()) {
             System.out.println(b.getName());
             for (String r : b.getBooksISBN()) {
                 System.out.println(r);
             }
         }

         */
        

        NavigationController.setStage(primaryStage);
        NavigationController.loadPage("/views/home.fxml");

        //NavigationController.loadPage("/views/user_template.fxml");
        //NavigationController.loadPage("/views/admin_template.fxml");

        /*
        Book b1 = new Book("book21", "author1", "publisher1",  "hey", java.time.LocalDate.now(), 3);
        Book b2 = new Book("book2222", "author2", "publisher2", "bitch", java.time.LocalDate.now(), 5);

        Library.getAllCategories().get(0).addToCategoryBooks(b1.getISBN());
        Library.getAllCategories().get(0).addToCategoryBooks(b2.getISBN());
        Library.addBook(b1);
        Library.addBook(b2);
        
        Platform.exit();
        */
        
    
    }

    @Override
    public void stop() {
        Library.saveData();
    }

    public static void main(String[] args) {
        launch(args);        
    }
   


    // dummy data generation - it was used once to create data to put in the Library
    public static void dummyDataCreation () {
        Library.setAllAdmins(new ArrayList<>());
        Library.setAllUsers(new ArrayList<>());
        Library.setAllActiveBorrows(new ArrayList<>());
        Library.setAllBooks(new ArrayList<>());
        Library.setAllCategories(new ArrayList<>());

        try { 
            // BOOKS
            Book b1 = new Book("To Kill a Mockingbird", "Harper Lee", "Penguin Random House",  "978-0-123456-78-9", java.time.LocalDate.of(2015, 7, 11), 3);
            Book b2 = new Book("Another Book by Harper Lee", "Harper Lee", "HarperCollins Publishers",  "978-1-234567-89-0", java.time.LocalDate.of(2017, 9, 15), 5);
            Book b3 = new Book("1984", "George Orwell", "Simon & Schuster",  "978-2-345678-90-1", java.time.LocalDate.of(2015, 6, 8), 7);
            Book b4 = new Book("Animal Farm", "George Orwell", "Penguin Random House",  "978-3-456789-01-2", java.time.LocalDate.of(2011, 8, 17), 2);
            Book b5 = new Book("Another Book by George Orwell", "George Orwell", "HarperCollins Publishers",  "978-4-567890-12-3", java.time.LocalDate.of(2020, 11, 22), 10);
            Book b6 = new Book("The Hobbit", "J.R.R. Tolkien", "HarperCollins Publishers",  "978-5-678901-23-4", java.time.LocalDate.of(2011, 9, 21), 0);
            Book b7 = new Book("The Lord of the Rings: The Fellowship of the Ring", "J.R.R. Tolkien", "Simon & Schuster",  "978-6-789012-34-5", java.time.LocalDate.of(2017, 7, 29), 3);
            Book b8 = new Book("The Lord of the Rings: The Two Towers", "J.R.R. Tolkien", "Simon & Schuster",  "978-7-890123-45-6", java.time.LocalDate.of(2020, 11, 11), 4);
            Book b9 = new Book("The Lord of the Rings: The Return of the King", "J.R.R. Tolkien", "Simon & Schuster",  "978-8-901234-56-7", java.time.LocalDate.of(2020, 10, 20), 5);
            Book b10 = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Penguin Random House",  "978-9-012345-67-8", java.time.LocalDate.of(2015, 6, 26), 1);
        
            Library.addBook(b10);
            Library.addBook(b9);
            Library.addBook(b8);
            Library.addBook(b7);
            Library.addBook(b6);
            Library.addBook(b5);
            Library.addBook(b4);
            Library.addBook(b3);
            Library.addBook(b2);
            Library.addBook(b1);

            // CATEGORIES
            Category c1 = new Category("Fantasy");
            Category c2 = new Category("Fiction");
            Category c3 = new Category("Mystery");
            Category c4 = new Category("Dystopian");
            
            c1.addToCategoryBooks(b6.getISBN());
            c1.addToCategoryBooks(b7.getISBN());
            c1.addToCategoryBooks(b8.getISBN());
            c1.addToCategoryBooks(b9.getISBN());
            c1.addToCategoryBooks(b10.getISBN());

            c2.addToCategoryBooks(b1.getISBN());
            c2.addToCategoryBooks(b5.getISBN());

            c3.addToCategoryBooks(b2.getISBN());

            c4.addToCategoryBooks(b4.getISBN());
            c4.addToCategoryBooks(b3.getISBN());

            Library.addCategory(c1);
            Library.addCategory(c2);
            Library.addCategory(c3);
            Library.addCategory(c4);

            //ADMINS
            Admin a = new Admin("medialab", "medialab_2024");
            
            Library.addAdmins(a);

            //USERS 
            User user1 = new User("john_doe", "password123", "John", "Doe", "ΑK323456", "john@example.com", "123 Main St", LocalDate.of(1985, 10, 15));
            User user2 = new User("jane_smith", "hello123", "Jane", "Smith", "ΑΘ987654", "jane@example.com", "456 Elm St", LocalDate.of(1990, 5, 25));
            User user3 = new User("mike_jones", "hey456", "Mike", "Jones", "ΑΩ456789", "mike@example.com", "789 Oak St", LocalDate.of(1978, 3, 8));
            User user4 = new User("emma_brown", "pass987", "Emma", "Brown", "AE765432", "emma@example.com", "321 Pine St", LocalDate.of(1987, 8, 25));
            User user5 = new User("michael_smith", "newpass", "Michael", "Smith", "AK543210", "michael@example.com", "567 Maple St", LocalDate.of(1995, 12, 8));

            //REVIEW CREATION - so the data is consistent
            // user 1 Reviews
            user1.borrowBook(b1);
            user1.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user1.borrowBook(b2);
            user1.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user1.borrowBook(b3);
            user1.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user1.borrowBook(b4);
            user1.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user1.borrowBook(b5);
            user1.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user1.borrowBook(b6);
            user1.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            // user 2 Reviews
            user2.borrowBook(b1);
            user2.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user2.borrowBook(b2);
            user2.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user2.borrowBook(b3);
            user2.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user2.borrowBook(b4);
            user2.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user2.borrowBook(b5);
            user2.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user2.borrowBook(b6);
            user2.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            // user 3 Reviews
            user3.borrowBook(b1);
            user3.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user3.borrowBook(b2);
            user3.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user3.borrowBook(b3);
            user3.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user3.borrowBook(b4);
            user3.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user3.borrowBook(b5);
            user3.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user3.borrowBook(b6);
            user3.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            // user 4 Reviews
            user4.borrowBook(b1);
            user4.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user4.borrowBook(b2);
            user4.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user4.borrowBook(b3);
            user4.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user4.borrowBook(b4);
            user4.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user4.borrowBook(b5);
            user4.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user4.borrowBook(b6);
            user4.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            // user 5 Reviews
            user5.borrowBook(b1);
            user5.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user5.borrowBook(b2);
            user5.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user5.borrowBook(b3);
            user5.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user5.borrowBook(b4);
            user5.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user5.borrowBook(b5);
            user5.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user5.borrowBook(b6);
            user5.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));




            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
