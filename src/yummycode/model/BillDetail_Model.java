package yummycode.model;

public class BillDetail_Model {
    String BillID;
    String date;
    double totalPay;
    String idTable;
    int voucher;

    public BillDetail_Model(String billID, String date, String idTable, int voucher) {
        BillID = billID;
        this.date = date;
        this.idTable = idTable;
        this.voucher = voucher;
    }

    public String getBillID() {
        return BillID;
    }

    public void setBillID(String billID) {
        BillID = billID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public int getVoucher() {
        return voucher;
    }

    public void setVoucher(int voucher) {
        this.voucher = voucher;
    }

}