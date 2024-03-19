package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import exceptions.InvalidUserInfoException;
import models.Admin;
import models.Library;


public class RegisterAdminController {

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

    // navigation buttons
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
        String username = username_input.getText().strip();
        String password = password_input.getText().strip();
        String passwordConfirmation = password2_input.getText().strip();
        String libraryPassword = libraryPassword_input.getText().strip(); 
        
        // if all fields are filled before pressing the sign up button
        if ( !username.isEmpty() && !password.isEmpty() && !passwordConfirmation.isEmpty() && !libraryPassword.isEmpty()) {

            // firstly chack if the library password is correct
            if (!libraryPassword.equals(Library.getPassword())) {
                NavigationController.showAlert(
                    AlertType.ERROR, 
                    "Incorrect Library Password. In order to register as admin the correct library password must be provided. Please try again.", 
                    ""
                );
                return;
            }

            // check password confirmation
            if (!password.equals(passwordConfirmation)) {
                NavigationController.showAlert(
                    AlertType.ERROR, 
                    "The Password and Confirmation Password fields do not match. Please try again.", 
                    ""
                );
                return;
            }

            try {
                Admin newAdmin = new Admin(username, password);
                Library.addAdmins(newAdmin);
                NavigationController.showAlert(
                    AlertType.INFORMATION, 
                    "Your register was successful. Please Log in.", 
                    "/views/login.fxml"
                );
            }
            catch (InvalidUserInfoException e) {
                NavigationController.showAlert(AlertType.ERROR, e.getMessage() ,"");
            }
        }
        else {
            NavigationController.showAlert(AlertType.INFORMATION, "Please fill in all the fields before you try to sign up.", "");
        }
    }

}

