package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;

import models.Category;
import models.Library;


public class AdminManageCategoriesController implements Initializable {

    @FXML
    private TextField newCategory_input;
    @FXML
    private Button addCategory_btn;
    @FXML
    private ListView<Category> categoryList;


    //hover effect for Buttons
    @FXML
    void hoverActivated(MouseEvent event) {
        addCategory_btn.setBlendMode(BlendMode.MULTIPLY);
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        addCategory_btn.setBlendMode(BlendMode.SRC_OVER);
    }

    @FXML
    void addCategory(MouseEvent event) {
        String newCategory = newCategory_input.getText().replaceAll("\\s+", " ");
        Library.createCategory(newCategory);
        NavigationController.loadCenter("/views/admin_manageCategories.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Category> observableBooklist = FXCollections.observableArrayList();
        List<Category> categories = Library.getAllCategories();

        observableBooklist.addAll(categories);
        
        categoryList.setItems(observableBooklist);
        categoryList.setCellFactory(booklist -> new ListCellAdminCategory());   
    }

}
