package bsu.comp152;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        var loc = getClass().getResource("Main.fxml");
        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e){
            System.out.println("Couldn't Find FXML file!!!!!!");
        }
        Scene windowContents = new Scene(root, 300,400);
        primaryStage.setScene(windowContents);
        primaryStage.setTitle("Demo to Nudge you in the right direction");
        primaryStage.show();
    }

    @FXML
    public void exit(ActionEvent event){
        System.exit(0);//alls well - bye bye
    }

    @FXML
    public  void openRecipeWindow(ActionEvent event){
        Parent root = null;
        var loc = getClass().getResource("RecipeWindow.fxml");
        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e){
            System.out.println("Couldn't Find FXML file!!!!!!");
        }
        //here is where we are creating the new window
        Scene windowContents = new Scene(root, 900,400);
        Stage recipeWindow = new Stage();
        recipeWindow.setScene(windowContents);
        recipeWindow.setTitle("Recipe Finder Demo");
        recipeWindow.show();
    }
}
