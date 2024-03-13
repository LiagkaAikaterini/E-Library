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
import models.Borrowed;
import models.Library;

public class ListCellReviewBook extends ListCell<Borrowed> {
    
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
    private Label bookcell_borrowDate;
    @FXML
    private Label bookcell_returnDate;

    @FXML
    private Button details_btn;
    @FXML
    private Button review_btn;

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
    void goToReviewBook(MouseEvent event) {
        Borrowed borrow = getItem();
        Book currBook = Library.findBook(borrow.getBookISBN());
        
        if (currBook != null) {
            //UserBookDetailsController.setCurrBook(currBook);
            NavigationController.loadCenter("/views/user_reviewBook.fxml");
        }
        else {
            // ?????????????????????????????????????????????
        }
    }
    

    @FXML
    void goToBookDetails(MouseEvent event) {
        Borrowed borrow = getItem();
        Book currBook = Library.findBook(borrow.getBookISBN());

        if (currBook != null) {
            UserBookDetailsController.setCurrBook(currBook);
            NavigationController.loadCenter("/views/user_bookDetails.fxml");
        }
        else {
            // ?????????????????????????????????????????????
        }
    }


    @Override
    protected void updateItem(Borrowed borrow, boolean empty) {
        super.updateItem(borrow, empty);

        if (empty || borrow == null) {
            setText(null);
            setGraphic(null);
        } 
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listcell_reviewBook.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }

            hbox.prefWidthProperty().bind(getListView().widthProperty());

            Book book = Library.findBook(borrow.getBookISBN());

            bookcell_title.setText(book.getTitle());
            bookcell_author.setText("by " + book.getAuthor());
            bookcell_isbn.setText("ISBN: " + book.getISBN());;
            bookcell_rating.setText(
                book.getAvgRating() + "  (" + String.valueOf(book.getCopiesAvailable()) + " reviews)"
            );
            bookcell_borrowDate.setText(String.valueOf(borrow.getBorrowingDate()));
            bookcell_returnDate.setText(String.valueOf(borrow.getReturnDate()));

            setText(null);
            setGraphic(hbox);
        }
    }
    
}