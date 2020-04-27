package bsu.comp152;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UnivWindow implements Initializable {
    @FXML
    private ListView UnivList;

    public ArrayList<UnivModel.APIUniv> loadData(){
        String apiLocation = "http://universities.hipolabs.com/search?name=junior";
        var UnivDataModel = new UnivModel(apiLocation);
        var univData = UnivDataModel.getData();
        return univData;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var data = loadData();
        var observableData = FXCollections.observableArrayList(data);
        UnivList.setItems(observableData);
    }
}
