package yummycode.admin.controller.tablecontroller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yummycode.admin.databasehelper.TableMapDbHelper;
import yummycode.admin.frontend.Navigator;
import yummycode.model.TableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddNewTableController implements Initializable {

    @FXML
    private TextField txtTableID;

    @FXML
    private TextField txtSeatsNumber;

    @FXML
    private TextField txtFloorsNumber;

    @FXML
    private ChoiceBox<String> cbTableStatus;

    @FXML
    private TextField txtTableName;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTableStatus.getItems().add("Empty");
        cbTableStatus.getItems().add("Inactive");
        cbTableStatus.getItems().add("Full");
        cbTableStatus.setValue("Empty");
    }

    @FXML
    void onClickCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToTableMap();
    }

    @FXML
    void onClickSubmit(ActionEvent event) throws SQLException, IOException {
        String id = txtTableID.getText();
        String name = txtTableName.getText();
        String seat = txtSeatsNumber.getText();
        String floor = txtFloorsNumber.getText();
        String status = cbTableStatus.getValue();
        if(!checkIDRegex(id)){
           Navigator.getInstance().showAlert(AlertType.ERROR, "This id includes 'T' and numbers from 0-9 ");
        }else if(!checkNumberRegex(seat)||!checkNumberRegex(floor)){
          Navigator.getInstance().showAlert(AlertType.ERROR, "This is not a number.Please input the number!");
        }else{
            TableMap tbl = new TableMap(id,name,Integer.parseInt(seat),Integer.parseInt(floor),status);
            if (TableMapDbHelper.addNewTable(tbl)){
                Navigator.getInstance().goToTableMap();
            }
        }
    }
    public static Boolean checkIDRegex(String id) {
        String regex = "^(?=.*T)(?=.*[0-9]).{3,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        boolean match = matcher.matches();
        return match;
    }
    public static Boolean checkNumberRegex(String a){
        String regex = "^[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(a);
        boolean match = matcher.matches();
        return match;
    }
}
