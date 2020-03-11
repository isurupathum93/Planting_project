/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Isuru Pathum
 */
public class CustomerController implements Initializable {

    @FXML
    TextField cus_tx_number, cus_tx_fisrtname, cus_tx_lastname, cus_tx_contact, cus_tx_email;
    @FXML
    TextArea cus_tx_address, cus_tx_remark;
    @FXML
    Button cus_btn_save;
    int btntype = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load_cus_number();
        cus_btn_save.setOnAction((event) -> {
            boolean status = true;
            TextField text[] = {cus_tx_number, cus_tx_fisrtname, cus_tx_lastname, cus_tx_contact, cus_tx_email};
            for (int i = 0; i < text.length; i++) {
                TextField r = text[i];
                if (r.getText().isEmpty() == true) {
                    //r.setText("error");
                    status = false;
                    r.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 2px ; ");
                }
                //r.setEditable(false);
            }
            if (status == true) {
                Model.AlertDialog confirm = new Model.AlertDialog();
                if (confirm.AlertDialog_cofirm_yes_no("Save", Alert.AlertType.CONFIRMATION).equals("yes")) {

                    try {
                        Model.DB.DBConn().createStatement().executeUpdate("INSERT INTO customer_register(idCustomer_register,cus_name, Address, contact_number, Email, remark, rating) VALUES ('" + cus_tx_number.getText() + "','" + cus_tx_fisrtname.getText() + "#" + cus_tx_lastname.getText() + "','" + cus_tx_address.getText() + "','" + cus_tx_contact.getText() + "','" + cus_tx_email.getText() + "','" + cus_tx_remark.getText() + "','1')");

                        confirm.AlertDialog_submit("Customer Register Successfull", Alert.AlertType.INFORMATION);
                        clearAll();
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                } else {

                }
            }
        });
    }

    private void load_cus_number() {
        try {

            ResultSet rs = Model.DB.DBConn().createStatement().executeQuery("SELECT max(idCustomer_register)+1 FROM customer_register ");
            while (rs.next()) {
                cus_tx_number.setText(rs.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void clearAll() {

        TextField text[] = {cus_tx_number, cus_tx_fisrtname, cus_tx_lastname, cus_tx_contact, cus_tx_email};
        for (int i = 0; i < text.length; i++) {
            TextField r = text[i];
            r.setText("");
            r.setStyle("-fx-border-color: blue ; -fx-border-width: 2px ; -fx-border-radius: 2px ; ");
            //r.setEditable(false);
        }
        TextArea area[] = {cus_tx_address, cus_tx_remark};
        for (int i = 0; i < area.length; i++) {
            TextArea ar = area[i];
            ar.setText("");
            ar.setStyle("-fx-border-color: blue ; -fx-border-width: 2px ; -fx-border-radius: 2px ; ");
        }
    }
}
