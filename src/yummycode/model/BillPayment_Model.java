package yummycode.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BillPayment_Model {
    private StringProperty FoodName;
    private StringProperty FoodQuantity;
    private StringProperty FoodPrice;
    private StringProperty SumOfUnitPrice;

    public BillPayment_Model(String FoodName, String FoodQuantity , String FoodPrice, String SumOfUnitPrice) {
        this.FoodName = new SimpleStringProperty(FoodName);
        this.FoodQuantity = new SimpleStringProperty(FoodQuantity);
        this.FoodPrice = new SimpleStringProperty(FoodPrice);
        this.SumOfUnitPrice = new SimpleStringProperty(SumOfUnitPrice);
    }

    public String getFoodName() {
        return FoodName.getValue();
    }

    public void setFoodName(String FoodName) {
        this.FoodName.setValue(FoodName);
    }

    public String getFoodQuantity() {
        return FoodQuantity.getValue();
    }

    public void setFoodQuantity(String FoodQuantity) {
        this.FoodQuantity.setValue(FoodQuantity);
    }

    public String getFoodPrice() {
        return FoodPrice.getValue();
    }

    public void setFoodPrice(String FoodPrice) {
        this.FoodPrice.setValue(FoodPrice);
    }

    public String getSumOfUnitPrice() {
        return SumOfUnitPrice.getValue();
    }

    public void setSumOfUnitPrice(String SumOfUnitPrice) {
       this.SumOfUnitPrice.setValue(SumOfUnitPrice);
    }
    public StringProperty getFoodNameProperty(){
        return FoodName;
    }
    public StringProperty getFoodQuantityProperty(){
        return FoodQuantity;
    }
    public StringProperty getFoodPriceProperty(){
        return FoodPrice;
    }
    public StringProperty getSumOfUnitPriceProperty(){
        return SumOfUnitPrice;
    }
}
