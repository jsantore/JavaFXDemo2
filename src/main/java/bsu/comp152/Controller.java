package bsu.comp152;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ListView<DataHandler.recipeDataType> ListControl;
    private DataHandler Model;
    private String dishType;

    public void loadData(){
        var site = "http://www.recipepuppy.com/api/?";
        var params = getQueryParameters();
        var query = site+params;

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
        TextInputDialog answer = new TextInputDialog("mushrooms");
        answer.setHeaderText("Gathering Information");
        answer.setContentText("What ingredients do you want, you can use multiple ingredients separated by commas");
        Optional<String> result = answer.showAndWait();
        if (result.isPresent())
            return result.get();
        else
            return "";
    }

    private String getDishType(){
        return dishType;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //loadData();
        dishType = "";
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
