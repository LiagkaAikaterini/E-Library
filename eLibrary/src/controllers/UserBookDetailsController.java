package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import exceptions.BorrowLimitException;
import exceptions.NoCopiesAvailableException;
import exceptions.NotFoundException;
import exceptions.UserNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import models.Book;
import models.Category;
import models.Library;
import models.User;
import models.Book.Review;

public class UserBookDetailsController implements Initializable {
    
    private static Book currBook;

    @FXML
    private Label title;
    @FXML
    private Label avgRating;
    @FXML
    private Label numUsersRated;
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
    private Label copies;

    @FXML
    private ListView<Review> reviewList;
    
    @FXML
    private Button borrow_btn;


    @FXML
    void hoverActivated(MouseEvent event) {
        borrow_btn.setBlendMode(BlendMode.MULTIPLY);
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        borrow_btn.setBlendMode(BlendMode.SRC_OVER);
    }

    @FXML
    void borrowBook(MouseEvent event) {
        try {
            User currUser = Library.findUser(NavigationController.getLoggedPerson().getUsername());

            currUser.borrowBook(currBook);
            // successfull borrow, go automatically to borrow history to show it
            NavigationController.loadCenter("/views/user_borrowHistory.fxml");
        }
        catch (UserNotFoundException e) {
            // user not found in the library by findAdmin, log out automatically and tell admin to log in again.
            NavigationController.setMainLayout(null);
            NavigationController.setLoggedPerson(null);
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "/views/login.fxml");
        }
        catch (BorrowLimitException | NoCopiesAvailableException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (currBook == null) {
                // if currBook null the page cannot be initialized - no need for null check in the other functions - button handlers 
                throw new NullPointerException();
            }

            setBookLabels(currBook);
            setCategoryLabel(currBook);
            setReviewList(currBook);
        }
        catch (NullPointerException e) {
            NavigationController.showAlert(AlertType.ERROR, "Something went wrong. This details book page could not be opened.", "/views/homepage.fxml");
        }
    }

    
    private void setBookLabels(Book book) {
        title.setText(book.getTitle());
        isbn.setText(book.getISBN());
        author.setText(book.getAuthor());
        publisher.setText(book.getPublisher());

        avgRating.setText(String.valueOf(book.getAvgRating()));
        year.setText(String.valueOf(book.getPublicationYear()));
        copies.setText(String.valueOf(book.getCopiesAvailable()));
        
        numUsersRated.setText(
            "( " + String.valueOf(book.getReviews().size()) + " user reviews )"
        );
    }

    private void setCategoryLabel(Book book) {
        try {
            Category cat = Library.categoryOfBook(book.getISBN());
            String catName = cat.getName();
            category.setText(catName);
        }
        catch (NotFoundException e) {
            // category not found
            category.setText(null);
        }
    }

    private void setReviewList(Book book) {
        ObservableList<Review> observableReviewlist = FXCollections.observableArrayList();
        List<Review> reviews = book.getReviews();

        observableReviewlist.addAll(reviews);

        reviewList.setItems(observableReviewlist);
        reviewList.setCellFactory(booklist -> new ListCellReview());
    }

    public static Book getCurrBook() {
        return currBook;
    }
    public static void setCurrBook(Book currBook) {
        UserBookDetailsController.currBook = currBook;
    }

}