package yummycode.model;

public class Payment_Model {
    double price;
    String tableID;
    int member;
    String idBill;
    String dateTime;
    int sale;

    

    public Payment_Model(double price, String tableID, int member, String idBill, String dateTime, int sale) {
        this.price = price;
        this.tableID = tableID;
        this.member = member;
        this.idBill = idBill;
        this.dateTime = dateTime;
        this.sale = sale;

    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

}
