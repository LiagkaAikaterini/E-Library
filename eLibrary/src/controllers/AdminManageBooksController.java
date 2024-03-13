package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Book;
import models.Category;
import models.Library;

public class AdminManageBooksController implements Initializable{
    public static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        AdminManageBooksController.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        VBox vbox = new VBox();

        for (Book cat : Library.getAllBooks()) {    
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin_manageBooks.fxml"));
                AnchorPane anchorPane = loader.load();
                AdminCategoryBookListController contr = loader.getController();
                contr.setCategory(cat.getTitle());
                contr.setBookList(Library.getAllBooks());
                vbox.getChildren().add(anchorPane);
                vbox.getChildren().add(anchorPane); // Add AnchorPane to the VBox
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        ScrollPane scrollPane = new ScrollPane(vbox);
        Scene scene = new Scene(scrollPane, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Dynamic AnchorPanes in ScrollPane");
        primaryStage.show();
        
    }
}
