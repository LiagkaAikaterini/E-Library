package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import models.Book;

public class ListCellBorrow extends ListCell<Book> implements Initializable {
    private FXMLLoader loader;

    @FXML
    private HBox hbox;

    @FXML
    private Label bookcell_title;
    @FXML
    private Label bookcell_author;
    @FXML
    private Label bookcell_isbn;
    @FXML
    private Label bookcell_rating;

    @FXML
    private Button borrow_btn;
    @FXML
    private Button details_btn;

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        details_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Book currBook = getItem();

                if (currBook != null) {
                    NavigationController.setBook(currBook);
                    NavigationController.loadCenter("/views/user_bookDetails.fxml");
                }
            }
        });

        borrow_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Book currBook = getItem();

                if (currBook != null) {
                    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    
                }
            }
        });
    }

    @Override
    protected void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);

        if(empty || book == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/views/listcell_borrow.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            hbox.prefWidthProperty().bind(getListView().widthProperty());

            bookcell_title.setText(book.getTitle());
            bookcell_author.setText("by " + book.getAuthor());
            bookcell_isbn.setText("ISBN: " + book.getISBN());;
            bookcell_rating.setText(book.getAvgRating() + "  (" + String.valueOf(book.getCopiesAvailable()) + " reviews)");

            setText(null);
            setGraphic(hbox);
        }

    }

}
