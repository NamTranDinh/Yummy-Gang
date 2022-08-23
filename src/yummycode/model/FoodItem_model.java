package yummycode.model;

public class FoodItem_model {
    String idFood;
    String ImgFood;
    String NameFood;
    double FOODPRICE;

    

    public FoodItem_model(String idFood, String imgFood, String nameFood, double FOODPRICE) {
        this.idFood = idFood;
        ImgFood = imgFood;
        NameFood = nameFood;
        this.FOODPRICE = FOODPRICE;
    }

    public String getImgFood() {
        return ImgFood;
    }

    public void setImgFood(String imgFood) {
        ImgFood = imgFood;
    }

    public String getNameFood() {
        return NameFood;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public void setNameFood(String nameFood) {
        NameFood = nameFood;
    }

    public double getFOODPRICE() {
        return FOODPRICE;
    }

    public void setFOODPRICE(double FOODPRICE) {
        FOODPRICE = FOODPRICE;
    }   
}



