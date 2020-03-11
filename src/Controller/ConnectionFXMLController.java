/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author EP33950
 */
public class ConnectionFXMLController implements Initializable {

    @FXML
    Button btn_Connect_test, btn_Connect_save;
    @FXML
    TextField txt_Connect_host, txt_Connect_dbname, txt_Connect_user, txt_Connect_password, txt_Connect_port;
    @FXML
    ComboBox combo_Connect_type;

    Stage stage;
    Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextField text[] = {txt_Connect_host, txt_Connect_dbname, txt_Connect_user, txt_Connect_password, txt_Connect_port};
        for (int i = 0; i < text.length; i++) {
            TextField r = text[i];
            r.setEditable(false);
        }
        ObservableList<String> options = FXCollections.observableArrayList(
                "MsSQL",
                "PostgreSQL"
        );
        //final ComboBox comboBox = new ComboBox(options);
        combo_Connect_type.setItems(options);
        combo_Connect_type.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    textFiledAccess(1);
                } else {
                    System.out.println("no");
                }

            }
        });
//        txt_Connect_host.setOnKeyReleased((KeyEvent event) -> {
//            if ((event.getCode() == KeyCode.ENTER) && (txt_Connect_host.getText().length() > 0)) {
//                textFiledAccess(2);
//            }
//        });
//        txt_Connect_dbname.setOnKeyReleased((event) -> {
//            if ((event.getCode() == KeyCode.ENTER) && (txt_Connect_dbname.getText().length() > 0)) {
//                textFiledAccess(3);
//            }
//        });
//        txt_Connect_user.setOnKeyReleased((event) -> {
//            if ((event.getCode() == KeyCode.ENTER) && (txt_Connect_user.getText().length() > 0)) {
//                textFiledAccess(4);
//            }
//        });
//        txt_Connect_password.setOnKeyReleased((event) -> {
//            if ((event.getCode() == KeyCode.ENTER) && (txt_Connect_password.getText().length() > 0)) {
//                //all(5);
//            }
//        });

//        txt_Connect_host.setOnMousePressed((event) -> {
//            System.out.println("click k");
//        });
        for (int i = 0; i < text.length; i++) {
            TextField r = text[i];
            int _NowTxt = i;
            r.setOnMouseClicked((MouseEvent event) -> {

            });

            r.setOnKeyReleased((KeyEvent event) -> {
                r.setStyle("-fx-border-color: transparent;");
                int _nextTxtcount = _NowTxt;
                _nextTxtcount += 1;
                System.out.println(_nextTxtcount);
                if ((r.getText().length() > 0) && (event.getCode() == KeyCode.ENTER)) {

                    if (_nextTxtcount < text.length) {
                        TextField _nextTxt = text[_nextTxtcount];
                        _nextTxt.requestFocus();
                        _nextTxt.setEditable(true);
                    }

                } else if ((r.getText().length() == 0) && (event.getCode() == KeyCode.ENTER)) {
                    r.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 3px ; ");
                } else if (r.getText().length() > 0) {
                    if (_nextTxtcount < text.length) {
                        TextField _nextTxt = text[_nextTxtcount];
                        _nextTxt.setEditable(true);
                    }
                } else {
                    if (_nextTxtcount < text.length) {
                        TextField _nextTxt = text[_nextTxtcount];
                        _nextTxt.setEditable(false);
                    }
                }
            });
        }
        btn_Connect_test.setOnAction((event) -> {
            try (BufferedReader br = new BufferedReader(new FileReader("D:/DBTxt.txt"))) {
                for (String line; (line = br.readLine()) != null;) {
                    String ar[] = line.split("#");
                    System.out.println(line);
                }
            } catch (Exception e) {
                System.out.println(e);

            }
        });

        btn_Connect_save.setOnAction((event) -> {
            System.out.println("print");
            Model.Create_file m = new Model.Create_file();
            String result = m.DB_connection_Input_data(txt_Connect_dbname.getText(), txt_Connect_port.getText(), txt_Connect_user.getText(), txt_Connect_password.getText(), txt_Connect_host.getText());

            if (result.equals("Success")) {
                try {

                    stage = (Stage) btn_Connect_save.getScene().getWindow();
                    stage.close();
                    stage = new Stage();
                    root = FXMLLoader.load(ConnectionFXMLController.this.getClass().getResource("/View/LoginFXML.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    //Image icon = new Image(getClass().getResourceAsStream("/Image/supermarket.png"));
                    //stage.getIcons().add(icon);
                    stage.setTitle("JavaFx Pos");
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(btn_Connect_save.getScene().getWindow());
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

    }

    private void textFiledAccess(int count) {
        TextField text[] = {txt_Connect_host, txt_Connect_dbname, txt_Connect_user, txt_Connect_password};
        for (int i = 0; i < count; i++) {
            TextField r = text[i];
            r.setEditable(true);
            r.requestFocus();
        }
    }

}
