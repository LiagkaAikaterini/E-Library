import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Pair;

import models.*;

import java.util.*;

public class App extends Application {

    @Override
    public void init() {
        // LOGIN LOGIC TO RETRIEVE CURRENT USER
        UserBase currUser = new Admin("", "");

        Library.retrieveData(currUser);
    }

    @Override
    public void start(Stage primaryStage) {
        //Platform.exit();
    }

    @Override
    public void stop() {
        Library.saveData();
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
