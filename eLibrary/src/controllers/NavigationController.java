package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Book;

// this class loads the pages and keeps the current stage
public class NavigationController {
    
    private static Stage stage;
    private static BorderPane mainLayout;
    private static Book book;
    

    public static void loadPage(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(NavigationController.class.getResource(fxmlFile));            
            Scene scene = new Scene(root, 800, 550);
            stage.setTitle("Medialab");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadCenter(String fxmlFile) {
        try {            
            mainLayout.setCenter(FXMLLoader.load(NavigationController.class.getResource(fxmlFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stage getStage() {
        return stage;
    }
    public static void setStage(Stage stage) {
        NavigationController.stage = stage;
    }
    public static BorderPane getMainLayout() {
        return mainLayout;
    }
    public static void setMainLayout(BorderPane mainLayout) {
        NavigationController.mainLayout = mainLayout;
    }
    public static Book getBook() {
        return book;
    }
    public static void setBook(Book book) {
        NavigationController.book = book;
    }
}
