package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import models.Book.Review;

public class ListCellReview extends ListCell<Review> {

    @FXML
    private VBox vbox;

    @FXML
    private Label username;
    @FXML
    private Label rating;
    @FXML
    private Label comment;
    

    @Override
    protected void updateItem(Review review, boolean empty) {
        super.updateItem(review, empty);

        if(empty || review == null) {
            setText(null);
            setGraphic(null);
        } 
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listcell_review.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }

            vbox.prefWidthProperty().bind(getListView().widthProperty());
            comment.prefWidthProperty().bind(vbox.widthProperty());

            username.setText(review.getUsername());
            rating.setText(
                "(  " + String.valueOf(review.getRating()) + " / 5  )"
            );
            comment.setText(review.getComment());

            setText(null);
            setGraphic(vbox);
        }
    }
    
}