package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import models.Borrowed;
import models.Library;


public class ListCellAdminBorrow extends ListCell<Borrowed> {

    @FXML
    private HBox hbox;

    @FXML
    private Label username;
    @FXML
    private Label booktitle;
    @FXML
    private Label isbn;
    @FXML
    private Label borrowDate;
    @FXML
    private Label returnDate;
    
    @FXML
    private Button terminate_btn;


    // button hover effect
    @FXML
    void hoverActivated(MouseEvent event) {
        terminate_btn.setStyle("-fx-background-color: #CC0000");
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        terminate_btn.setStyle("-fx-background-color: #E74C3C");
    }


    @FXML
    void terminateActiveBorrow(MouseEvent event) {
        // ?????????????????????????????????????????????????????????/
    }


    @Override
    protected void updateItem(Borrowed activeBorrow, boolean empty) {
        super.updateItem(activeBorrow, empty);

        if (empty || activeBorrow == null) {
            setText(null);
            setGraphic(null);
        } 
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listcell_adminBorrow.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        
            hbox.prefWidthProperty().bind(getListView().widthProperty());


            username.setText(activeBorrow.getUsername());
            
            isbn.setText(activeBorrow.getBookISBN());
            booktitle.setText(
                Library.findBook(activeBorrow.getBookISBN()).getTitle()
            );

            borrowDate.setText(String.valueOf(activeBorrow.getBorrowingDate()));
            returnDate.setText(String.valueOf(activeBorrow.getReturnDate()));
            

            setText(null);
            setGraphic(hbox);
        }
    }
}
