import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import controllers.NavigationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import models.*;

public class App extends Application  {
    public Library libraryInstance;

    // MAYBE NOT IN INIT BUT IN START!!!!
    @Override
    public void init() {
        libraryInstance = new Library();
    }

    @Override
    public void start(Stage primaryStage) {
        /*
        Library.setAllAdmins(new ArrayList<>());
        Library.setAllUsers(new ArrayList<>());
        Library.setAllActiveBorrows(new ArrayList<>());
        Library.setAllBooks(new ArrayList<>());
        Library.setAllCategories(new ArrayList<>());
        
        Admin a = new Admin("admin1", "STYLESHEET_CASPIAN");
        Book b1 = new Book("book1", "author1", "publisher1", "sumaryy", "111", java.time.LocalDate.now(), 3);
        Book b2 = new Book("book2", "author2", "publisher2", "sumaryy", "222", java.time.LocalDate.now(), 5);
        User user = new User("username", "STYLESHEET_CASPIAN", "STYLESHEET_CASPIAN", "STYLESHEET_CASPIAN", "STYLESHEET_CASPIAN", "STYLESHEET_MODENA", "STYLESHEET_CASPIAN", java.time.LocalDate.now());
        Library.addAdmins(a);
        Library.addUsers(user);
        Library.addBook(b2);
        Library.addBook(b1);

        user.borrowBook(b2);
         */

        
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
         */

        
        NavigationController.setStage(primaryStage);
        NavigationController.loadPage("/views/user_template.fxml");

        
        
        //Platform.exit();
    }

    @Override
    public void stop() {
        libraryInstance.saveData();
    }

    public static void main(String[] args) {
        launch(args);        
    }
   
    /* 
    private static List<Book> libary_books;
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
    

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    */
    
}
