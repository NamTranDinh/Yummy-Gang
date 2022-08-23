package yummycode.admin.controller.billcontroller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import yummycode.admin.databasehelper.BillOrderDBHelper;
import yummycode.model.BillOrder_Model;
import yummycode.model.BillPayment_Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillPayment_Controller implements Initializable {
    @FXML
    private Label billCode;

    @FXML
    private Label date;

    @FXML
    private Label cashier;

    @FXML
    private Label table;

    @FXML
    private TableView<BillPayment_Model> tblBillPay;

    @FXML
    private TableColumn<BillPayment_Model, String> colNameFood;

    @FXML
    private TableColumn<BillPayment_Model, String> colAmount;

    @FXML
    private TableColumn<BillPayment_Model, String> colUnitPrice;

    @FXML
    private TableColumn<BillPayment_Model, String> colIntoMoney;

    @FXML
    private Label total;

    @FXML
    private Label voucher;

    @FXML
    private Label totalPay;

    @FXML
    private Label txtStatus;
    @FXML
    private Label txtBillID;

    public void LoadData(BillOrder_Model bill) {
        txtBillID.setText(bill.getBillId());
        System.out.println(txtBillID.getText());
        billCode.setText(bill.getDetailBillID());
        date.setText(bill.getDateTime());
        table.setText(bill.getTableID());
        total.setText(bill.getSumofPrice());
        txtStatus.setText(bill.getStatus());
        voucher.setText(bill.getSaleCode());

        ObservableList<BillPayment_Model> listBill = FXCollections.observableArrayList();
        List<BillPayment_Model> listData;
        listData = BillOrderDBHelper.getDataForBill(bill.getDetailBillID());
        System.out.println(listData.size() + "Detail");
        listBill.addAll(listData);
        tblBillPay.setItems(listBill);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNameFood.setCellValueFactory(CellData -> CellData.getValue().getFoodNameProperty());
        colAmount.setCellValueFactory(CellData -> CellData.getValue().getFoodQuantityProperty());
        colUnitPrice.setCellValueFactory(CellData -> CellData.getValue().getFoodPriceProperty());
        colIntoMoney.setCellValueFactory(CellData -> CellData.getValue().getSumOfUnitPriceProperty()); 
    }

}
