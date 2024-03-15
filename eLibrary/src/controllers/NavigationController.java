package controllers;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.UserBase;

// this class loads the pages and keeps the current stage
public class NavigationController {

    private static UserBase loggedPerson;
    private static Stage stage;
    private static BorderPane mainLayout;
    

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


    public static ButtonType showAlert(AlertType alertType, String message, String reloadPagePath) {
        Alert customAlert = new Alert(alertType);
        //customAlert.setAlertType(alertType);
        //customAlert.setTitle(title);
        customAlert.setHeaderText(null);
        customAlert.setContentText(message);

        if (loggedPerson == null) { 
            // not logged in -> general pages
            customAlert.setOnCloseRequest(e -> {
                loadPage(reloadPagePath);
            });
        }
        else {
            // logged in -> i have a navigation menu template
            customAlert.setOnCloseRequest(e -> {
                loadCenter(reloadPagePath);
            });
        }

        final Optional<ButtonType> result = customAlert.showAndWait();

        return result.orElse(ButtonType.CANCEL);
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
    public static UserBase getLoggedPerson() {
        return loggedPerson;
    }
    public static void setLoggedPerson(UserBase loggedPerson) {
        NavigationController.loggedPerson = loggedPerson;
    }
}
