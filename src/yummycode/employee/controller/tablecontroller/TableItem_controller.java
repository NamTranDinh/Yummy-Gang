package yummycode.employee.controller.tablecontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import yummycode.admin.frontend.Navigator;
import yummycode.employee.controller.FoodItemController;
import yummycode.employee.controller.FoodMenuController;
import yummycode.employee.controller.paymentcontroller.BillPayment_controller;
import yummycode.employee.controller.paymentcontroller.Payment_controller;
import yummycode.employee.databasehelper.FoodManagerDBHelper;
import yummycode.model.TableMap;

public class TableItem_controller {

    TableMap infTbl;
    String codeBill;
    public static final String STATUS_EMPTY = "Empty";
    public static final String STATUS_SERVING = "Serving";
    public static final String STATUS_RESERVE = "Reserve";
    public static final String STATUS_MAINTENANCE = "Maintenance";

    @FXML
    private Button textButton;

    @FXML
    void onclickChooseTable(ActionEvent event) throws IOException {
        String addBillCode = FoodManagerDBHelper.getBillCodeOfTable(infTbl.getTableID());
        if (addBillCode == null) {
            codeBill = Navigator.randomAlphaNumeric(4);
            Navigator.getInstance().goTableMapFoodMenu(infTbl, codeBill);
        } else {
            Navigator.getInstance().goTableMapFoodMenu(infTbl, addBillCode);
        }
        // Payment_controller.idTable = infTbl.getTableID();
        BillPayment_controller.tableID = infTbl.getTableID();

    }

    public void setData(TableMap tbl) {
        textButton.setText(tbl.getTableID());
        infTbl = tbl;

        if (tbl.getTblStatus().equals(STATUS_EMPTY)) {
            textButton.setStyle("-fx-background-color: #2062c9; -fx-background-radius: 12;");
        } else if (tbl.getTblStatus().equals(STATUS_SERVING)) {
            textButton.setStyle("-fx-background-color: #96be25; -fx-background-radius: 12;");
        } else if (tbl.getTblStatus().equals(STATUS_RESERVE)) {
            textButton.setStyle("-fx-background-color: #e28743; -fx-background-radius: 12;");
        } else if (tbl.getTblStatus().equals(STATUS_MAINTENANCE)) {
            textButton.setStyle("-fx-background-color: #7d0600; -fx-background-radius: 12;");
        }
    }

}
