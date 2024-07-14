package com.mycompany.imageviewnodeinfx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    public void start(Stage stage) throws IOException {

    FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
    Parent root = loader.load();
    
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();     
    stage.setResizable(false);
    
    Image ico = new Image(App.class.getResourceAsStream("/AMPM/c.png"));
    stage.setTitle("Clock");
    stage.getIcons().add(ico);
        
    }
    

    public static void main(String[] args) {
        launch();
    }

}