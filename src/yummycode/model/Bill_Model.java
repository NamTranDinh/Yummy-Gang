package yummycode.model;

public class Bill_Model {
    // String idBill;
    // String date;
    // String tableID;
    String colNameFood;
    int colAmount;
    Double colUnitPrice;
    Double IntoMoney;

    // Double TotalMoney;
    // int voucher;
    // Double TotalPay;
    public Bill_Model(String colNameFood, int colAmount, Double colUnitPrice) {
        this.colNameFood = colNameFood;
        this.colAmount = colAmount;
        this.colUnitPrice = colUnitPrice;
        this.IntoMoney = colAmount * colUnitPrice;
    }

    public String getColNameFood() {
        return colNameFood;
    }

    public void setColNameFood(String colNameFood) {
        this.colNameFood = colNameFood;
    }

    public int getColAmount() {
        return colAmount;
    }

    public void setColAmount(int colAmount) {
        this.colAmount = colAmount;
    }

    public Double getColUnitPrice() {
        return colUnitPrice;
    }

    public void setColUnitPrice(Double colUnitPrice) {
        this.colUnitPrice = colUnitPrice;
    }

    public Double getIntoMoney() {
        return IntoMoney;
    }

    public void setIntoMoney(Double intoMoney) {
        IntoMoney = intoMoney;
    }

}
