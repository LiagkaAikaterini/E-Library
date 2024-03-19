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
        
        //dummyDataCreation();

        NavigationController.setStage(primaryStage);
        NavigationController.loadPage("/views/home.fxml");
    
    }

    @Override
    public void stop() {
        Library.saveData();
    }

    public static void main(String[] args) {
        launch(args);        
    }
   


    // dummy data generation - it was used once to create data to put in the Library
    public static void dummyDataCreation() {
        Library.setAllAdmins(new ArrayList<>());
        Library.setAllUsers(new ArrayList<>());
        Library.setAllActiveBorrows(new ArrayList<>());
        Library.setAllBooks(new ArrayList<>());
        Library.setAllCategories(new ArrayList<>());

        try { 
            //ADMINS
            Admin a = new Admin("medialab", "medialab_2024");
            
            Library.addAdmins(a);

            //USERS 
            User user1 = new User("john_doe", "password123", "John", "Doe", "ΑK323456", "john@example.com", "123 Main St", LocalDate.of(1985, 10, 15));
            User user2 = new User("jane_smith", "hello123", "Jane", "Smith", "ΑΘ987654", "jane@example.com", "456 Elm St", LocalDate.of(1990, 5, 25));
            User user3 = new User("mike_jones", "hey456", "Mike", "Jones", "ΑΩ456789", "mike@example.com", "789 Oak St", LocalDate.of(1978, 3, 8));
            User user4 = new User("emma_brown", "pass987", "Emma", "Brown", "AE765432", "emma@example.com", "321 Pine St", LocalDate.of(1987, 8, 25));
            User user5 = new User("michael_smith", "newpass", "Michael", "Smith", "AK543210", "michael@example.com", "567 Maple St", LocalDate.of(1995, 12, 8));

            Library.addUsers(user1);
            Library.addUsers(user2);
            Library.addUsers(user3);
            Library.addUsers(user4);
            Library.addUsers(user5);
            
            // BOOKS
            Book b1 = new Book("To Kill a Mockingbird", "Harper Lee", "Penguin Random House",  "1-12345", java.time.LocalDate.of(2015, 7, 11), 3);
            Book b2 = new Book("Another Book by Harper Lee", "Harper Lee", "HarperCollins Publishers",  "2-12345", java.time.LocalDate.of(2017, 9, 15), 5);
            Book b3 = new Book("1984", "George Orwell", "Simon & Schuster",  "3-12345", java.time.LocalDate.of(2015, 6, 8), 7);
            Book b4 = new Book("Animal Farm", "George Orwell", "Penguin Random House",  "4-12345", java.time.LocalDate.of(2011, 8, 17), 2);
            Book b5 = new Book("Another Book by George Orwell", "George Orwell", "HarperCollins Publishers",  "5-12345", java.time.LocalDate.of(2020, 11, 22), 10);
            Book b6 = new Book("The Hobbit", "J.R.R. Tolkien", "HarperCollins Publishers",  "6-12345", java.time.LocalDate.of(2011, 9, 21), 5);
            Book b7 = new Book("The Lord of the Rings: The Fellowship of the Ring", "J.R.R. Tolkien", "Simon & Schuster",  "7-12345", java.time.LocalDate.of(2017, 7, 29), 3);
            Book b8 = new Book("The Lord of the Rings: The Two Towers", "J.R.R. Tolkien", "Simon & Schuster",  "8-12345", java.time.LocalDate.of(2020, 11, 11), 4);
            Book b9 = new Book("The Lord of the Rings: The Return of the King", "J.R.R. Tolkien", "Simon & Schuster",  "9-12345", java.time.LocalDate.of(2020, 10, 20), 0);
            Book b10 = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Penguin Random House",  "10-12345", java.time.LocalDate.of(2015, 6, 26), 1);

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
            Category c1 = new Category("fantasy");
            Category c2 = new Category("fiction");
            Category c3 = new Category("mystery");
            Category c4 = new Category("dystopian");
            
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

           
            //REVIEW CREATION - so the data is consistent

            // b1
            user1.borrowBook(b1);
            user1.reviewBook(b1, 5, "A classic tale of morality and justice.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));


            // b2
            user1.borrowBook(b2);
            user1.reviewBook(b2, 5, "Harper Lee's masterpiece. A must-read!");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user3.borrowBook(b2);
            user3.reviewBook(b2, 4, " ");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user2.borrowBook(b2);
            user2.reviewBook(b2, 2, "Not my cup of tea.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user5.borrowBook(b2);
            user5.reviewBook(b2, 1, "Terrible.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            
            // b3
            user1.borrowBook(b3);
            user1.reviewBook(b3, 3, " ");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user2.borrowBook(b3);
            user2.reviewBook(b3, 3, "An intriguing exploration of the American Dream.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));
            
            user3.borrowBook(b3);
            user3.reviewBook(b3, 1, "Confusing.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));


            // b4
            user1.borrowBook(b4);
            user1.reviewBook(b4, 3, "Clever allegory with profound implications.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));


            // b5
            user1.borrowBook(b5);
            user1.reviewBook(b5, 5, "A masterpiece of political satire.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user4.borrowBook(b5);
            user4.reviewBook(b5, 4, " ");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));


            // b6
            user5.borrowBook(b6);
            user5.reviewBook(b6, 2, "Entertaining and full of whimsy.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));
            


            // b7
            user2.borrowBook(b7);
            user2.reviewBook(b7, 4, "Epic adventure with rich world-building.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user4.borrowBook(b7);
            user4.reviewBook(b7, 3, "A bit slow-paced but beautifully written.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));


            // b8
            user1.borrowBook(b8);
            user1.reviewBook(b8, 4, " ");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user2.borrowBook(b8);
            user2.reviewBook(b8, 2, "Not as good as the first.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user3.borrowBook(b8);
            user3.reviewBook(b8, 5, "Incredible continuation of the epic saga.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            
            // b10
            user1.borrowBook(b10);
            user1.reviewBook(b10, 5, " ");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));


            user2.borrowBook(b10);
            user2.reviewBook(b10, 4, " ");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user3.borrowBook(b10);
            user3.reviewBook(b10, 5, "Really fun.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user4.borrowBook(b10);
            user4.reviewBook(b10, 4, "Captures the imagination of both young and old.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));

            user5.borrowBook(b10);
            user5.reviewBook(b10, 5, "Magical and enchanting.");
            a.terminateBorrow(Library.getAllActiveBorrows().get(0));


            //HAVE SOME ACTIVE BORROWS
            user1.borrowBook(b1);
            user1.borrowBook(b6);

            user2.borrowBook(b1);

            user3.borrowBook(b1);

            user4.borrowBook(b5);
            user4.borrowBook(b8);



            // Reviews and borrows are Made, add books to the Library
            
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
