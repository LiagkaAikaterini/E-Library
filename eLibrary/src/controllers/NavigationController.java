package controllers;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// this class loads the pages and keeps the current stage
public class NavigationController {
    
    private static Stage stage;
    
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

    public static void loadUserPage(String fxmlFile, BorderPane mainLayout) {
        try {            
            mainLayout.setCenter(FXMLLoader.load(NavigationController.class.getResource(fxmlFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAdminPage(String fxmlFile, BorderPane mainLayout) {
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
}
