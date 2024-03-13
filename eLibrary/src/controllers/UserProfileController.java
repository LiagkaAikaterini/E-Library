package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
        /*  my Logged user is user - maybe unessesary!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (NavigationController.getLoggedUser().getIsAdmin()) {
            
        }
        */

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // NULL RESPONSE HANDLING

        User loggedUser = Library.getCurrUser(NavigationController.getLoggedPerson());

        username.setText(loggedUser.getUsername());
        firstname.setText(loggedUser.getFirstName());
        lastname.setText(loggedUser.getLastName());
        id.setText(loggedUser.getIdNum());
        email.setText(loggedUser.getEmail());
        address.setText(loggedUser.getAddress());
        birthday.setText(String.valueOf(loggedUser.getBirthDate()));
    }

   

}

