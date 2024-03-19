package controllers;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidDateException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
        String title = title_input.getText().replaceAll("\\s+", " ").strip();
        String author = author_input.getText().replaceAll("\\s+", " ").strip();
        String year = year_input.getText().replaceAll("\\s+", "");

        // execute searchs seperately for each text field that is not empty
        List< List<Book> > results = new ArrayList<>();


        if ( !title.isEmpty() ) {
            List<Book> resTitle = UserBase.searchByTitle(title);
            results.add(resTitle);
        }

        if ( !author.isEmpty() ) {
            List<Book> resAuthor = UserBase.searchByAuthor(author);
            results.add(resAuthor);
        }

        if ( !year.isEmpty() ) {
            try {
                int yearInt = Integer.parseInt(year);

                // check if year negative or in the future - invalid years
                if (yearInt < 0 || yearInt > java.time.LocalDate.now().getYear()) {
                    throw new InvalidDateException("The year provided is invalid. Please ensure the year you enter is a positive integer and does not represent a future year.");
                }

                List<Book> resYear = UserBase.searchByYear(yearInt);
                results.add(resYear);
            } 
            catch (NumberFormatException e) {
                NavigationController.showAlert(AlertType.ERROR, "The year provided in not a valid Integer.", "");
                return;
            }
            catch (InvalidDateException e) {
                NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
                return;
            }
        }


        // all the search result Book Lists are saved in the "results" List
        // according to the size of this list we combine the resultLists the appropriate way 
        List<Book> searchRes = null;

        switch (results.size()) {
            case 1:
                searchRes = results.get(0);
                break;
            case 2:
                searchRes = UserBase.combineSearches(results.get(0), results.get(1));
                break;
            case 3:
                searchRes = UserBase.combineThreeSearches(results.get(0), results.get(1), results.get(2));
                break;
        }

        // if searchRes == null no input was given so user stay in the searchForm page
        if (searchRes != null) {
            SearchResultController.setResult(searchRes);
            NavigationController.loadCenter("/views/searchResult.fxml");
        }
    }

}

