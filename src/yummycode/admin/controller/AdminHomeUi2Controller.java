package yummycode.admin.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import yummycode.admin.databasehelper.TableMapDbHelper;
import yummycode.admin.frontend.Navigator;
import yummycode.model.TableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.scene.control.Alert.AlertType;

public class AdminHomeUi2Controller {
    @FXML
    private Button btnChart;

    @FXML
    private Button btnFoodMenu;

    @FXML
    private Button btnAccount;

    @FXML
    private Button btnBillOrder;

    @FXML
    private Button btnSale;

    @FXML
    private Button btnTableMap;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnHidden;

    @FXML
    void onClickCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLOGIN();
    }

    @FXML
    void onClickHidden(ActionEvent event) {
        Object Node;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onClickAccount(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAccountHome();
    }

    @FXML
    void onClickBillOrder(ActionEvent event) throws IOException {
        Navigator.getInstance().goToBillOrder();
    }

    @FXML
    void onClickChart(ActionEvent event) {

    }

    @FXML
    void onClickFoodMenu(ActionEvent event) throws IOException {
        Navigator.getInstance().goToFoodMenu();
    }

    @FXML
    void onClickLogOut(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLOGIN();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure want to log out ");
        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            Navigator.getInstance().goToLOGIN();
        } else if (option.get() == ButtonType.CANCEL) {

        }
    }

    @FXML
    void onClickSale(ActionEvent event) throws IOException {
        Navigator.getInstance().goToSaleDetails();
    }

    @FXML
    void onClickTableMap(ActionEvent event) throws IOException {

        Navigator.getInstance().goToTableMap();

    }

}
