/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Isuru Pathum
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField Tx_login_username;
    @FXML
    private TextField tx_login_password;
    @FXML
    private Button btn_Login_Login;

    Stage stage;
    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Tx_login_username.
        Tx_login_username.setPromptText("Enter Your User Name");
        tx_login_password.setPromptText("Enter Your Password");
        btn_Login_Login.setOnAction((ActionEvent event) -> {
            if (Tx_login_username.getText().length() == 0) {
                alertbox(AlertType.ERROR, "Error", "Please Insert User Name", 1);
            } else if (tx_login_password.getText().length() == 0) {
                alertbox(AlertType.ERROR, "Error", "Please Insert Password", 2);
            } else {
                login(btn_Login_Login);
            }
        });
    }

    private void alertbox(AlertType type, String title, String message, int textf) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        if (textf == 1) {
            Tx_login_username.requestFocus();
        } else if (textf == 2) {
            tx_login_password.requestFocus();
        }
    }

    private void login(Button btn) {

        try {
            stage = (Stage) btn.getScene().getWindow();
            stage.close();
            stage = new Stage();
            root = FXMLLoader.load(LoginFXMLController.this.getClass().getResource("/View/MainFrameFXML1.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //Image icon = new Image(getClass().getResourceAsStream("/Image/supermarket.png"));
            //stage.getIcons().add(icon);
            stage.setTitle("JavaFx Pos");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btn.getScene().getWindow());
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
