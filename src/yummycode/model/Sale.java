package yummycode.model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Sale {
    private StringProperty SaleCode;
    private StringProperty DateStart;
    private StringProperty DateEnd;
    private StringProperty Decription;
    private ObjectProperty<Integer> Discount;
    private StringProperty SaleStatus;

    public Sale(){

    }
    public Sale(String SaleCode, String DateStart, String DateEnd, String Decription, int Discount,String SaleStatus){
        this.SaleCode = new SimpleStringProperty(SaleCode);
        this.DateStart = new SimpleStringProperty(DateStart);
        this.DateEnd = new SimpleStringProperty(DateEnd);
        this.Decription = new SimpleStringProperty(Decription);
        this.Discount = new SimpleObjectProperty<Integer>(Discount);
        this.SaleStatus = new SimpleStringProperty(SaleStatus);
    }

    public void setSaleCode(String SaleCode) {
        this.SaleCode.setValue(SaleCode);
    }
    public void setDateStart(String DateStart) {
        this.DateStart.setValue(DateStart);
    }
    public void setDateEnd(String DateEnd) {
        this.DateEnd.setValue(DateEnd);
    }
    public void setDecription(String Decription){
       this.Decription.setValue(Decription);
    }
    public void setDiscount(int Discount){
       this.Discount.setValue(Discount);
    }
    public void setSaleStatus(String SaleStatus) {
        this.SaleStatus.setValue(SaleStatus);
    }
    public String getSaleCode(){
        return SaleCode.getValue();
    }
    public String getDateStart(){
        return DateStart.getValue();
    }
    public String getDateEnd(){
        return DateEnd.getValue();
    }
    public String getDecription(){
        return Decription.getValue();
    }
    public int getDiscount(){
        return Discount.getValue();
    }
    public String getSaleStatus(){
        return SaleStatus.getValue();
    }
    public StringProperty getSaleCodeProperty() {
        return SaleCode;
    }
    public StringProperty getDateStartProperty(){
        return DateStart;
    }
    public StringProperty getDateEndProperty(){
        return DateEnd;
    }
    public StringProperty getDecriptionProperty(){
        return Decription;
    }
    public  ObjectProperty<Integer> getDiscountProperty(){
        return Discount;
    }
    public StringProperty getSaleStatusProperty(){
        return SaleStatus;
    }
       
}
