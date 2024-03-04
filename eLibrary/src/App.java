import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.*;

public class App extends Application {
    private static List<Admin> allAdmins;
    private static List<User> allUsers;
    private static List<Book> allBooks;
    private static List<Category> allCategories;
    private static List<Borrowed> allActiveBorrows;
    private static UserBase logedUser;

    @Override
    public void init() {

        // initialize empty lists to pass to the deserializer
        allAdmins = new ArrayList<>();
        allUsers = new ArrayList<>(); 
        allBooks = new ArrayList<>(); 
        allCategories = new ArrayList<>(); 
        allActiveBorrows = new ArrayList<>(); 
        
        // deserialize data from each data file and populate the corresponding list
        allAdmins = DataManagement.deserialize("src/medialab/admins.ser");
        allUsers = DataManagement.deserialize("src/medialab/users.ser");
        allBooks = DataManagement.deserialize("src/medialab/books.ser");
        allCategories = DataManagement.deserialize("src/medialab/categories.ser");
        allActiveBorrows = DataManagement.deserialize("src/medialab/borrows.ser");
         
    }

    @Override
    public void start(Stage primaryStage) {
                  
        //Platform.exit();
    }

    @Override
    public void stop() {
        // serialize data from each data file and populate the corresponding list
        DataManagement.serialize("src/medialab/admins.ser", allAdmins);
        DataManagement.serialize("src/medialab/users.ser", allUsers);
        DataManagement.serialize("src/medialab/books.ser", allBooks);
        DataManagement.serialize("src/medialab/categories.ser", allCategories);
        DataManagement.serialize("src/medialab/borrows.ser", allActiveBorrows);
    }

    public static void main(String[] args) {
        launch(args);        
    }

    
    public static List<Admin> getAllAdmins() {
        return allAdmins;
    }
    public static void addAdmins(Admin admin) {
        App.allAdmins.add(admin);
    }
    public static void removeAdmins(Admin admin) {
        App.allAdmins.remove(admin);
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }
    public static void addUsers(User user) {
        App.allUsers.add(user);
    }
    public static void removeUser(User user) {
        App.allUsers.remove(user);
    }

    public static List<Book> getAllBooks() {
        return allBooks;
    }
    public static void addBook(Book book) {
        App.allBooks.add(book);
    }
    public static void removeBook(Book book) {
        App.allBooks.remove(book);
    }

    public static List<Category> getAllCategories() {
        return allCategories;
    }
    public static void addCategory(Category category) {
        App.allCategories.add(category);
    }
    public static void removeCategory(Category category) {
        App.allCategories.remove(category);
    }


    public static List<Borrowed> getAllActiveBorrows() {
        return allActiveBorrows;
    }
    public static void addActiveBorrow(Borrowed borrow) {
        App.allActiveBorrows.add(borrow);
    }
    public static void removeActiveBorrow(Borrowed borrow) {
        App.allActiveBorrows.remove(borrow);
    }

    // Retrieve User or Admin seperately ??????????????????????????????????????
    public static UserBase getLogedUser() {
        return logedUser;
    }
    public static void setLogedUser(UserBase logedUser) {
        App.logedUser = logedUser;
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
