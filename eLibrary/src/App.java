import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.*;

public class App extends Application {
    private static List<Admin> allAdmins;
    private static List<User> allUsers;
    private static List<Book> allBooks;
    private static List<Category> allCategories;
    private static List<Borrowed> allActiveBorrows;
    private static User logedUser;
    private static boolean adminMode;

    
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

    public static User getLogedUser() {
        return logedUser;
    }
    public static void setLogedUser(User logedUser) {
        App.logedUser = logedUser;
    }

    public static boolean getAdminMode() {
        return adminMode;
    }
    public static void setAdminMode(boolean adminMode) {
        App.adminMode = adminMode;
    }

    @Override
    public void init(){}

    @Override
    public void start(Stage primaryStage) {}

    @Override
    public void stop(){}
    /* 
    private static List<Book> libary_books;
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
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
    public static void main(String[] args) {
        launch(args);
    }
    
}
