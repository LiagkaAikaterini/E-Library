package controllers;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Book;


public class AdminCategoryBookListController {

    @FXML
    private ListView<Book> bookList;
    @FXML
    private Label categoryName;


    public void setBookList(List<Book> allCatBooks) {
        ObservableList<Book> observableBookList = FXCollections.observableArrayList();

        observableBookList.addAll(allCatBooks);
        

        // calculate listView height according to Listcell height so that all books can be shown whith no scrollbar
        double height = observableBookList.size() * 80 + 30;
        bookList.setPrefHeight(height);

        bookList.setItems(observableBookList);
        bookList.setCellFactory(booklist -> new ListCellAdminBook());
        
    }

    public void setCategoryName(String category) {
        this.categoryName.setText(category);
    }

}

