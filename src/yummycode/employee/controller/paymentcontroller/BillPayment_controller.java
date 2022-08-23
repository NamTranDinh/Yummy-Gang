package yummycode.employee.controller.paymentcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import yummycode.admin.frontend.Navigator;
import yummycode.employee.databasehelper.FoodManagerDBHelper;
import yummycode.model.BillDetail_Model;
import yummycode.model.Bill_Model;

public class BillPayment_controller implements Initializable {

    public static BillDetail_Model bill;

    public static String tableID;

    @FXML
    private Label billCode;

    @FXML
    private Label date;

    @FXML
    private Label table;

    @FXML
    private TableView<Bill_Model> tblBillPay;

    @FXML
    private TableColumn<Bill_Model, String> colNameFood;

    @FXML
    private TableColumn<Bill_Model, Integer> colAmount;

    @FXML
    private TableColumn<Bill_Model, Double> colUnitPrice;

    @FXML
    private TableColumn<Bill_Model, Double> colIntoMoney;

    @FXML
    private Label total;

    @FXML
    private Label voucher;

    @FXML
    private Label totalPay;
    
    ObservableList<Bill_Model> billDetail = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        billDetail.addAll(FoodManagerDBHelper.getDataForBill(bill.getBillID()));
        tblBillPay.setItems(billDetail);
        colNameFood.setCellValueFactory(new PropertyValueFactory<>("colNameFood"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("colAmount"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("colUnitPrice"));
        colIntoMoney.setCellValueFactory(new PropertyValueFactory<>("IntoMoney"));

        loadData(bill);
    }

    @FXML
    void clickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoPayment();
    }

    public void loadData(BillDetail_Model bill) {
        double totalMoney = 0.0;
        for (Bill_Model bill_Model : billDetail) {
            System.out.println(bill_Model.getIntoMoney());
            totalMoney += bill_Model.getIntoMoney();
            
        }
        total.setText(String.valueOf(totalMoney));
        voucher.setText(String.valueOf(bill.getVoucher()));
        Double voucherr = Double.parseDouble(voucher.getText());
        double totalpay = totalMoney * ((100 - voucherr) / 100);
        totalPay.setText(String.valueOf(totalpay));
        billCode.setText(bill.getBillID());
        date.setText(bill.getDate());
        
        table.setText(bill.getIdTable());

        
    }

}
