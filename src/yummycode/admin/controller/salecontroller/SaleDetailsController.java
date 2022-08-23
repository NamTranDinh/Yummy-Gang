package yummycode.admin.controller.salecontroller;
import java.io.IOException;

import yummycode.admin.frontend.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
public class SaleDetailsController {
    @FXML
    private TableView<?> tblSaleDetails;

    @FXML
    private TableColumn<?, ?> tcStartDay;

    @FXML
    private TableColumn<?, ?> tcEndDay;

    @FXML
    private TableColumn<?, ?> tcSalesCodes;

    @FXML
    private TableColumn<?, ?> tcDecription;

    @FXML
    private TableColumn<?, ?> tcDiscount;

    @FXML
    private TableColumn<?, ?> tcStatus;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnAddSales;

    @FXML
    private Button btnSortBy;

    @FXML
    private Button btnSetStatus;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnCancel;

    @FXML
    void onClickAddSales(ActionEvent event) {

    }

    @FXML
    void onClickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminHome2();
    }

    @FXML
    void onClickCancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onClickSearch(ActionEvent event) {

    }

    @FXML
    void onClickSetStatus(ActionEvent event) {

    }

    @FXML
    void onClickSortBy(ActionEvent event) {

    }

}
