import javafx.application.Application;
import javafx.application.Platform;
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
        // LOGIN LOGIC TO RETRIEVE CURRENT USER
        Platform.exit();
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
