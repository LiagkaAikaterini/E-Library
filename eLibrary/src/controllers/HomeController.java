package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import models.Book;
import models.Library;

public class HomeController implements Initializable{
    @FXML
    private Button login_btn;

    @FXML
    private Button signup_btn;

    @FXML
    private ListView<Book> topBooklist;

    private ObservableList<Book> studentObservableList;

    @FXML
    void goToLoginPage(MouseEvent event) {

    }

    @FXML
    void goToSignUpPage(MouseEvent event) {

    }

    public HomeController()  {

        studentObservableList = FXCollections.observableArrayList();

        //add some Students
        for (Book book : Library.getAllBooks()) {
            studentObservableList.add(book);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        topBooklist.setItems(studentObservableList);
        topBooklist.setCellFactory(studentListView -> new BookCellController());
    }

    
}
