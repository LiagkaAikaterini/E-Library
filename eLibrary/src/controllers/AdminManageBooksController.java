package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Category;
import models.Library;


public class AdminManageBooksController implements Initializable{

    @FXML
    private ScrollPane scrollpane;
    @FXML
    private VBox vbox;
    @FXML
    private Button addNewBook;

    //hover effect for Buttons
    @FXML
    void hoverActivated(MouseEvent event) {
        addNewBook.setBlendMode(BlendMode.MULTIPLY);
    }
    @FXML
    void hoverDeactivated(MouseEvent event) {
        addNewBook.setBlendMode(BlendMode.SRC_OVER);
    }

    @FXML
    void goToCreateBook(MouseEvent event) {
        NavigationController.loadCenter("/views/admin_createBook.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        vbox.prefWidthProperty().bind(scrollpane.widthProperty());
        vbox.prefHeightProperty().bind(scrollpane.heightProperty());

        for (Category cat : Library.getAllCategories()) {
            createCategoryBookList(cat);
        }
    }


    private void createCategoryBookList(Category category) {   
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin_categoryBookList.fxml"));
            AnchorPane anchorPane = loader.load();
            AdminCategoryBookListController contr = loader.getController();

            contr.setCategoryName(category.getName());
            contr.setBookList(category.getAllCategoryBooks());

            vbox.getChildren().add(anchorPane); // Add AnchorPane to the VBox
        }
        catch(Exception e) {
            e.printStackTrace();
        }  
    }

}
