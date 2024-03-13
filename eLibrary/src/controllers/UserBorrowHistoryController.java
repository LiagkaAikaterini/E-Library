package controllers;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        // HANDLE IF USER IS ADMIN????????????????????????????????????????
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

        User user = Library.getCurrUser(currUser);
        List<Book> historyBooks = new ArrayList<Book>();

        for (String isbn : user.getBorrowHistory()) {
            Book book = Library.findBook(isbn);
            historyBooks.add(book);
        }

        observableBooklist.addAll(historyBooks);
        historyList.setItems(observableBooklist);
        historyList.setCellFactory(booklist -> new ListCellBorrow());
    }

}

