package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import models.Library;

public class RegisterAdminController implements Initializable{

    @FXML
    private Button home_btn;
    @FXML
    private Button loginForm_btn;

    @FXML
    private PasswordField libraryPassword_input;
    @FXML
    private PasswordField password2_input;
    @FXML
    private PasswordField password_input;
    @FXML
    private TextField username_input;
    
    @FXML
    private Button signup_btn;

    // hover effect for buttons
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
    void goToLogin(MouseEvent event) {
        NavigationController.loadPage("/views/login.fxml");
    }

    @FXML
    void signup(MouseEvent event) {
        String username = username_input.getText();
        String password = password_input.getText();
        String passwordConfirmation = password2_input.getText();
        String libraryPassword = libraryPassword_input.getText();

        // check username 
        

        // check password
        if (!password.equals(passwordConfirmation)) {
            NavigationController.showAlert(
                AlertType.ERROR, 
                "The password", 
                "/views/register_admin.fxml"
            );
            return;
        }

    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }



}

