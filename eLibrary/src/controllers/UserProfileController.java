package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import exceptions.UserNotFoundException;
import models.Library;
import models.User;


public class UserProfileController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label firstname;
    @FXML
    private Label lastname;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label birthday;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            User loggedUser = Library.findUser(NavigationController.getLoggedPerson().getUsername());

            username.setText(loggedUser.getUsername());
            firstname.setText(loggedUser.getFirstName());
            lastname.setText(loggedUser.getLastName());
            id.setText(loggedUser.getIdNum());
            email.setText(loggedUser.getEmail());
            address.setText(loggedUser.getAddress());
            birthday.setText(String.valueOf(loggedUser.getBirthDate()));   
        }
        catch (UserNotFoundException e) {
            // user not found in the library by findAdmin, log out automatically and tell admin to log in again.
            NavigationController.setMainLayout(null);
            NavigationController.setLoggedPerson(null);
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "/views/login.fxml");
        }
    }

}

