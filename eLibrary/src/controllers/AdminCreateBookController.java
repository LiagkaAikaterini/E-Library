package controllers;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import models.Admin;
import models.Library;
import models.UserBase;

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
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());

        String category = category_input.getText().replaceAll("\\s+", " ");
        
        String title = title_input.getText().replaceAll("\\s+", " ");
        String author = author_input.getText().replaceAll("\\s+", " ");
        String publisher = publisher_input.getText().replaceAll("\\s+", " ");
        String isbn = isbn_input.getText().replaceAll("\\s+", "");
        LocalDate publishDate = publishDate_input.getValue();

        String copies = copies_input.getText().replaceAll("\\s+", "");

        try {
                int copiesInt = Integer.parseInt(copies);

                // check if year negative or in the future
                // invalid years
                if (copiesInt < 0) {
                    throw new NumberFormatException();
                }

                if (admin != null) {
                    admin.createBook(title, author, publisher, isbn, publishDate, copiesInt, category);
                }
                
                // If parsing succeeds, input is a valid integer
                //showAlert("Valid Integer", "Input is a valid integer: " + value);
            } catch (NumberFormatException ex) {
                System.out.println("INVALID YEAR");
                // If parsing fails, input is not a valid integer
                // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ->     Alert and the search will not be executed
                //showAlert("Invalid Input", "Input is not a valid integer");
            }
        

        
    }

}
