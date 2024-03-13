package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import models.Book;

public class ListCellBook extends ListCell<Book> {
    
    @FXML
    private VBox vbox;

    @FXML
    private Label bookcell_title;
    @FXML
    private Label bookcell_author;
    @FXML
    private Label bookcell_isbn;
    @FXML
    private Label bookcell_rating;


    @Override
    protected void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);

        if (empty || book == null) {
            setText(null);
            setGraphic(null);
        } 
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listcell_book.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }

            vbox.prefWidthProperty().bind(getListView().widthProperty());

            bookcell_title.setText(book.getTitle());
            bookcell_author.setText("by " + book.getAuthor());
            bookcell_isbn.setText("ISBN: " + book.getISBN());;
            bookcell_rating.setText(book.getAvgRating() + "  (" + String.valueOf(book.getCopiesAvailable()) + " reviews)");;

            setText(null);
            setGraphic(vbox);
        }
    }

}