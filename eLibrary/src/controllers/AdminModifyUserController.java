package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import exceptions.InvalidDateException;
import exceptions.InvalidUserInfoException;
import exceptions.UserNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import models.Admin;
import models.Library;
import models.User;


public class AdminModifyUserController implements Initializable {

    private static User currUser;
    private Admin admin;

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

    @FXML
    private TextField username_input;
    @FXML
    private TextField firstaname_input;
    @FXML
    private TextField lastname_input;
    @FXML
    private TextField id_input;
    @FXML
    private TextField email_input;
    @FXML
    private TextField address_input;
    @FXML
    private DatePicker birthday_input;

    @FXML
    private Button username_btn;
    @FXML
    private Button firstname_btn;
    @FXML
    private Button lastname_btn;
    @FXML
    private Button id_btn;
    @FXML
    private Button email_btn;
    @FXML
    private Button address_btn;
    @FXML
    private Button birthday_btn;

    @FXML
    private Button deleteUser_btn;

    //hover effect for Buttons
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


    // admin changes user info - buttons OnClick handlers
    @FXML
    void changeUsername(MouseEvent event) {
        try { 
            String newUsername = username_input.getText().replaceAll("\\s+", "");
            admin.changeUserUsername(currUser, newUsername);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
        catch (InvalidUserInfoException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
        }
        
    }

    @FXML
    void changeFirstName(MouseEvent event) {
  
        String newFirstname = firstaname_input.getText().replaceAll("\\s+", " ");
        admin.changeUserFirstname(currUser, newFirstname);
        NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        
    }

    @FXML
    void changeLastName(MouseEvent event) {
    
        String newLastname = lastname_input.getText().replaceAll("\\s+", " ");
        admin.changeUserLastname(currUser, newLastname);
        NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        
    }

    @FXML
    void changeID(MouseEvent event) {
        try { 
            String newIdNum = id_input.getText().replaceAll("\\s+", "");
            admin.changeUserIdNum(currUser, newIdNum);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
        catch (InvalidUserInfoException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
        }
        
    }

    @FXML
    void changeEmail(MouseEvent event) {
        try { 
            String newEmail = email_input.getText().replaceAll("\\s+", "");
            admin.changeUserEmail(currUser, newEmail);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
        catch (InvalidUserInfoException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
        }
    }

    @FXML
    void changeAddress(MouseEvent event) {
        String newAddress = address_input.getText().replaceAll("\\s+", " ");
        admin.changeUserAddress(currUser, newAddress);
        NavigationController.loadCenter("/views/admin_modifyUser.fxml");
    }
    
    @FXML
    void changeBirthDate(MouseEvent event) {
        try { 
            LocalDate newBirthday = birthday_input.getValue();
            admin.changeUserBirthday(currUser, newBirthday);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
        catch (InvalidDateException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
        }
    }

    @FXML
    void deleteUser(MouseEvent event) {
        
            admin.deleteUser(currUser);
            NavigationController.loadCenter("/views/admin_manageUsers.fxml");
        
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (currUser == null) {
                // if currUser null the page cannot be initialized - no need for null check in the other functions - button handlers 
                throw new NullPointerException();
            }
            // we keep admin in the page because it is used all the button handlers to keep the code simpler
            this.admin = Library.findAdmin(NavigationController.getLoggedPerson().getUsername());
            // initialize page info
            currUserInfoInit();
        }
        catch (UserNotFoundException e) {
            // admin not found in the library by findAdmin, log out automatically and tell admin to log in again.
            NavigationController.setMainLayout(null);
            NavigationController.setLoggedPerson(null);
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "/views/login.fxml");
        }
        catch (NullPointerException e) {
            NavigationController.showAlert(AlertType.ERROR, "Something went wrong. This modify user page could not be opened.", "/views/admin_manageUsers.fxml");
        }
    }


    private void currUserInfoInit() {
        username.setText(currUser.getUsername());
        firstname.setText(currUser.getFirstName());
        lastname.setText(currUser.getLastName());
        id.setText(currUser.getIdNum());
        email.setText(currUser.getEmail());
        address.setText(currUser.getAddress());
        birthday.setText(String.valueOf(currUser.getBirthDate()));
    }

    public static User getCurrUser() {
        return currUser;
    }
    public static void setCurrUser(User currUser) {
        AdminModifyUserController.currUser = currUser;
    }
}
