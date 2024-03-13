package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import models.Book;
import models.Library;
import models.Book.Review;

public class UserBookDetailsController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Book book = NavigationController.getCurrBook();

        if (book != null) {
            // get all book related information
            title.setText(book.getTitle());
            avgRating.setText(String.valueOf(book.getAvgRating()));
            numUsersRated.setText(
                "( " + String.valueOf(book.getReviews().size()) + " user reviews )"
            );
            author.setText(book.getAuthor());
            publisher.setText(book.getPublisher());
            year.setText(String.valueOf(book.getPublicationYear()));
            isbn.setText(book.getISBN());
            copies.setText(String.valueOf(book.getCopiesAvailable()));

            // find the category the book belongs in and display it
            String cat = Library.categoryOfBook(book.getISBN());
            if (cat == null) {
                category.setText(null);
            }
            else {
                category.setText(cat);
            }

            // display reviews in review list
            ObservableList<Review> observableReviewlist = FXCollections.observableArrayList();
            List<Review> reviews = book.getReviews();

            observableReviewlist.addAll(reviews);

            reviewList.setItems(observableReviewlist);
            reviewList.setCellFactory(booklist -> new ListCellReview());

            // handle borrow action
            borrow_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }
            });

        }
    }

}