package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import exceptions.CategoryException;
import exceptions.UserNotFoundException;
import models.Admin;
import models.Category;
import models.Library;


public class ListCellAdminCategory extends ListCell<Category> {

    @FXML
    private VBox vbox;
    
    @FXML
    private Label categoryName;
    @FXML
    private TextField newName_input;
    
    @FXML
    private Button changeName_btn;
    @FXML
    private Button deleteCategory_btn;


    // button hover effect
    @FXML
    void hoverActivated(MouseEvent event) {
        changeName_btn.setStyle("-fx-background-color: #8C7460");
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        changeName_btn.setStyle("-fx-background-color: #B69E7A");
    }
    @FXML
    void hoverActivatedDelete(MouseEvent event) {
        deleteCategory_btn.setStyle("-fx-background-color: #CC0000");
    }
    @FXML
    void hoverDeactivatedDelete(MouseEvent event) {
        deleteCategory_btn.setStyle("-fx-background-color: #E74C3C");
    }


    @FXML
    void changeCategoryName(MouseEvent event) {
        try {
            Admin admin = Library.findAdmin(NavigationController.getLoggedPerson().getUsername());
            Category currCellCategory = getItem();
            String newCategoryName = newName_input.getText().replaceAll("\\s+", "");

            if (!newCategoryName.isEmpty()) {
                admin.changeCategoryName(currCellCategory, newCategoryName);
                NavigationController.loadCenter("/views/admin_manageCategories.fxml");
            }
            else {
                NavigationController.loadCenter("/views/admin_manageCategories.fxml");
            }
        }
        catch (UserNotFoundException e) {
            // admin not found in the library by findAdmin, log out automatically and tell admin to log in again.
            NavigationController.setMainLayout(null);
            NavigationController.setLoggedPerson(null);
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "/views/login.fxml");
        }
        catch (CategoryException e) {
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "");
        }
    }

    @FXML
    void deleteCategory(MouseEvent event) {
        try { 
            Admin admin = Library.findAdmin(NavigationController.getLoggedPerson().getUsername());
            Category currCellCategory = getItem();

            // ask for confirmation from the admin first
            ButtonType conf = NavigationController.showAlert(AlertType.CONFIRMATION, "Delete Category : If you press OK this category and all its book will be deleted permanently.", "");
            if (conf == ButtonType.OK) {
                admin.deleteCategory(currCellCategory);
                NavigationController.loadCenter("/views/admin_manageCategories.fxml");
            }
        }
        catch (UserNotFoundException e) {
            // admin not found in the library by findAdmin, log out automatically and tell admin to log in again.
            NavigationController.setMainLayout(null);
            NavigationController.setLoggedPerson(null);
            NavigationController.showAlert(AlertType.ERROR, e.getMessage(), "/views/login.fxml");
        }
    }

    
    @Override
    protected void updateItem(Category category, boolean empty) {
        super.updateItem(category, empty);

        if (empty || category == null) {
            setText(null);
            setGraphic(null);
        } 
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listcell_adminCategory.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        
            vbox.prefWidthProperty().bind(getListView().widthProperty());

            categoryName.setText(category.getName());
            
            setText(null);
            setGraphic(vbox);
        }
    }
}
