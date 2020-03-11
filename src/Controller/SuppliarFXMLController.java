/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Isuru Pathum
 */
public class SuppliarFXMLController implements Initializable {

    @FXML
    TextField sup_tx_supname, sup_tx_supcode, sup_tx_person, sup_tx_number, sup_tx_location;
    @FXML
    Button sup_btn_save;
    @FXML
    ComboBox sup_combo_status;

    String selectstatus = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sup_combo_status.getItems().addAll("Active", "Deactive");
        sup_combo_status.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue.equals("Active")) {
                selectstatus = "1";
            } else if (newValue.equals("Deactive")) {
                selectstatus = "0";
            }

        });
        sup_btn_save.setOnAction((event) -> {

            boolean state = true;
            TextField tx[] = {sup_tx_supname, sup_tx_supcode, sup_tx_person, sup_tx_number, sup_tx_location};
            for (int i = 0; i < tx.length; i++) {

                if (tx[i].getText().isEmpty() == true) {
                    state = false;
                }

            }
            if (state == true) {

                Model.AlertDialog alert = new Model.AlertDialog();
                String status = alert.AlertDialog_cofirm_yes_no("Add " + sup_tx_supname.getText(), Alert.AlertType.CONFIRMATION);
                if (status.equals("yes")) {
                    status = add_suppliar();
                    if (status.equals("save")) {
                        alert.AlertDialog_submit("Suppliar Registerd Successfull", Alert.AlertType.INFORMATION);
                    }
                }
            }
        });
    }

    private String add_suppliar() {
        String returnmsg = "";
        try {
            Model.DB.DBConn().createStatement().executeUpdate("INSERT INTO supplier_master(name, code, location, contact_number, contact_person, status, reg_date) VALUES('" + sup_tx_supname.getText() + "','" + sup_tx_supcode.getText() + "','" + sup_tx_location.getText() + "','" + sup_tx_number.getText() + "','" + sup_tx_person.getText() + "','" + selectstatus + "','" + sdf.format(new Date()) + "')");
            returnmsg = "save";
        } catch (Exception e) {
            returnmsg = "error";
            System.out.println(e);
        }
        return returnmsg;
    }

}
