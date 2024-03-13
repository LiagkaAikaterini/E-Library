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

public class UserHomepageController implements Initializable{

    @FXML
    private ListView<Book> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Book> observableBooklist = FXCollections.observableArrayList();
        List<Book> books = Library.getAllBooks();

        observableBooklist.addAll(books);

        list.setItems(observableBooklist);
        list.setCellFactory(booklist -> new ListCellBorrow());
    }

}
