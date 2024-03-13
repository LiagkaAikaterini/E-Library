package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Book;

public class SearchResultController implements Initializable {

    private static List<Book> result;

    @FXML
    private ListView<Book> searchResultList;

    @FXML
    private Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // populate listView with all the search result books  
        ObservableList<Book> observableBooklist = FXCollections.observableArrayList();

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //  handling if result == null
        //
        observableBooklist.addAll(result);
        searchResultList.setItems(observableBooklist);

        // set the suitable cell type based on whether we are in User or Admin mode
        if (NavigationController.getLoggedPerson().getIsAdmin()) {
            // An Admin logged in
        }
        else {
            // A simple User logged in
            searchResultList.setCellFactory(booklist -> new ListCellBorrow());
        }
    }

    public static List<Book> getResult() {
        return result;
    }
    public static void setResult(List<Book> result) {
        SearchResultController.result = result;
    }
    

}