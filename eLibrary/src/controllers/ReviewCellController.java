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
import javafx.scene.layout.VBox;

import models.Book;

public class ReviewCellController extends ListCell<Book> implements Initializable {
    private FXMLLoader loader;

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

    @FXML
    private Button details_btn;
    @FXML
    private Button review_btn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        details_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Book currBook = getItem();

                if (currBook != null) {
                    NavigationController.setBook(currBook);
                    NavigationController.loadCenter("/views/user_viewBookPage.fxml");
                }
            }
        });

        review_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Book currBook = getItem();

                if (currBook != null) {
                    NavigationController.setBook(currBook);
                    NavigationController.loadCenter("/views/user_reviewBook.fxml");
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
                loader = new FXMLLoader(getClass().getResource("/views/listcell_review.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            bookcell_title.setText(book.getTitle());
            bookcell_author.setText("by " + book.getAuthor());
            bookcell_isbn.setText("ISBN: " + book.getISBN());;
            bookcell_rating.setText(book.getAvgRating() + "  (" + String.valueOf(book.getCopiesAvailable()) + " reviews)");

            setText(null);
            setGraphic(vbox);
        }

    }


}
