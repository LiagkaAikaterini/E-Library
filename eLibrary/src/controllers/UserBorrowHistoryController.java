package controllers;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

import exceptions.NotFoundException;
import exceptions.UserNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import models.Book;
import models.Borrowed;
import models.Library;
import models.User;
import models.UserBase;

public class UserBorrowHistoryController implements Initializable {

    @FXML
    private ListView<Borrowed> activeBorrowsList;
    @FXML
    private ListView<Book> historyList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserBase loggedUser = NavigationController.getLoggedPerson();
        activeBorrowsInit(loggedUser);
        historyInit(loggedUser);
    }


    private void activeBorrowsInit(UserBase currUser) {
        ObservableList<Borrowed> observableBooklist = FXCollections.observableArrayList();
        
        String currUsername = currUser.getUsername();
        List<Borrowed> books = Library.findUsersActiveBorrows(currUsername);

        observableBooklist.addAll(books);
        activeBorrowsList.setItems(observableBooklist);
        activeBorrowsList.setCellFactory(booklist -> new ListCellReviewBook());
    }

    private void historyInit(UserBase currUser) {
        ObservableList<Book> observableBooklist = FXCollections.observableArrayList();
        List<Book> historyBooks = new ArrayList<Book>();

        try {
            User user = Library.findUser(currUser.getUsername());

            for (String isbn : user.getBorrowHistory()) {
                try {
                    Book book = Library.findBook(isbn);
                    historyBooks.add(book);
                }
                catch (NotFoundException e) {
                    // if book not found we do not add it to the dispay history list and we continue with the iteration
                }
            }

            observableBooklist.addAll(historyBooks);
            historyList.setItems(observableBooklist);
            historyList.setCellFactory(booklist -> new ListCellBorrow());
        }
        catch (UserNotFoundException e) {
            // user not found in the library by findAdmin, log out automatically and tell admin to log in again.
            NavigationController.setMainLayout(null);
            NavigationController.setLoggedPerson(null);
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "/views/login.fxml");
        }
        
    }

}

