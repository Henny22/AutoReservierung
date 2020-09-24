/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
    import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
    
     private static Stage guiStage;
    
    public static Stage getStage(){
        return guiStage;
    }   
    
    @Override
    public void start(Stage stage) throws Exception {
        guiStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("LoginSystem.fxml"));   
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Sad Rent Car");
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}