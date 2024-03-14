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


public class ListCellAdminBook extends ListCell<Book> {

    @FXML
    private HBox hbox;

    @FXML
    private Label title;
    @FXML
    private Label author;
    @FXML
    private Label isbn;
    @FXML
    private Label rating;

    @FXML
    private Button modifyBook_btn;
    @FXML
    private Button deleteBook_btn;


    // button hover effect
    @FXML
    void hoverActivatedDelete(MouseEvent event) {
        deleteBook_btn.setStyle("-fx-background-color: #CC0000");
    }
    @FXML
    void hoverDeactivatedDelete(MouseEvent event) {
        deleteBook_btn.setStyle("-fx-background-color: #E74C3C");
    }
    @FXML
    void hoverActivated(MouseEvent event) {
        modifyBook_btn.setStyle("-fx-background-color: #8C7460");
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        modifyBook_btn.setStyle("-fx-background-color: #B69E7A");
    }


    @FXML
    void goToModifyBook(MouseEvent event) {
        Book currBook = getItem();

        if (currBook != null) {
            //AdminModifyBookController.setCurrBook(currBook);
            //NavigationController.loadCenter("/views/admin_modifyBook.fxml");
        }
    }

    @FXML
    void deleteBook(MouseEvent event) {

    }
   

    @Override
    protected void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);

        if (empty || book == null) {
            setText(null);
            setGraphic(null);
        } 
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listcell_adminBook.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        
            hbox.prefWidthProperty().bind(getListView().widthProperty());

            title.setText(book.getTitle());
            author.setText("by " + book.getAuthor());
            isbn.setText("ISBN: " + book.getISBN());;
            rating.setText(
                book.getAvgRating() + "  (" + String.valueOf(book.getCopiesAvailable()) + " reviews)"
            );

            setText(null);
            setGraphic(hbox);
        }
    }
}
