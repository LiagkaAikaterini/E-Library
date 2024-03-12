package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // get top 5 books 
        ObservableList<Book> studentObservableList = FXCollections.observableArrayList();

        for (Book book : Library.getAllBooks()) {
            studentObservableList.add(book);
        }

        // show top 5 books
        topBooklist.setItems(studentObservableList);
        topBooklist.setCellFactory(booklist -> new ListCellBorrow());
        

        // button usability
        signupForm_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NavigationController.loadPage("/views/register.fxml");
            }
        });

        loginForm_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NavigationController.loadPage("/views/login.fxml");
            }
        });
    }

    
}
