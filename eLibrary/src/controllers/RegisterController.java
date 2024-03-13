package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;

public class RegisterController {
    
    @FXML
    private Button home_btn;
    @FXML
    private Button loginForm_btn;

    @FXML
    private Button admin_btn;
    @FXML
    private Button user_btn;


    // hover effect for buttons
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

    
    @FXML
    void goToHome(MouseEvent event) {
        NavigationController.loadPage("/views/home.fxml");
    }

    @FXML
    void goToLogin(MouseEvent event) {
        NavigationController.loadPage("/views/login.fxml");
    }

    @FXML
    void goToAdminRegister(MouseEvent event) {
        NavigationController.loadPage("/views/register_admin.fxml");
    }

    @FXML
    void goToUserRegister(MouseEvent event) {
        NavigationController.loadPage("/views/register_user.fxml");
    }

}

