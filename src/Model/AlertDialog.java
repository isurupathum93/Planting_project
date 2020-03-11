/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Isuru Pathum
 */
public class AlertDialog {

    int btntype = 0;

    public String AlertDialog_cofirm_yes_no(String text, Alert.AlertType type) {
        //Alert.AlertType.CONFIRMATION
        String returnValue = "";
        Alert alert = new Alert(type, "Are You Sure Want to " + text + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        Stage stage1 = (Stage) alert.getDialogPane().getScene().getWindow();
        stage1.getIcons().add(new Image("/Image/informationI.png"));

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/CSS/Alertdialog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.YES);
        yesButton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        yesButton.setDefaultButton(false);

        Button nOButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
        nOButton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        nOButton.setDefaultButton(false);

        Button CANELButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        CANELButton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        CANELButton.setDefaultButton(false);

        yesButton.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                btntype = 1;
                yesButton.setDefaultButton(true);
                nOButton.setDefaultButton(false);
                CANELButton.setDefaultButton(false);
            }
        });
        nOButton.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                btntype = 2;
                yesButton.setDefaultButton(false);
                nOButton.setDefaultButton(true);
                CANELButton.setDefaultButton(false);
            }
        });
        CANELButton.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                btntype = 3;
                yesButton.setDefaultButton(false);
                nOButton.setDefaultButton(false);
                CANELButton.setDefaultButton(true);
            }
        });
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES && btntype == 1) {
            System.out.println("btn" + "yes");
            returnValue = "yes";
        } else if (alert.getResult() == ButtonType.NO && btntype == 2) {
            System.out.println("btn" + "no");
            returnValue = "no";
        } else if (alert.getResult() == ButtonType.CANCEL && btntype == 3) {
            System.out.println("btn" + "cancel");
            returnValue = "cancel";
        }

        return returnValue;
    }

    public String AlertDialog_submit(String text, Alert.AlertType type) {
        //Alert.AlertType.CONFIRMATION
        String returnValue = "";
        Alert alert = new Alert(type, text + " ", ButtonType.OK);
        Stage stage1 = (Stage) alert.getDialogPane().getScene().getWindow();
        stage1.getIcons().add(new Image("/Image/informationI.png"));

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/CSS/Alertdialog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        yesButton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        yesButton.setDefaultButton(false);

        yesButton.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                btntype = 1;
                yesButton.setDefaultButton(true);
            }
        });

        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK && btntype == 1) {
            System.out.println("btn" + "yes");
            returnValue = "yes";
        }
        return returnValue;
    }
}
