package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import exceptions.InvalidBookInfoException;
import exceptions.InvalidDateException;
import exceptions.NotFoundException;
import exceptions.UserNotFoundException;
import models.Admin;
import models.Book;
import models.Category;
import models.Library;


public class AdminModifyBookController implements Initializable {

    private static Book currBook;
    private Admin admin;
    
    @FXML
    private Label title;
    @FXML
    private Label copies;
    @FXML
    private Label category;
    @FXML
    private Label author;
    @FXML
    private Label publisher;
    @FXML
    private Label isbn;
    @FXML
    private Label publishDate;
    
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
    private Button title_btn;
    @FXML
    private Button copies_btn; 
    @FXML
    private Button category_btn;
    @FXML
    private Button author_btn;
    @FXML
    private Button publisher_btn;
    @FXML
    private Button isbn_btn;
    @FXML
    private Button publishDate_btn;

    @FXML
    private Button deleteBook_btn;


    //hover effect for Buttons
    @FXML
    void hoverActivated(MouseEvent event) {
        Button buttonEntered = (Button) event.getSource();
        buttonEntered.setBlendMode(BlendMode.MULTIPLY);
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        Button buttonEntered = (Button) event.getSource();
        buttonEntered.setBlendMode(BlendMode.SRC_OVER);
    }


    // admin changes user info - buttons OnClick handlers
    @FXML
    void changeTitle(MouseEvent event) {
        String newTitle = title_input.getText().replaceAll("\\s+", " ");
        admin.changeBookTitle(currBook, newTitle);
        NavigationController.loadCenter("/views/admin_modifyBook.fxml");
    }

    @FXML
    void changeCopiesAvail(MouseEvent event) {
        try {
            String newCopies = copies_input.getText().replaceAll("\\s+", "");
            int newCopiesInt = Integer.parseInt(newCopies);

            admin.changeBookCopies(currBook, newCopiesInt);
            NavigationController.loadCenter("/views/admin_modifyBook.fxml");
            
        } 
        catch (NumberFormatException e) {
            NavigationController.showAlert(AlertType.ERROR, "The copies you provided is not a valid Integer.", "");
        }
        catch (InvalidBookInfoException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
        }
    }
    
    @FXML
    void changeCategory(MouseEvent event) {
        try {
            String newCat = category_input.getText().replaceAll("\\s+", " ");
            admin.addBookToCategory(currBook.getISBN(), newCat);
            NavigationController.loadCenter("/views/admin_modifyBook.fxml");
        }
        catch(NotFoundException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
        }
    }

    @FXML
    void changeAuthor(MouseEvent event) {
        String newAuthor = author_input.getText().replaceAll("\\s+", " ");
        admin.changeBookAuthor(currBook, newAuthor);
        NavigationController.loadCenter("/views/admin_modifyBook.fxml");
    }

    @FXML
    void changePublisher(MouseEvent event) {
        String newPublisher = publisher_input.getText().replaceAll("\\s+", " ");
        admin.changeBookPublisher(currBook, newPublisher);
        NavigationController.loadCenter("/views/admin_modifyBook.fxml");
    }

    @FXML
    void changeISBN(MouseEvent event) {
        try {
            String newISBN = isbn_input.getText().replaceAll("\\s+", "");
            admin.changeBookISBN(currBook, newISBN);
            NavigationController.loadCenter("/views/admin_modifyBook.fxml");
        }
        catch(InvalidBookInfoException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
        }
    } 

    @FXML
    void changeDatePublished(MouseEvent event) {
        try {
            LocalDate newDate = publishDate_input.getValue();
            admin.changeBookDatePublished(currBook, newDate);
            NavigationController.loadCenter("/views/admin_modifyBook.fxml");
        }
        catch(InvalidDateException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
        }
        
    }

    // Delete Book button - onClick handler
    @FXML
    void deleteBook(MouseEvent event) {
        admin.deleteBook(currBook);
        NavigationController.loadCenter("/views/admin_manageBooks.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // we keep admin in the page beacause it is used all the button handlers to keep the code simpler
            this.admin = Library.findAdmin(NavigationController.getLoggedPerson().getUsername());
            // initialize page info
            currBookInfoInit();
            setCategoryLabel();
        }
        catch (UserNotFoundException e) {
            // admin not found in the library by findAdmin, log out automatically and tell admin to log in again.
            NavigationController.setMainLayout(null);
            NavigationController.setLoggedPerson(null);
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "/views/login.fxml");
        }
        
    }

    
    private void currBookInfoInit() {
        title.setText(currBook.getTitle());
        author.setText(currBook.getAuthor());
        publisher.setText(currBook.getPublisher());
        isbn.setText(currBook.getISBN());

        copies.setText(String.valueOf(currBook.getCopiesAvailable()));
        publishDate.setText(String.valueOf(currBook.getDatePublished()));
    }

    private void setCategoryLabel() {
        try {
            Category cat = Library.categoryOfBook(currBook.getISBN());
            String catName = cat.getName();
            category.setText(catName);
        }
        catch (NotFoundException e) {
            // category not found
            category.setText(null);
        }
    }

    public static Book getCurrBook() {
        return currBook;
    }
    public static void setCurrBook(Book currBook) {
        AdminModifyBookController.currBook = currBook;
    }

}