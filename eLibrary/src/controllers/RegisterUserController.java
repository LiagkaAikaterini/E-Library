package controllers;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import exceptions.InvalidDateException;
import exceptions.InvalidUserInfoException;
import models.Library;
import models.User;


public class RegisterUserController {
    
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button home_btn;
    @FXML
    private Button loginForm_btn;
    
    @FXML
    private TextField username_input;
    @FXML
    private TextField address_input;
    @FXML
    private DatePicker birthday_input;
    @FXML
    private TextField email_input;
    @FXML
    private TextField firstname_input;
    @FXML
    private TextField id_input;
    @FXML
    private TextField lastname_input;
    @FXML
    private PasswordField password2_input;
    @FXML
    private PasswordField password_input;

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
        String username = username_input.getText().strip();
        String password = password_input.getText().strip();
        String passwordConfirmation = password2_input.getText().strip();
        String firstname = firstname_input.getText().replaceAll("\\s+", " ").strip();
        String lastname = lastname_input.getText().replaceAll("\\s+", " ").strip();
        String idNum = id_input.getText().replaceAll("\\s+", "");
        String email = email_input.getText().strip();
        String address = address_input.getText().replaceAll("\\s+", " ").strip();
        LocalDate date = birthday_input.getValue();

        // if all text fields are filled
        if (!username.isEmpty() && !password.isEmpty() && !passwordConfirmation.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !idNum.isEmpty() && !email.isEmpty() && !address.isEmpty() && date != null ) {
            
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
                User newUser = new User(username, password, firstname, lastname, idNum, email, address, date);
                Library.addUsers(newUser);
                NavigationController.showAlert(
                    AlertType.INFORMATION, 
                    "Your register was successful. Please Log in.", 
                    "/views/login.fxml"
                );
            }
            catch (InvalidUserInfoException e) {
                NavigationController.showAlert(AlertType.ERROR, e.getMessage() ,"");
            }
            catch (InvalidDateException e) {
                NavigationController.showAlert(AlertType.ERROR, e.getMessage() ,"");
            }
        }
        else {
            NavigationController.showAlert(AlertType.INFORMATION, "Please fill in all the fields before you try to sign up.", "");
        }


    }

}
