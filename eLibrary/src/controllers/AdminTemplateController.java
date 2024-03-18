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


public class AdminTemplateController implements Initializable {

    @FXML
    private BorderPane mainLayout;

    @FXML
    private Button homepage_btn;
    @FXML
    private Button search_btn;
    @FXML
    private Button manageBooks_btn;
    @FXML
    private Button manageCategories_btn;
    @FXML
    private Button manageBorrows_btn;
    @FXML
    private Button manageUsers_btn;
    @FXML
    private Button help_btn;
    
    @FXML
    private TextField topSearchBar;
    
    @FXML
    private MenuButton username_dropdown;
    @FXML
    private MenuItem logout_btn;


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
    void hoverMenuActivated(MouseEvent event) {
        username_dropdown.setBlendMode(BlendMode.MULTIPLY);
    }
    @FXML
    void hoverMenuDeactivated(MouseEvent event) {
        username_dropdown.setBlendMode(BlendMode.SRC_OVER);
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
    void goToManageBooks(MouseEvent event) {
        NavigationController.loadCenter("/views/admin_manageBooks.fxml");
    }
    @FXML
    void goToManageCategories(MouseEvent event) {
        NavigationController.loadCenter("/views/admin_manageCategories.fxml");
    }
    @FXML
    void goToManageBorrows(MouseEvent event) {
        NavigationController.loadCenter("/views/admin_manageBorrows.fxml");
    }
    @FXML
    void goToManageUsers(MouseEvent event) {
        NavigationController.loadCenter("/views/admin_manageUsers.fxml");
    }
    @FXML
    void goToHelpPage(MouseEvent event) {
        NavigationController.loadCenter("/views/admin_help.fxml");
    }


    @FXML
    void onEnterSearch(ActionEvent event) { 
        String title = topSearchBar.getText().replaceAll("\\s+", " ");

        if (!title.isEmpty()) {
            List<Book> searchRes = UserBase.searchByTitle(title);

            SearchResultController.setResult(searchRes);
            topSearchBar.setText("");
            mainLayout.requestFocus();
            NavigationController.loadCenter("/views/searchResult.fxml");
            
        }
    }

    // Logout MenuItem handling 
    @FXML
    void logout(ActionEvent event) {
        NavigationController.setMainLayout(null);
        NavigationController.setLoggedPerson(null);

        NavigationController.loadPage("/views/home.fxml");
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username_dropdown.setText(NavigationController.getLoggedPerson().getUsername());
        NavigationController.setMainLayout(mainLayout);
        NavigationController.loadCenter("/views/homepage.fxml");
    }

}