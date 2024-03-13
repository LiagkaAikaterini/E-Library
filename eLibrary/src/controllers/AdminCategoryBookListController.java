package controllers;

import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Book;
import models.Library;

public class AdminCategoryBookListController {

    @FXML
    private ListView<Book> bookList;

    @FXML
    private Label category;


    public void setBookList(List<Book> bookList) {
        ObservableList<Book> studentObservableList = FXCollections.observableArrayList();

        for (Book book : Library.getAllBooks()) {
            studentObservableList.add(book);
        }

        // show top 5 books
        this.bookList.setItems(studentObservableList);
        this.bookList.setCellFactory(booklist -> new ListCellBook());
    }
    public void setCategory(String category) {
        this.category.setText(category);
    }
    



}

