package yummycode.employee.controller;

import yummycode.admin.frontend.Navigator;
import yummycode.employee.controller.ordercontroller.OrderItem_controller;
import yummycode.employee.databasehelper.FoodManagerDBHelper;
import yummycode.model.FoodItem_model;
import yummycode.model.Food_model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FoodItemController {

    public static VBox container;
    String idFood;

    @FXML
    private ImageView imgFood;

    @FXML
    private Label FoodName;

    @FXML
    private Label FoodPrice;

    public static ArrayList<Food_model> ListFoodOrder = new ArrayList<>();

    @FXML
    void clickItem(MouseEvent event) throws IOException {

        Food_model food = new Food_model();
        food.setIdFood(idFood);
        food.setName(FoodName.getText());
        food.setQuantity(1);
        food.setPrice(Double.parseDouble(FoodPrice.getText()));
        food.setTotalMoney(food.getPrice());

        ListFoodOrder.add(food);
        loadScene(food);

        OrderItem_controller.models = ListFoodOrder;
    }

    public void setData(FoodItem_model items) {
        Image img = new Image(getClass().getResourceAsStream("../image/" + items.getImgFood()));
        imgFood.setImage(img);
        FoodName.setText(items.getNameFood());
        FoodPrice.setText(String.valueOf(items.getFOODPRICE()));
        idFood = items.getIdFood();
    }

    public void loadScene(Food_model food) throws IOException {
        FoodMenuController foodMenuController = new FoodMenuController();
        foodMenuController.LoadFoodItem(food, container);
        
    }
}