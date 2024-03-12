package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RegisterUserController implements Initializable {
    
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button home_btn;
    @FXML
    private Button loginForm_btn;
    
    @FXML
    private TextField username_input;
    @FXML
    private TextField address_input;
    @FXML
    private DatePicker birthday_input;
    @FXML
    private TextField email_input;
    @FXML
    private TextField firstname_input;
    @FXML
    private TextField id_input;
    @FXML
    private TextField lastname_input;
    @FXML
    private PasswordField password2_input;
    @FXML
    private PasswordField password_input;

    @FXML
    private Button signup_btn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorpane.prefWidthProperty().bind((scrollpane.widthProperty()));

        home_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NavigationController.loadPage("/views/home.fxml");
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
