package yummycode.admin.controller.tablecontroller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

public class EditTableController implements Initializable {
    @FXML
    private TextField txtTableID;

    @FXML
    private TextField txtTableName;

    @FXML
    private ChoiceBox<String> cbTableStatus;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtSeatsNumber;

    @FXML
    private TextField txtFloorsNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTableStatus.getItems().add("Empty");
        cbTableStatus.getItems().add("Inactive");
        cbTableStatus.getItems().add("Full");
        cbTableStatus.setValue("Empty");
       
    }
    public void LoadData(TableMap tbl) {
        String seat = String.valueOf(tbl.getSeatsNumber());
        String floor = String.valueOf(tbl.getFloorsNumber());
        txtTableID.setText(tbl.getTableId());
        txtTableID.setEditable(false);
        txtTableName.setText(tbl.getTableName());
        txtSeatsNumber.setText(seat);
        txtFloorsNumber.setText(floor);
        cbTableStatus.setValue(tbl.getTableStatus());
    }
    @FXML
    void onClickSubmit(ActionEvent event) throws SQLException, IOException {
        AddNewTableController add = new AddNewTableController();
        String id = txtTableID.getText();
        String name = txtTableName.getText();
        String seat = txtSeatsNumber.getText();
        String floor = txtFloorsNumber.getText();
        String status = cbTableStatus.getValue();
        if(!add.checkIDRegex(id)){
            Navigator.getInstance().showAlert(AlertType.ERROR, "This id includes 'T' and numbers from 0-9 ");
         }else if(!add.checkNumberRegex(seat)||!add.checkNumberRegex(floor)){
           Navigator.getInstance().showAlert(AlertType.ERROR, "This is not a number.Please input the number!");
         }else{
             TableMap tbl = new TableMap(id,name,Integer.parseInt(seat),Integer.parseInt(floor),status);
             if (TableMapDbHelper.editTableMap(tbl)){
                 Navigator.getInstance().goToTableMap();
             }
         }

    }
}
