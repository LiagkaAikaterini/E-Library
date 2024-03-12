package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import main.App;

public class UserMenuController implements Initializable {
    
    @FXML
    private BorderPane mainLayout;

    @FXML
    private Button homepage_btn;
    @FXML
    private Button search_btn;
    @FXML
    private MenuButton categories_dropdown;
    @FXML
    private Button history_btn;
    @FXML
    private Button help_btn;

    @FXML
    private MenuButton profile_dropdown;
    @FXML
    private MenuItem profile;
    @FXML
    private MenuItem logout;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load default content when the application starts

        NavigationController.setMainLayout(mainLayout);

        // hover effect for menu items
        homepage_btn.setOnMouseEntered(this::hoverActivated);
        search_btn.setOnMouseEntered(this::hoverActivated);
        history_btn.setOnMouseEntered(this::hoverActivated);
        help_btn.setOnMouseEntered(this::hoverActivated);
        categories_dropdown.setOnMouseEntered(this::hoverMenuActivated);
        profile_dropdown.setOnMouseEntered(this::hoverMenuActivated);

        homepage_btn.setOnMouseExited(this::hoverDeactivated);
        search_btn.setOnMouseExited(this::hoverDeactivated);
        history_btn.setOnMouseExited(this::hoverDeactivated);
        help_btn.setOnMouseExited(this::hoverDeactivated);
        categories_dropdown.setOnMouseExited(this::hoverMenuDeactivated);
        profile_dropdown.setOnMouseExited(this::hoverMenuDeactivated);


        // When each button of the Menu gets clicked the correct page the correct page appears
        homepage_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NavigationController.loadCenter("/views/titledBookList.fxml");
            }
        });

        search_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NavigationController.loadCenter("/views/user_searchForm.fxml");
            }
        });

        // Prosoxhhhhh vres pws na fotvneis tis categories
        categories_dropdown.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {




            }
        });

        history_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NavigationController.loadCenter("/views/user_borrowHistory.fxml");
            }
        });

        help_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NavigationController.loadCenter("/views/user_help.fxml");
            }
        });

        profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NavigationController.loadCenter("/views/user_profile.fxml");
            }
        });

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NavigationController.setBook(null);
                NavigationController.setMainLayout(null);
                
                App.getLibraryInstance().setLoggedUser(null);

                NavigationController.loadPage("/views/home.fxml");
            }
        });

        
       
    }

    private void hoverActivated(MouseEvent event) {
        Button buttonEntered = (Button) event.getSource();
        buttonEntered.setBlendMode(BlendMode.MULTIPLY);
    }

    private void hoverDeactivated(MouseEvent event) {
        Button buttonExited = (Button) event.getSource();
        buttonExited.setBlendMode(BlendMode.SRC_OVER);
    }

    private void hoverMenuActivated(MouseEvent event) {
        MenuButton buttonEntered = (MenuButton) event.getSource();
        buttonEntered.setBlendMode(BlendMode.MULTIPLY);
    }

    private void hoverMenuDeactivated(MouseEvent event) {
        MenuButton buttonExited = (MenuButton) event.getSource();
        buttonExited.setBlendMode(BlendMode.SRC_OVER);
    }
    

}
