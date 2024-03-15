package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import models.Library;
import models.UserBase;

public class LoginController implements Initializable {

    @FXML
    private Button home_btn;
    @FXML
    private Button login_btn;

    @FXML
    private PasswordField password_input;
    @FXML
    private TextField username_input;
    
    @FXML
    private Button signupForm_btn;

    // button hover effect
    @FXML
    void hoverActivated(MouseEvent event) {
        Button buttonEntered = (Button) event.getSource();
        buttonEntered.setBlendMode(BlendMode.MULTIPLY);
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        Button buttonEntered = (Button) event.getSource();
        buttonEntered.setBlendMode(BlendMode.SRC_OVER);
    }

    @FXML
    void goToHome(MouseEvent event) {
        NavigationController.loadPage("/views/home.fxml");
    }

    @FXML
    void goToRegister(MouseEvent event) {
        NavigationController.loadPage("/views/register.fxml");
    }

    @FXML
    void login(MouseEvent event) {
        String username = username_input.getText();
        String password = password_input.getText();

        UserBase existingUser = Library.authenticateUser(username, password);

        if (existingUser == null) {
            NavigationController.showAlert(AlertType.ERROR, "User does not exist", "/views/login.fxml");
        }
        else {
            NavigationController.setLoggedPerson(existingUser);
            
            if (existingUser.getIsAdmin()) {
                NavigationController.loadPage("/views/admin_template.fxml");
            }
            else {
                NavigationController.loadPage("/views/user_template.fxml");
            }
        }
    }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
