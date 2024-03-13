package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
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
    void signup(MouseEvent event) {
        //??????????????????????????????????????????????????????????????
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
