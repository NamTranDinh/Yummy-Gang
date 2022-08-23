package yummycode.employee.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import yummycode.admin.frontend.Navigator;
import yummycode.employee.controller.ordercontroller.OrderItem_controller;
import yummycode.employee.controller.tablecontroller.TableItem_controller;
import yummycode.employee.databasehelper.FoodManagerDBHelper;
import yummycode.model.FoodItem_model;
import yummycode.model.Food_model;
import yummycode.model.TableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FoodMenuController implements Initializable {
    boolean saved = false;
    public static Button btn;
    // food menu
    @FXML
    private TextField searchFood;

    @FXML
    private StackPane paneTranslate;

    @FXML
    private VBox ContentPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private StackPane paneShowFoodOrder;

    // order menu

    @FXML
    private Label IDBill;

    @FXML
    private Label seat;

    @FXML
    private Label IDTable;

    @FXML
    private VBox ContainerFoodOrder;

    @FXML
    private Label TotalMoney;

    public static ArrayList<Food_model> saveListFood;

    List<FoodItem_model> listItems = new ArrayList<>(FoodManagerDBHelper.getAllItemFood());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (OrderItem_controller.models == null) {

        } else {
            double totalPrice = 0.0;
            saveListFood = OrderItem_controller.models;
            for (Food_model e : saveListFood) {
                totalPrice += e.getTotalMoney();
            }

            TotalMoney.setText(String.valueOf(totalPrice));
        }

        FoodItemController.container = ContainerFoodOrder;

        searchFood.textProperty().addListener((a, b, c) -> {
            int row = 1;
            int col = 0;
            List<FoodItem_model> l = new ArrayList<>(
                    FoodManagerDBHelper.getAllFoodByName(searchFood.getText()));
            try {
                for (FoodItem_model foodItem_model : l) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../frontend/FoodItemUI.fxml"));
                    VBox vBox;
                    vBox = fxmlLoader.load();

                    FoodItemController foodMenuController = fxmlLoader.getController();
                    foodMenuController.setData(foodItem_model);
                    vBox.setVisible(true);

                    if (col == 6) {
                        col = 0;
                        ++row;
                    }
                    gridPane.add(vBox, col++, row);
                    gridPane.setMargin(vBox, new Insets(4));
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        });

    }

    @FXML
    void onclickSearch(ActionEvent event) {

    }

    @FXML
    void onclickBack(ActionEvent event) throws IOException {
        saveListFood = OrderItem_controller.models;
        // Navigator.getInstance().goToEmployeeTableMap();

        if (saveListFood == null) {
            Navigator.getInstance().goToEmployeeTableMap();
        } else if (saved) {
            Navigator.getInstance().goToEmployeeTableMap();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Are you sure back ?");
            alert.setContentText("if you back all data will be deleted");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {

                for (int i = 0; i < FoodItemController.ListFoodOrder.size(); i++) {
                    System.out.println(FoodItemController.ListFoodOrder.get(i).getName());
                    FoodItemController.ListFoodOrder.remove(i);
                }
                Navigator.getInstance().goToEmployeeTableMap();
            } else if (option.get() == ButtonType.CANCEL) {

            }
        }
    }

    @FXML
    void onclickPayment(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoPayment();
    }

    // order menu

    @FXML
    void onclickCance(ActionEvent event) throws IOException {
        saveListFood = OrderItem_controller.models;
        // Navigator.getInstance().goToEmployeeTableMap();

        if (saveListFood == null) {
            Navigator.getInstance().goToEmployeeTableMap();
        } else if (saved) {
            Navigator.getInstance().goToEmployeeTableMap();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Are you sure back ?");
            alert.setContentText("if you back all data will be deleted");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {

                for (int i = 0; i < FoodItemController.ListFoodOrder.size(); i++) {
                    System.out.println(FoodItemController.ListFoodOrder.get(i).getName());
                    FoodItemController.ListFoodOrder.remove(i);
                }
                Navigator.getInstance().goToEmployeeTableMap();
            } else if (option.get() == ButtonType.CANCEL) {

            }
        }
    }

    @FXML
    void onclickSentChef(MouseEvent event) throws IOException {

        saveListFood = OrderItem_controller.models;
        if (saveListFood == null) {
            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("Error");
            alert.setHeaderText("Unable to perform this action !");
            alert.setContentText("Please choose at least one dish !");

            alert.showAndWait();
        } else {
            FoodManagerDBHelper.ChangeStatusTable(TableItem_controller.STATUS_SERVING, IDTable.getText());
            saved = true;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date dateobj = new Date();

            String dateTimeCurrent = df.format(dateobj);

            ///////////////////////////////////////////////////////////////////////

            List<Food_model> lFood_models = new ArrayList<>(FoodManagerDBHelper.loadAllFoodOrdered(IDTable.getText()));

            ///////////////////////////////////////////////////////////////////////
            for (Food_model food_model : saveListFood) {
                if (lFood_models.isEmpty()) {

                    FoodManagerDBHelper
                            .insertDetailFoodForEachBill(IDBill.getText(), food_model.getIdFood(), IDTable.getText(),
                                    food_model.getQuantity(), food_model.getTotalMoney());

                    FoodManagerDBHelper.insertInfForBill(IDBill.getText(), dateTimeCurrent, IDTable.getText(),
                            IDBill.getText(), Double.parseDouble(TotalMoney.getText()) );

                } else {

                    for (Food_model foodSub : saveListFood) {
                        for (Food_model food_model2 : lFood_models) {
                            if (foodSub.getIdFood().equals(food_model2.getIdFood())) {
                                int newQuan = food_model2.getQuantity() + foodSub.getQuantity();
                                Double totalMoney = (Double) (foodSub.getPrice() * newQuan);

                                FoodManagerDBHelper.UpdateQantityFood(newQuan, totalMoney, IDBill.getText(),
                                        food_model2.getIdFood(), IDTable.getText());
                            } else {
                                FoodManagerDBHelper.insertDetailFoodForEachBill(IDBill.getText(), foodSub.getIdFood(),
                                        IDTable.getText(), foodSub.getQuantity(),
                                        foodSub.getPrice() * foodSub.getQuantity());
                            }
                        }
                    }

                }
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Save successfull !");

            alert.showAndWait();
            Navigator.getInstance().goToEmployeeTableMap();

        }

    }
    // function

    public void loadBillInf(TableMap tableMap, String billCode) throws IOException {
        IDTable.setText(tableMap.getTableID());
        seat.setText(String.valueOf(tableMap.getSeat()));
        IDBill.setText(billCode);

        /////////////////////////////////////////
        if (searchFood.getText().equals("")) {
            int row = 1;
            int col = 0;
            try {
                for (FoodItem_model foodItem_model : listItems) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../frontend/FoodItemUI.fxml"));
                    VBox vBox;

                    vBox = fxmlLoader.load();

                    FoodItemController foodMenuController = fxmlLoader.getController();
                    foodMenuController.setData(foodItem_model);

                    if (col == 6) {
                        col = 0;
                        ++row;
                    }
                    gridPane.add(vBox, col++, row);
                    gridPane.setMargin(vBox, new Insets(12));

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /////////////////////////////////////////
        loadAllFoodOrdered(IDTable.getText());
    }

    public void LoadFoodItem(Food_model food, VBox vBox) throws IOException {

        // onclick Food will show scene foodItem to bill
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../frontend/OrderItemUI.fxml"));
        HBox hBox = fxmlLoader.load();

        OrderItem_controller orderItem_controller = fxmlLoader.getController();
        orderItem_controller.setData(food);

        vBox.getChildren().add(hBox);

    }

    public void loadAllFoodOrdered(String IDTable) throws IOException {

        List<Food_model> lFood_models = new ArrayList<>(FoodManagerDBHelper.loadAllFoodOrdered(IDTable));

        for (Food_model food_model : lFood_models) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../frontend/OrderItemUI.fxml"));
            HBox hBox = fxmlLoader.load();

            OrderItem_controller orderItem_controller = fxmlLoader.getController();
            orderItem_controller.setData(food_model);

            ContainerFoodOrder.getChildren().add(hBox);

        }
    }

    public void saveFood(List<Food_model> listF) {
        System.out.println(listF.size());
        for (int i = 0; i < listF.size(); i++) {
            System.out.println(listF.get(i).getName());
            // System.out.println(model.getName() + model.getQuantity() + model.getPrice());

        }
    }

}
