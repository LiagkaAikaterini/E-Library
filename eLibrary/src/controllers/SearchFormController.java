package controllers;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import models.Book;
import models.UserBase;

public class SearchFormController {

    @FXML
    private TextField title_input;
    @FXML
    private TextField author_input;
    @FXML
    private TextField year_input;

    @FXML
    private Button search_btn;


    //button hover effect
    @FXML
    void hoverActivated(MouseEvent event) {
        search_btn.setBlendMode(BlendMode.MULTIPLY);
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        search_btn.setBlendMode(BlendMode.SRC_OVER);
    }


    @FXML
    void search(MouseEvent event) {        
        // get input and replace whitespace characters (space, tab, newline) with a single space.
        String title = title_input.getText().replaceAll("\\s+", " ");
        String author = author_input.getText().replaceAll("\\s+", " ");
        String year = year_input.getText().replaceAll("\\s+", "");
        
        List<Book> res1 = null;

        if ( !year.isEmpty() ) {
            try {
                int yearInt = Integer.parseInt(year);

                // check if year negative or in the future
                // invalid years
                if (yearInt < 0 || yearInt > java.time.LocalDate.now().getYear()) {
                    throw new NumberFormatException();
                }

                res1 = UserBase.searchByYear(yearInt);
                
                // If parsing succeeds, input is a valid integer
                //showAlert("Valid Integer", "Input is a valid integer: " + value);
            } catch (NumberFormatException ex) {
                System.out.println("INVALID YEAR");
                // If parsing fails, input is not a valid integer
                // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ->     Alert and the search will not be executed
                //showAlert("Invalid Input", "Input is not a valid integer");
            }
        }

        List<Book> res2 = UserBase.searchByTitle(title);
        List<Book> res3 = UserBase.searchByAuthor(author);
        
        List<Book> searchRes = UserBase.search(res1, res2, res3);

        if (searchRes == null) {
            // no input
            System.out.println("NO INPUT");
        }
        else {
            System.out.println("DONE");
            SearchResultController.setResult(searchRes);
        }
        
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // execute search 
        
        
    }

}

