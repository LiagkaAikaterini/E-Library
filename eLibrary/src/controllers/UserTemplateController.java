package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import models.Book;
import models.UserBase;


public class UserTemplateController implements Initializable {
    
    @FXML
    private BorderPane mainLayout;

    @FXML
    private Button homepage_btn;
    @FXML
    private Button search_btn;
    @FXML
    private Button history_btn;
    @FXML
    private Button help_btn;

    @FXML
    private TextField topSearchBar;

    @FXML
    private MenuButton profile_dropdown;
    @FXML
    private MenuItem profile;
    @FXML
    private MenuItem logout;

    //hover effect for Buttons
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
    
    // hover effect for MenuButton
    @FXML
    void hoverActivatedMenu(MouseEvent event) {
        profile_dropdown.setBlendMode(BlendMode.MULTIPLY);
    }
    @FXML
    void hoverDeactivatedMenu(MouseEvent event) {
        profile_dropdown.setBlendMode(BlendMode.SRC_OVER);
    }

    // Navigation handling for buttons in the sidebar menu
    @FXML
    void goToHomepage(MouseEvent event) {
        NavigationController.loadCenter("/views/homepage.fxml");
    }
    @FXML
    void goToSearchForm(MouseEvent event) {
        NavigationController.loadCenter("/views/searchForm.fxml");
    }
    @FXML
    void goToBorrowHistory(MouseEvent event) {
        NavigationController.loadCenter("/views/user_borrowHistory.fxml");
    }
    @FXML
    void goToHelpPage(MouseEvent event) {
        NavigationController.loadCenter("/views/user_help.fxml");
    }


    // top search bar that is contained on the upper menu handled
    @FXML
    void onEnterSearch(ActionEvent event) { 
        String title = topSearchBar.getText().replaceAll("\\s+", " ");

        if (!title.isEmpty()) {
            List<Book> searchRes = UserBase.searchByTitle(title);
            
            SearchResultController.setResult(searchRes);
            NavigationController.loadCenter("/views/searchResult.fxml");
        }
    }

    // navigation handling for MenuItems of MenuButton - Logout handling
    @FXML
    void goToProfile(ActionEvent event) {
        NavigationController.loadCenter("/views/user_profile.fxml");
    }
    @FXML
    void logout(ActionEvent event) {
        NavigationController.setMainLayout(null);
        NavigationController.setLoggedPerson(null);

        NavigationController.loadPage("/views/home.fxml");
    }
   

    // Loads default content when the application starts
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profile_dropdown.setText(NavigationController.getLoggedPerson().getUsername());
        NavigationController.setMainLayout(mainLayout);
        NavigationController.loadCenter("/views/homepage.fxml");
    }

}
