package yummycode.model;

public class Food_model {
    String idFood;
    String name;
    int quantity;
    double price;
    double totalMoney;

    public Food_model() {
    }

    

    public Food_model(String idFood, String name, int quantity, double price) {
        this.idFood = idFood;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.totalMoney = quantity * price;
    }



    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

}
