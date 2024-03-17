package controllers;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import exceptions.InvalidBookInfoException;
import exceptions.InvalidDateException;
import exceptions.NotFoundException;
import exceptions.UserNotFoundException;
import models.Admin;
import models.Library;


public class AdminCreateBookController {

    @FXML
    private TextField title_input;
    @FXML
    private TextField copies_input;
    @FXML
    private TextField category_input;
    @FXML
    private TextField author_input;
    @FXML
    private TextField publisher_input;
    @FXML
    private TextField isbn_input;
    @FXML
    private DatePicker publishDate_input;

    @FXML
    private Button createBook_btn;


    //hover effect for Buttons
    @FXML
    void hoverActivated(MouseEvent event) {
        createBook_btn.setBlendMode(BlendMode.MULTIPLY);
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        createBook_btn.setBlendMode(BlendMode.SRC_OVER);
    }


    @FXML
    void createNewBook(MouseEvent event) {
        try {
            // if logged in admin not found -> UserNotFoundException
            Admin admin = Library.findAdmin(NavigationController.getLoggedPerson().getUsername());

            // the replaceAll whitespace characters (space, tab, newline) with a single space or no space, according to the field
            // for example ISBN or category should have no whitespace characters - one word
            String category = category_input.getText().replaceAll("\\s+", "");
            
            String title = title_input.getText().replaceAll("\\s+", " ");
            String author = author_input.getText().replaceAll("\\s+", " ");
            String publisher = publisher_input.getText().replaceAll("\\s+", " ");
            String isbn = isbn_input.getText().replaceAll("\\s+", "");
            LocalDate publishDate = publishDate_input.getValue();

            String copies = copies_input.getText().replaceAll("\\s+", ""); 
            int copiesInt = Integer.parseInt(copies);
        
            // if some argument is not right the Book object will not be created -> throws custom exceptions
            admin.createBook(title, author, publisher, isbn, publishDate, copiesInt, category);

            // succeessful creation navigate back to manage books
            NavigationController.loadCenter("/views/admin_manageBooks.fxml");     
        }
        catch (UserNotFoundException e) {
            // admin not found in the library by findAdmin, log out automatically and tell admin to log in again.
            NavigationController.setMainLayout(null);
            NavigationController.setLoggedPerson(null);
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "/views/login.fxml");
        }
        catch (NumberFormatException e) {
            NavigationController.showAlert(AlertType.ERROR, "The copies you provided is not a valid Integer.", "");
        }
        catch (InvalidBookInfoException | NotFoundException | InvalidDateException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
        }
    }

}
