package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController implements Initializable {

    @FXML
    private Button home_btn;
    @FXML
    private Button login_btn;

    @FXML
    private PasswordField password_input;
    @FXML
    private TextField username_input;
    
    @FXML
    private Button signupForm_btn;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NavigationController.loadPage("/views/home.fxml");
            }
        });

        signupForm_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NavigationController.loadPage("/views/register.fxml");
            }
        });
        
    }

}
