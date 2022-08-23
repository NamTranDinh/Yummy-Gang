package yummycode.employee.controller.paymentcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import yummycode.admin.frontend.Navigator;
import yummycode.employee.controller.tablecontroller.TableItem_controller;
import yummycode.employee.databasehelper.FoodManagerDBHelper;
import yummycode.model.Payment_Model;

public class Payment_controller implements Initializable {
    public static String idTable;

    @FXML
    private AnchorPane anchorPnaeContainer;

    @FXML
    private GridPane gridPaneContainer;

    @FXML
    void onClickPayment(ActionEvent event) throws IOException {
        if (idTable == null) {
            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please choose at least one bill to payment !");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Confirm payment");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                if (FoodManagerDBHelper.paidBill(idTable)) {
                    FoodManagerDBHelper.ChangeStatusTable( TableItem_controller.STATUS_EMPTY , idTable);
                } else {
                }
                Navigator.getInstance().gotoPayment();
            } else if (option.get() == ButtonType.CANCEL) {

            }

        }
    }

    @FXML
    void onClickSeeDetail(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoBillPayment();
    }

    @FXML
    void onclickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToEmployeeTableMap();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Payment_Model> list = FoodManagerDBHelper.ShowUnpaidInvoices();

        int row = 1;
        int col = 0;

        try {
            for (Payment_Model pModel : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../frontend/PaymentItemUI.fxml"));
                VBox vBox;
                vBox = fxmlLoader.load();

                PaymentItem_controller item_controller = fxmlLoader.getController();
                item_controller.loadData(pModel.getPrice(), pModel.getTableID(), pModel.getMember(), pModel.getIdBill(), pModel.getDateTime(), pModel.getSale());

                if (col == 5) {
                    col = 0;
                    ++row;
                }
                gridPaneContainer.add(vBox, col++, row);
                gridPaneContainer.setMargin(vBox, new Insets(20));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
