package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import models.Library;
import models.User;


public class AdminManageUsersController implements Initializable {

    @FXML
    private ListView<User> userList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<User> observableUserList = FXCollections.observableArrayList();
        List<User> users = Library.getAllUsers();

        observableUserList.addAll(users);
        
        userList.setItems(observableUserList);
        userList.setCellFactory(userlist -> new ListCellAdminUser());   
    }

}