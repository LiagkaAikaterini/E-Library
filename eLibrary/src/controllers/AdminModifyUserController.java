package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());
        if (admin != null){
            String newUsername = username_input.getText().replaceAll("\\s+", "");
            admin.changeUserUsername(currUser, newUsername);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
    }

    @FXML
    void changeFirstName(MouseEvent event) {
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());
        if (admin != null){
            String newFirstname = firstaname_input.getText().replaceAll("\\s+", "");
            admin.changeUserFirstname(currUser, newFirstname);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
    }

    @FXML
    void changeLastName(MouseEvent event) {
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());
        if (admin != null){
            String newLastname = lastname_input.getText().replaceAll("\\s+", "");
            admin.changeUserLastname(currUser, newLastname);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
    }

    @FXML
    void changeID(MouseEvent event) {
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());
        if (admin != null){
            String newIdNum = id_input.getText().replaceAll("\\s+", "");
            admin.changeUserIdNum(currUser, newIdNum);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
    }

    @FXML
    void changeEmail(MouseEvent event) {
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());
        if (admin != null){
            String newEmail = email_input.getText().replaceAll("\\s+", "");
            admin.changeUserEmail(currUser, newEmail);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
    }
    @FXML
    void changeAddress(MouseEvent event) {
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());
        if (admin != null){
            String newAddress = address_input.getText().replaceAll("\\s+", " ");
            admin.changeUserAddress(currUser, newAddress);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
    }
    
    @FXML
    void changeBirthDate(MouseEvent event) {
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());
        if (admin != null){
            LocalDate newBirthday = birthday_input.getValue();
            admin.changeUserBirthday(currUser, newBirthday);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
    }

    // Delete User button - onClick handler
    // ??????????????????????????????????????????????????????????????????????????????
    // maybe add an alert that says User deleted successfully 
    @FXML
    void deleteUser(MouseEvent event) {
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());
        if (admin != null){
            admin.deleteUser(currUser);
            NavigationController.loadCenter("/views/admin_manageUsers.fxml");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currUserInfoInit();
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
