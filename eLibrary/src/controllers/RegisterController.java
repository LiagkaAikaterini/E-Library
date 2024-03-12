package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class RegisterController implements Initializable {
    
    @FXML
    private Button home_btn;
    @FXML
    private Button loginForm_btn;

    @FXML
    private Button admin_btn;
    @FXML
    private Button user_btn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        admin_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NavigationController.loadPage("/views/register_admin.fxml");
            }
        });

        user_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NavigationController.loadPage("/views/register_user.fxml");
            }
        });
    }

}

