package bsu.comp152;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public CheckBox cheese;
    public CheckBox kale;
    public CheckBox chicken;
    public CheckBox rice;
    public CheckBox flour;
    @FXML
    private CheckBox mushrooms;
    @FXML
    private ListView<DataHandler.recipeDataType> ListControl;
    private ArrayList<CheckBox> allCheckboxes;
    private DataHandler Model;
    private String dishType;

    public void loadData(){
        var site = "http://www.recipepuppy.com/api/?";
        var params = getQueryParameters();
        var query = site+params;
        System.out.println(query);
        Model = new DataHandler(query);
        var recipeList = Model.getData();
        ObservableList<DataHandler.recipeDataType> dataToShow = FXCollections.observableArrayList(recipeList);
        ListControl.setItems(dataToShow);
    }

    public String getQueryParameters(){
        var dish = getDishType();
        var ingredients = getIngredients();
        return "i="+ingredients+"&q="+dish;
    }

    private String getIngredients(){
        var ingredients = "";
        for (var box : allCheckboxes){
            if (box.isSelected()){
                var text = box.getText();
                if(ingredients.length()>0){
                    ingredients = ingredients+","+text;
                }else
                    ingredients = text;
            }
        }
        return ingredients;
    }

    private String getDishType(){
        return dishType;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //loadData();
        dishType = "";
        allCheckboxes = new ArrayList<CheckBox>();
        allCheckboxes.add(mushrooms);
        allCheckboxes.add(cheese);
        allCheckboxes.add(chicken);
        allCheckboxes.add(kale);
        allCheckboxes.add(flour);
        allCheckboxes.add(rice);
        ListControl.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<DataHandler.recipeDataType>() {
                    @Override
                    public void changed(ObservableValue<? extends DataHandler.recipeDataType> observable, DataHandler.recipeDataType oldValue, DataHandler.recipeDataType newValue) {
                        var recipe = ListControl.getSelectionModel().getSelectedItem();
                        Alert recipeInfo = new Alert(Alert.AlertType.INFORMATION);
                        recipeInfo.setTitle("Info  for "+recipe.title);
                        recipeInfo.setHeaderText("Ingredients: "+ recipe.ingredients);
                        recipeInfo.setContentText("Here: "+recipe.href);
                        recipeInfo.showAndWait();
                    }
                }
        );
    }

    @FXML
    public void selectMenuItem(javafx.event.ActionEvent actionEvent) {
        var item =(MenuItem)actionEvent.getSource();
        dishType = item.getText();
    }


}
