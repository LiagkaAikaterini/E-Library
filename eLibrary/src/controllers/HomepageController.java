package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import models.Book;
import models.Library;

public class HomepageController implements Initializable{

    @FXML
    private ListView<Book> list;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Book> observableBooklist = FXCollections.observableArrayList();
        List<Book> books = Library.getAllBooks();

        observableBooklist.addAll(books);
        
        list.setItems(observableBooklist);

        // set the suitable cell type based on whether we are in User or Admin mode
        if (NavigationController.getLoggedPerson().getIsAdmin()) {
            // An Admin logged in
        }
        else {
            // A simple User logged in
            list.setCellFactory(booklist -> new ListCellBorrow());
        }
        
    }

}
