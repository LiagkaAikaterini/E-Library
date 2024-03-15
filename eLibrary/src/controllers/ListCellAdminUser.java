package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Admin;
import models.Library;
import models.User;


public class ListCellAdminUser extends ListCell<User> {
    
    @FXML
    private HBox hbox;

    @FXML
    private Label username;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;

    @FXML
    private Button modifyUser_btn;
    @FXML
    private Button deleteUser_btn;


    // button hover effect
    @FXML
    void hoverActivated(MouseEvent event) {
        modifyUser_btn.setStyle("-fx-background-color: #8C7460");
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        modifyUser_btn.setStyle("-fx-background-color: #B69E7A");
    }
    @FXML
    void hoverActivatedDelete(MouseEvent event) {
        deleteUser_btn.setStyle("-fx-background-color: #CC0000");
    }
    @FXML
    void hoverDeactivatedDelete(MouseEvent event) {
        deleteUser_btn.setStyle("-fx-background-color: #E74C3C");
    }


    @FXML
    void goToModifyUser(MouseEvent event) {
        User currUser = getItem();

        if (currUser != null) {
            AdminModifyUserController.setCurrUser(currUser);
            NavigationController.loadCenter("/views/admin_modifyUser.fxml");
        }
    }

    @FXML
    void deleteUser(MouseEvent event) {
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());
        User currCellUser = getItem();

        if (admin != null){
            admin.deleteUser(currCellUser);
            NavigationController.loadCenter("/views/admin_manageUsers.fxml");
        }
    }


    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } 
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listcell_adminUser.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        
            hbox.prefWidthProperty().bind(getListView().widthProperty());

            username.setText(user.getUsername());
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            
            setText(null);
            setGraphic(hbox);
        }
    }
    
}