package controllers;

import java.io.IOException;

import exceptions.NotFoundException;
import exceptions.UserNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Admin;
import models.Borrowed;
import models.Library;
import models.User;


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
        try {
            Admin admin = Library.findAdmin(NavigationController.getLoggedPerson().getUsername());
            Borrowed currCellActiveBorrow = getItem();

            admin.terminateBorrow(currCellActiveBorrow);
            NavigationController.loadCenter("/views/admin_manageBorrows.fxml");
        }
        catch (UserNotFoundException e) {
            // admin not found in the library by findAdmin, log out automatically and tell admin to log in again.
            NavigationController.setMainLayout(null);
            NavigationController.setLoggedPerson(null);
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "/views/login.fxml");
        }
        
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


            try {
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
            catch (NotFoundException e) {
                // this will most likely never been thrown as we make sure our data is consistent in the model package
                // book of the borrow not found in the library - inconsistent data: the borrow must have been removed already
                
                // fistly we fix all the possible inconsistent data of the Library (deleted book dependencies)
                for (Borrowed bor : Library.getAllActiveBorrows()) {
                    if( (bor.getBookISBN()).equals(activeBorrow.getBookISBN()) ) {
                        Library.removeActiveBorrow(bor);
                    }
                }
                for (User user : Library.getAllUsers()) {
                    user.removeBorrowHistory(activeBorrow.getBookISBN());
                }

                // we set empty cell
                setText(null);
                setGraphic(null);
            }
           
        }
    }
}
