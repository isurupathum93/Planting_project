/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ProductFXMLController implements Initializable {

    @FXML
    TextField cat_tx_category_name, cat_tx_category_code, cat_tx_category_desc, cat_tx_sub_cat_name, cat_tx_sub_desc, cat_tx_sub_code;
    @FXML
    Button cat_btn_add_category, cat_btn_category_save_status, sub_btn_add;
    @FXML
    ComboBox cat_combo_category, cat_combo_category_status, cat_combo_category_level;
    String categoryID = "";
    String combocategory = "";
    String combostatus = "";
    String combocatlevel = "";

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        load_categoryid();
        set_category_items_from_DB();
        cat_btn_add_category.setOnAction((event) -> {
            boolean state = true;
            TextField tx[] = {cat_tx_category_name, cat_tx_category_code, cat_tx_category_desc};
            for (int i = 0; i < tx.length; i++) {

                if (tx[i].getText().isEmpty() == true) {
                    state = false;
                }

            }
            if (state == true) {

                Model.AlertDialog alert = new Model.AlertDialog();
                String status = alert.AlertDialog_cofirm_yes_no("Save " + cat_tx_category_name.getText(), Alert.AlertType.CONFIRMATION);
                if (status.equals("yes")) {

                    status = save_category();
                    if (status.equals("save")) {
                        alert.AlertDialog_submit("Saved Category Successfull", Alert.AlertType.INFORMATION);
                        clear_category_added();
                        load_categoryid();
                        set_category_items_from_DB();
                    } else {
                        alert.AlertDialog_submit("Category Saved Error", Alert.AlertType.ERROR);
                    }
                }
            }
        });
        cat_combo_category.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            combocategory = (String) newValue;

        });
        cat_combo_category_status.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            //combostatus = (String) newValue;
            String ar[] = {"Active", "Deactive", "Discontinue"};

            for (int i = 0; i < ar.length; i++) {
                if (ar[i].equals(newValue)) {
                    combostatus = String.valueOf(i);
                }
            }
        });
        cat_combo_category_level.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            //String ar[] = {"Active", "Deactive", "Discontinue"};
            String ar[] = {"Sale Only",
                "Sale and Package",
                "package"};
            for (int i = 0; i < ar.length; i++) {
                if (ar[i].equals(newValue)) {
                    combocatlevel = String.valueOf(i);
                }
            }
            //combocatlevel = (String) newValue;
        });
        cat_btn_category_save_status.setOnAction((event) -> {
            Model.AlertDialog model = new Model.AlertDialog();
            if (combocategory.equals("")) {
                System.out.println("catgory");
            } else if (combostatus.equals("")) {
                System.out.println("statys");
            } else if (combocatlevel.equals("")) {
                System.out.println("level");
            } else {
                String state = model.AlertDialog_cofirm_yes_no("Update " + combocategory, Alert.AlertType.CONFIRMATION);
                if (state.equals("yes")) {

                    state = update_category();
                    if (state.equals("save")) {

                        model.AlertDialog_submit("Category Item Updated Successfull", Alert.AlertType.INFORMATION);
                    }
                }
            }
        });
        //sub category
        sub_btn_add.setOnAction((event) -> {
            boolean state = true;
            TextField tx[] = {cat_tx_sub_cat_name, cat_tx_sub_desc, cat_tx_sub_code};
            for (TextField tx1 : tx) {
                if (tx1.getText().isEmpty() == true) {
                    state = false;
                }
            }
            if (state == true) {
                Model.AlertDialog alert = new Model.AlertDialog();
                String status = alert.AlertDialog_cofirm_yes_no("Add " + cat_tx_category_name.getText(), Alert.AlertType.CONFIRMATION);
                if (status.equals("yes")) {

                    status = create_sub_category1();
                    if (status.equals("save")) {
                        alert.AlertDialog_submit("Sub Category Added Successfull", Alert.AlertType.INFORMATION);
                    }
                }
            }
        });
    }

    private void load_categoryid() {
        Model.Global_variable.product.clear();
        try {
            ResultSet rs = Model.DB.DBConn().createStatement().executeQuery("SELECT * FROM `category`");
            while (rs.next()) {
                //System.out.println(rs);
                /*
                 if (rs.getString(1) == null) {
                 System.out.println("null");
                 categoryID = "1";
                 } else {
                 categoryID = rs.getString(1);
                 }
                 */
                Model.category cat = new Model.category();
                cat.setCategoryid(rs.getString(1));
                cat.setCategoryname(rs.getString(2));
                cat.setCategoryCode(rs.getString(3));
                cat.setCategoryStatus(rs.getString(4));
                cat.setCategoryAdjust(rs.getString(5));
                Model.Global_variable.product.add(cat);
            }
            if (Model.Global_variable.product.isEmpty()) {
                categoryID = "1";
            } else {
                categoryID = String.valueOf(Model.Global_variable.product.size() + 1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String save_category() {
        String retunmsg = "";
        try {

            Model.DB.DBConn().createStatement().executeUpdate("INSERT INTO category(idCategory, category_name, cate_code, description) VALUES ('" + categoryID + "','" + cat_tx_category_name.getText() + "','" + cat_tx_category_code.getText() + "','" + cat_tx_category_desc.getText() + "')");
            retunmsg = "save";
        } catch (Exception e) {
            retunmsg = "error";
            System.out.println(e);
        }
        return retunmsg;
    }

    private String update_category() {
        String retunrmsg = "";
        try {
            Model.DB.DBConn().createStatement().executeUpdate("UPDATE category SET status='" + combostatus + "', adjust = '" + combocatlevel + "' WHERE category_name='" + combocategory + "'");
            retunrmsg = "save";
        } catch (Exception e) {
            retunrmsg = " error";
        }
        return retunrmsg;
    }

    private void clear_category_added() {

        TextField tx[] = {cat_tx_category_name, cat_tx_category_code, cat_tx_category_desc};
        for (int i = 0; i < tx.length; i++) {

            tx[i].setText("");

        }
    }

    private void set_category_items_from_DB() {
        try {
            //ObservableList<String> options = FXCollections.observableArrayList();
            cat_combo_category.getItems().clear();
            cat_combo_category_status.getItems().clear();
            cat_combo_category_level.getItems().clear();
            for (int i = 0; i < Model.Global_variable.product.size(); i++) {
                //options.add(Model.Global_variable.product.get(i).getCategoryname());
                cat_combo_category.getItems().add(Model.Global_variable.product.get(i).getCategoryname());
            }
            cat_combo_category_status.getItems().addAll("Active", "Deactive", "Discontinue");
            cat_combo_category_level.getItems().addAll(
                    "Sale Only",
                    "Sale and Package",
                    "package"
            );
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String create_sub_category1() {
        String returnmsg = "";
        try {
            Model.DB.DBConn().createStatement().executeUpdate("INSERT INTO sub_category_01(sub_name, description, cate_sub_code) VALUES('" + cat_tx_sub_cat_name.getText() + "','" + cat_tx_sub_desc.getText() + "','" + cat_tx_sub_code.getText() + "')");
            returnmsg = "save";
        } catch (Exception e) {
            System.out.println(e);
            returnmsg = "error";
        }
        return returnmsg;
    }
}
