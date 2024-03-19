package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import exceptions.NotFoundException;
import exceptions.ReviewException;
import exceptions.UserNotFoundException;
import models.Book;
import models.Category;
import models.Library;
import models.User;


public class UserReviewBookController implements Initializable {

    private static Book currBook;

    @FXML
    private Label title;
    @FXML
    private Label category;
    @FXML
    private Label author;
    @FXML
    private Label publisher;
    @FXML
    private Label year;
    @FXML
    private Label isbn;
    @FXML
    private Label successLabel;

    @FXML
    private TextArea comment_input;

    @FXML
    private ToggleGroup rating_input;

    @FXML
    private Button submitReview_btn;

    //button hover effect 
    @FXML
    void hoverActivated(MouseEvent event) {
        submitReview_btn.setBlendMode(BlendMode.MULTIPLY);
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        submitReview_btn.setBlendMode(BlendMode.SRC_OVER);
    }


    @FXML
    void submitReview(MouseEvent event) {
        try {
            User currUser = Library.findUser(NavigationController.getLoggedPerson().getUsername());
            // get input and replace whitespace characters (space, tab, newline) with a single space
            String comment = comment_input.getText().replaceAll("\\s+", " ").strip();

            if (comment.isEmpty()) {
                comment = " ";
            }

            ToggleButton chosenButton = (ToggleButton) rating_input.getSelectedToggle();
            
            if (chosenButton == null) {
                NavigationController.showAlert(AlertType.ERROR, "You cannot submit review without rating. Please add a rating.", "");
                return;
            }

            String ratingText = chosenButton.getText();

            switch ( ratingText ) {
                case "1":
                    currUser.reviewBook(currBook, 1, comment);
                    UserBookDetailsController.setCurrBook(currBook);
                    NavigationController.showAlert(AlertType.INFORMATION, "Review Submitted Successfully","/views/user_bookDetails.fxml");
                    break;
                case "2":
                    currUser.reviewBook(currBook, 2, comment);
                    UserBookDetailsController.setCurrBook(currBook);
                    NavigationController.showAlert(AlertType.INFORMATION, "Review Submitted Successfully","/views/user_bookDetails.fxml");
                    break;
                case "3":
                    currUser.reviewBook(currBook, 3, comment);
                    UserBookDetailsController.setCurrBook(currBook);
                    NavigationController.showAlert(AlertType.INFORMATION, "Review Submitted Successfully","/views/user_bookDetails.fxml");
                    break;
                case "4":
                    currUser.reviewBook(currBook, 4, comment);
                    UserBookDetailsController.setCurrBook(currBook);
                    NavigationController.showAlert(AlertType.INFORMATION, "Review Submitted Successfully","/views/user_bookDetails.fxml");
                    break;
                case "5":
                    currUser.reviewBook(currBook, 5, comment);
                    UserBookDetailsController.setCurrBook(currBook);
                    NavigationController.showAlert(AlertType.INFORMATION, "Review Submitted Successfully","/views/user_bookDetails.fxml");
                    break;
                default:
                    NavigationController.showAlert(AlertType.ERROR, "You cannot submit review without rating. Please add a rating.", "");
                    break;
            }
        }
        catch (UserNotFoundException e) {
            // user not found in the library by findAdmin, log out automatically and tell admin to log in again.
            NavigationController.setMainLayout(null);
            NavigationController.setLoggedPerson(null);
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "/views/login.fxml");
        }
        catch (ReviewException e) {
            // this will never been thrown as we handle the rating with the switch above and a 1-5 range toggleGroup
            // so we add only acceptable ratings 1-5 and otherwise we show an alert to the user
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (currBook == null) {
                // if currBook null the page cannot be initialized - no need for null check in the other functions - handlers 
                throw new NullPointerException();
            }

            bookInfoDisplay();
            bookCategoryDisplay();
        }
        catch (NullPointerException e) {
            NavigationController.showAlert(AlertType.ERROR, "Something went wrong. This review book page could not be opened.", "/views/user_borrowHistory.fxml");
        }
        
    }

    private void bookInfoDisplay() {
        title.setText(currBook.getTitle());
        isbn.setText(currBook.getISBN());
        author.setText(currBook.getAuthor());
        publisher.setText(currBook.getPublisher());
        year.setText(String.valueOf(currBook.getPublicationYear()));
    }

    private void bookCategoryDisplay() {
        try {
            Category cat = Library.categoryOfBook(currBook.getISBN());
            String catName = cat.getName();
            category.setText(catName);
        }
        catch (NotFoundException e) {
            // category not found
            category.setText(null);
        }
    }

    public static Book getCurrBook() {
        return currBook;
    }
    public static void setCurrBook(Book currBook) {
        UserReviewBookController.currBook = currBook;
    }

}