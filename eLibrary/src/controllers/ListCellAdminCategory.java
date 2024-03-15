package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());
        String currCellCategoryName = getItem().getName();
        String newCategoryName = newName_input.getText().replaceAll("\\s+", " ");

        if (admin != null){
            admin.changeCategoryName(currCellCategoryName, newCategoryName);
            NavigationController.loadCenter("/views/admin_manageCategories.fxml");
        }
    }

    @FXML
    void deleteCategory(MouseEvent event) {
        Admin admin = Library.getCurrAdmin(NavigationController.getLoggedPerson());
        Category currCellCategory = getItem();

        if (admin != null){
            admin.deleteCategory(currCellCategory);
            NavigationController.loadCenter("/views/admin_manageBooks.fxml");
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
