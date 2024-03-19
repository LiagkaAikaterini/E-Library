package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import exceptions.UserNotFoundException;
import models.Library;
import models.UserBase;


public class LoginController {

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

    // navigation button handlers
    @FXML
    void goToHome(MouseEvent event) {
        NavigationController.loadPage("/views/home.fxml");
    }
    @FXML
    void goToRegister(MouseEvent event) {
        NavigationController.loadPage("/views/register.fxml");
    }

    // login handler
    @FXML
    void login(MouseEvent event) {
        String username = username_input.getText().strip();
        String password = password_input.getText().strip();

        try {
            UserBase existingUser = Library.authenticateUser(username, password);
            NavigationController.setLoggedPerson(existingUser);
            
            if (existingUser.getIsAdmin()) {
                NavigationController.loadPage("/views/admin_template.fxml");
            }
            else {
                NavigationController.loadPage("/views/user_template.fxml");
            }
        
        }
        catch (UserNotFoundException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "/views/login.fxml");
        }
    }

}
