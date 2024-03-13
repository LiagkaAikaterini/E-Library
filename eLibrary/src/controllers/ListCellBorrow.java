package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import models.Book;

public class ListCellBorrow extends ListCell<Book> {

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

    // button hover effect
    @FXML
    void hoverActivated(MouseEvent event) {
        // #8C7460
        Button buttonEntered = (Button) event.getSource();
        buttonEntered.setStyle("-fx-background-color: #8C7460");
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        Button buttonEntered = (Button) event.getSource();
        buttonEntered.setStyle("-fx-background-color: #B69E7A");
    }

    
    @FXML
    void goToBookDetails(MouseEvent event) {
        Book currBook = getItem();

        if (currBook != null) {
            UserBookDetailsController.setCurrBook(currBook);
            NavigationController.loadCenter("/views/user_bookDetails.fxml");
        }
        else {
            // ?????????????????????????????????????????????
        }
    }

    @FXML
    void borrowBookRequest(MouseEvent event) {
        // maybe show alert to verify borrow 
        Book currBook = getItem();

        if (currBook != null) {
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            
        }
        else {
            // ?????????????????????????????????????????????
        }
    }


    @Override
    protected void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);

        if (empty || book == null) {
            setText(null);
            setGraphic(null);
        } 
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listcell_borrow.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        
            hbox.prefWidthProperty().bind(getListView().widthProperty());

            bookcell_title.setText(book.getTitle());
            bookcell_author.setText("by " + book.getAuthor());
            bookcell_isbn.setText("ISBN: " + book.getISBN());;
            bookcell_rating.setText(
                book.getAvgRating() + "  (" + String.valueOf(book.getCopiesAvailable()) + " reviews)"
            );

            setText(null);
            setGraphic(hbox);
        }
    }
    
}