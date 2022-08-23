package yummycode.employee.controller.ordercontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import yummycode.employee.controller.FoodItemController;
import yummycode.model.Food_model;

public class OrderItem_controller implements Initializable{

    public static ArrayList<Food_model> models;

    Food_model foodMode;

    @FXML
    private Label nameFood;

    @FXML
    private Label quantity;

    @FXML
    private Label totalMoney;

    @FXML
    void onclickAdd(ActionEvent event) {

        quantity.setText(String.valueOf(Integer.parseInt(quantity.getText()) + 1));
        totalMoney.setText(String.valueOf(Double.parseDouble(quantity.getText()) * foodMode.getPrice()));

        foodMode.setName(nameFood.getText());
        foodMode.setQuantity(Integer.parseInt(quantity.getText()));
        foodMode.setTotalMoney(Double.parseDouble(totalMoney.getText()));

        int index = models.indexOf(foodMode);

        if (index != -1) {
            models.set(index, foodMode);
        }

    }

    @FXML
    void onclickFoodItem(MouseEvent event) {
       System.out.println(nameFood.getText() + quantity.getText() );
       
    }

    @FXML
    void onclickMinus(ActionEvent event) {
        if (Integer.parseInt(quantity.getText()) > 1) {
            quantity.setText(String.valueOf(Integer.parseInt(quantity.getText()) - 1));
            totalMoney.setText(String.valueOf(Integer.parseInt(quantity.getText()) * foodMode.getPrice()));

            foodMode.setName(nameFood.getText());
            foodMode.setQuantity(Integer.parseInt(quantity.getText()));
            foodMode.setTotalMoney(Double.parseDouble(totalMoney.getText()));

            int index = models.indexOf(foodMode);

            if (index != -1) {
                models.set(index, foodMode);
            }
        }
    }

    public void setData(Food_model food_model) {
        nameFood.setText(food_model.getName());
        quantity.setText(String.valueOf(food_model.getQuantity()));
        totalMoney.setText(String.valueOf(food_model.getPrice() * food_model.getQuantity()));
        this.foodMode = food_model;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        models = FoodItemController.ListFoodOrder;
        
    }

}