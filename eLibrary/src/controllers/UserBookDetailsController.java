package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import models.Book;
import models.Library;
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
        // ??????????????????????????????????????????????????????????????????????????????????
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (currBook != null) {
            setBookLabels(currBook);
            setCategoryLabel(currBook);
            setReviewList(currBook);
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
        String cat = Library.categoryOfBook(book.getISBN());
        
        if (cat == null) {
            category.setText(null);
        }
        else {
            category.setText(cat);
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