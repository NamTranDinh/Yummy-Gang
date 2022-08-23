package yummycode.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BillOrder_Model {
    // BillID,Datetime,TableID,DetailBillID,SumOfPrice,SeeDetail.
    private StringProperty BillID;
    private StringProperty DateTime;
    private StringProperty TableID;
    private StringProperty DetailBillID;
    private StringProperty SumOfPrice;
    private StringProperty Status;
    private StringProperty SaleCode;
    private StringProperty Discount;
    
    public BillOrder_Model() {

    }

    public BillOrder_Model(String billId, String dateTime, String tableId, String detailBillId, String sumOfPrice,String Status,String SaleCode, String Discount) {
        this.BillID = new SimpleStringProperty(billId);
        this.DateTime = new SimpleStringProperty(dateTime);
        this.TableID = new SimpleStringProperty(tableId);
        this.DetailBillID = new SimpleStringProperty(detailBillId);
        this.SumOfPrice = new SimpleStringProperty(sumOfPrice);
        this.Status = new SimpleStringProperty(Status);
        this.SaleCode  = new SimpleStringProperty(SaleCode);
        this.Discount = new SimpleStringProperty(Discount);
    }

    public void setBillId(String Billid) {
        this.BillID.setValue(Billid);
    }

    public void setDateTime(String dateTime) {
        this.DateTime.setValue(dateTime);
    }

    public void setTableID(String tableId) {
        this.TableID.setValue(tableId);
    }

    public void setDetailBillID(String detailBillId) {
        this.DetailBillID.setValue(detailBillId);
    }

    public void setSumofPrice(String sumOfprice) {
        this.SumOfPrice.setValue(sumOfprice);
    }
    public void setStatus(String status){
        this.Status.setValue(status);
    }
    public void setSaleCode(String SaleCode){
        this.SaleCode.setValue(SaleCode);
    }
    public void setDiscount(String Discount){
        this.Discount.setValue(Discount);
    }
    public String getBillId() {
        return BillID.getValue();
    }

    public String getDateTime() {
        return DateTime.getValue();
    }

    public String getTableID() {
        return TableID.getValue();
    }

    public String getDetailBillID() {
        return DetailBillID.getValue();
    }

    public String getSumofPrice() {
        return SumOfPrice.getValue();
    }
    public String getStatus() {
        return Status.getValue();
    }
    public String getSaleCode() {
        return SaleCode.getValue();
    }
    public String getDiscount() {
        return  Discount.getValue();
    }
    public StringProperty getBillIdProperty() {
        return BillID;
    }

    public StringProperty getDateTimeProperty() {
        return DateTime;
    }

    public StringProperty getTableIdProperty() {
        return TableID;
    }

    public StringProperty getDetailBillIdProperty() {
        return DetailBillID;
    }

    public StringProperty getSumOfPriceProperty() {
        return SumOfPrice;
    }
    public StringProperty getStatusProperty() {
        return Status;
    }
    public StringProperty getSaleCodeProperty() {
        return SaleCode;
    }
    public StringProperty getDiscountProperty() {
        return Discount;
    }

}
