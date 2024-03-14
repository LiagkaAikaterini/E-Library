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

import models.Category;


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
        deleteCategory_btn.setStyle("-fx-background-color: #CC0000");
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        changeName_btn.setStyle("-fx-background-color: #B69E7A");
        deleteCategory_btn.setStyle("-fx-background-color: #E74C3C");
    }


    @FXML
    void changeCategoryName(MouseEvent event) {
        //??????????????????????????????????????????????????????
    }

    @FXML
    void deleteCategory(MouseEvent event) {
        //??????????????????????????????????????????????????????????
    }

    
    @Override
    protected void updateItem(Category category, boolean empty) {
        super.updateItem(category, empty);

        if (empty || category == null) {
            setText(null);
            setGraphic(null);
        } 
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listcell_adminUser.fxml"));
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
