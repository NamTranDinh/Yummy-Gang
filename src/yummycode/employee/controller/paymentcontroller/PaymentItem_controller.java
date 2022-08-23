package yummycode.employee.controller.paymentcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import yummycode.model.BillDetail_Model;

public class PaymentItem_controller {

    @FXML
    private Label TotalPrice;

    @FXML
    private Label tableID;

    @FXML
    private Label member;

    private String BillIDD;

    private String dateTimee;

    private int sale;

    @FXML
    void onclickItem(MouseEvent event) {
        Payment_controller.idTable = tableID.getText();

        BillDetail_Model detail_Model = new BillDetail_Model(BillIDD, dateTimee, tableID.getText(), sale);
        BillPayment_controller.bill = detail_Model;
    }

    public void loadData(double price, String idTbl, int seat, String billID, String dateTime, int sale) {
        TotalPrice.setText(String.valueOf(price));
        tableID.setText(idTbl);
        member.setText(String.valueOf(seat));
        this.BillIDD = billID;
        this.dateTimee = dateTime;
        this.sale = sale;
    }

}
