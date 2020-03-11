/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planting_project;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Isuru Pathum
 */
public class Planting_project extends Application {

    public static Stage primary;

    @Override
    public void start(Stage stage) throws IOException {

        Model.Create_file m = new Model.Create_file();
        String result = m.DB_connection_file();
        if (result.equals("override")) {

            Parent root = FXMLLoader.load(getClass().getResource("/View/MainFrameFXML.fxml"));
            Scene scene = new Scene(root);
            //Image icon = new Image(getClass().getResourceAsStream("/Image/supermarket.png"));
            //stage.getIcons().add(icon);
            stage.setTitle("Planting");
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } else if (result.equals("created")) {
            Parent root = FXMLLoader.load(getClass().getResource("/View/ConnectionFXML.fxml"));
            Scene scene = new Scene(root);
            //Image icon = new Image(getClass().getResourceAsStream("/Image/supermarket.png"));
            //stage.getIcons().add(icon);
            stage.setTitle("Planting");
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
