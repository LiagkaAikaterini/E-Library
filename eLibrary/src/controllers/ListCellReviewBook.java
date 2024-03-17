package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import exceptions.NotFoundException;
import models.Book;
import models.Borrowed;
import models.Library;
import models.User;


public class ListCellReviewBook extends ListCell<Borrowed> {

    private Book currBook;
    
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
        // buttons shown only for when currBook != null
        UserReviewBookController.setCurrBook(currBook);
        NavigationController.loadCenter("/views/user_reviewBook.fxml");
    }
    

    @FXML
    void goToBookDetails(MouseEvent event) {
        // buttons shown only for when currBook != null
        UserBookDetailsController.setCurrBook(currBook);
        NavigationController.loadCenter("/views/user_bookDetails.fxml");
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

            try {
                // we keep the Book object of the borrow in a field currBook as we need it for the button handling
                this.currBook = Library.findBook(borrow.getBookISBN());

                bookcell_title.setText(currBook.getTitle());
                bookcell_author.setText("by " + currBook.getAuthor());
                bookcell_isbn.setText("ISBN: " + currBook.getISBN());;
                bookcell_rating.setText(
                    currBook.getAvgRating() + "  (" + String.valueOf(currBook.getReviews().size()) + " user reviews)"
                );
                bookcell_borrowDate.setText(String.valueOf(borrow.getBorrowingDate()));
                bookcell_returnDate.setText(String.valueOf(borrow.getReturnDate()));

                setText(null);
                setGraphic(hbox);
            }
            catch (NotFoundException e) {
                // this will most likely never been thrown as we make sure our data is consistent in the model package
                // book of the borrow not found in the library - inconsistent data: the borrow must have been removed already
                
                // fistly we fix all the possible inconsistent data of the Library (deleted book dependencies)
                for (Borrowed bor : Library.getAllActiveBorrows()) {
                    if( (bor.getBookISBN()).equals(borrow.getBookISBN()) ) {
                        Library.removeActiveBorrow(bor);
                    }
                }
                for (User user : Library.getAllUsers()) {
                    user.removeBorrowHistory(borrow.getBookISBN());
                }
    
                // then we set empty cell
                setText(null);
                setGraphic(null);
            }
        }
    }
    
    public Book getCurrBook() {
        return this.currBook;
    }
}