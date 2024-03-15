package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import models.Book;
import models.Category;
import models.Library;

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
    private TextArea comment_input;

    @FXML
    private ToggleGroup rating_input;
    @FXML
    private ToggleButton rating1;
    @FXML
    private ToggleButton rating2;
    @FXML
    private ToggleButton rating3;
    @FXML
    private ToggleButton rating4;
    @FXML
    private ToggleButton rating5;

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
        // ???????????????????????????????????????????????????????????????????
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookInfoDisplay();
        bookCategoryDisplay();
    }

    private void bookInfoDisplay() {
        title.setText(currBook.getTitle());
        isbn.setText(currBook.getISBN());
        author.setText(currBook.getAuthor());
        publisher.setText(currBook.getPublisher());
        year.setText(String.valueOf(currBook.getPublicationYear()));
    }

    private void bookCategoryDisplay() {
        Category cat = Library.categoryOfBook(currBook.getISBN());
        
        if (cat == null) {
            category.setText(null);
        }
        else {
            String catName = cat.getName();
            category.setText(catName);
        }
    }

    public static Book getCurrBook() {
        return currBook;
    }
    public static void setCurrBook(Book currBook) {
        UserReviewBookController.currBook = currBook;
    }

}