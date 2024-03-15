package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import models.Borrowed;
import models.Library;

public class AdminManageBorrowsController implements Initializable {

    @FXML
    private ListView<Borrowed> borrowsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Borrowed> observableBooklist = FXCollections.observableArrayList();
        List<Borrowed> activeBorrows = Library.getAllActiveBorrows();

        observableBooklist.addAll(activeBorrows);
        
        borrowsList.setItems(observableBooklist);
        borrowsList.setCellFactory(booklist -> new ListCellAdminBorrow());
    }

}
