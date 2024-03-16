package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import models.Book;
import models.Library;

public class HomeController implements Initializable {

    @FXML
    private Button loginForm_btn;
    @FXML
    private Button signupForm_btn;

    @FXML
    private ListView<Book> topBooklist;


    // button hover effect
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

    // set navigation handlers for buttons  
    @FXML
    void goToLogin(MouseEvent event) {
        NavigationController.loadPage("/views/login.fxml");
    }

    @FXML
    void goToRegister(MouseEvent event) {
        NavigationController.loadPage("/views/register.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Book> observableBookList = FXCollections.observableArrayList();

        observableBookList.addAll(Library.getTop5Books());

        topBooklist.setItems(observableBookList);
        topBooklist.setCellFactory(booklist -> new ListCellBook());
        
    }

    
}
