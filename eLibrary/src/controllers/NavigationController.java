package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import models.Book;
import models.Library;
import models.UserBase;

// this class loads the pages and keeps the current stage
public class NavigationController {

    private static UserBase loggedPerson = Library.getAllUsers().get(0);
    private static Stage stage;
    private static BorderPane mainLayout;
    private static Book currBook;
    

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
    public static Book getCurrBook() {
        return currBook;
    }
    public static void setCurrBook(Book currBook) {
        NavigationController.currBook = currBook;
    }
    public static UserBase getLoggedPerson() {
        return loggedPerson;
    }
    public static void setLoggedPerson(UserBase loggedPerson) {
        NavigationController.loggedPerson = loggedPerson;
    }
}
