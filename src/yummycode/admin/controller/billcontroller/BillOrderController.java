package yummycode.admin.controller.billcontroller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.NavigableMap;
import java.util.ResourceBundle;

import yummycode.admin.databasehelper.BillOrderDBHelper;
import yummycode.admin.frontend.Navigator;
import yummycode.model.BillOrder_Model;
import yummycode.model.BillPayment_Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

public class BillOrderController implements Initializable {

    @FXML
    private AnchorPane MainPane;

    @FXML
    private TableView<BillOrder_Model> tblBillOrder;

    @FXML
    private TableColumn<BillOrder_Model, String> tcBillCode;

    @FXML
    private TableColumn<BillOrder_Model, String> tcDateTime;

    @FXML
    private TableColumn<BillOrder_Model, String> tcDetailBillID;

    @FXML
    private TableColumn<BillOrder_Model, String> tcTableID;

    @FXML
    private TableColumn<BillOrder_Model, String> tcSumOfPrice;

    @FXML
    private TableColumn<BillOrder_Model, String> tcAction;

    @FXML
    private TableColumn<BillOrder_Model, String> tcStatus;

    @FXML
    private TableColumn<BillOrder_Model, String> tcDiscount;

    @FXML
    private TableColumn<BillOrder_Model, String> tcSaleCode;

    @FXML
    private Pane paneSlide1;

    @FXML
    private Button btnBack1;

    @FXML
    private Button btnSeeDetail;

    @FXML
    private Button btnMainSeeDetail;

    @FXML
    private Button btnMainSearch;

    @FXML
    private Pane paneSlide2;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSearch;

    @FXML
    private AnchorPane paneTranslate;

    @FXML
    private StackPane bill;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnHidden;

    @FXML
    private AnchorPane paneSearchTranslate;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnTopSearch;
    ObservableList<BillOrder_Model> listBill = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneSearchTranslate.setTranslateY(-300);
        Navigator.getInstance().translateSideBarMinus(paneSlide1, paneSlide2, paneTranslate, -770, 0);
        List<BillOrder_Model> listData;
        listData = BillOrderDBHelper.getAllBillOrder();
        listBill.addAll(listData);
        tblBillOrder.setItems(this.listBill);
        tcBillCode.setCellValueFactory(CellData -> CellData.getValue().getBillIdProperty());
        tcDateTime.setCellValueFactory(CellData -> CellData.getValue().getDateTimeProperty());
        tcDetailBillID.setCellValueFactory(CellData -> CellData.getValue().getDetailBillIdProperty());
        tcTableID.setCellValueFactory(CellData -> CellData.getValue().getTableIdProperty());
        tcSumOfPrice.setCellValueFactory(CellData -> CellData.getValue().getSumOfPriceProperty());
        tcSaleCode.setCellValueFactory(CellData -> CellData.getValue().getSaleCodeProperty());
        tcDiscount.setCellValueFactory(CellData -> CellData.getValue().getDiscountProperty());
        tcStatus.setCellValueFactory(CellData -> CellData.getValue().getStatusProperty());

        FilteredList<BillOrder_Model> filteredData = new FilteredList<>(listBill , b ->true);
        txtSearch.textProperty().addListener((observable, oldValue , newValue) ->{
            filteredData.setPredicate(BillOrder_Model -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                   return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if(BillOrder_Model.getBillId().toLowerCase().indexOf(searchKeyword) > -1){
                   return true;
                }else if(BillOrder_Model.getDateTime().toLowerCase().indexOf(searchKeyword) > -1){
                   return true;
                }else if(BillOrder_Model.getDetailBillID().toLowerCase().indexOf(searchKeyword) > -1){
                   return true;
                }else if(BillOrder_Model.getDiscount().toLowerCase().indexOf(searchKeyword) > -1){
                   return true;
                }else if(BillOrder_Model.getSaleCode().indexOf(searchKeyword) > -1){
                  return true;
                }else if(BillOrder_Model.getStatus().toLowerCase().indexOf(searchKeyword) > -1){
                  return true;
                }else{
                    return false;
                }
            });
        });
        SortedList<BillOrder_Model> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblBillOrder.comparatorProperty());
        tblBillOrder.setItems(sortedData);
    }

    @FXML
    void onClickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminHome2();
    }

    @FXML
    void onClickCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminHome2();
    }

    @FXML
    void onClickHidden(ActionEvent event) {
        Object Node;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onClickMainSearch(ActionEvent event) {
        Navigator.getInstance().translateSideYBarPlus(paneSlide1, paneSlide2, paneSearchTranslate, 0, -300);
    }

    @FXML
    void onClickSearch(ActionEvent event) {
        Navigator.getInstance().translateSideYBarMinus(paneSlide1, paneSlide2, paneSearchTranslate, -300, 0);
    }

    @FXML
    void onClickTopSearch(ActionEvent event) {

    }

    @FXML
    void onClickMainSeeDetail(ActionEvent event) throws IOException {

        Object Node;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BillOrder_Model selected = tblBillOrder.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Navigator.getInstance().showAlert(AlertType.ERROR, "Please select one!");
        } else {

            Navigator.getInstance().translateSideBarPlus(paneSlide1, paneSlide2, paneTranslate, 0, -600);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../frontend/billfrontend/BillPayment.fxml"));
            Parent root = loader.load();
            bill.getChildren().removeAll();
            bill.getChildren().setAll(root);

            BillPayment_Controller controller = loader.getController();
            controller.LoadData(selected);
            stage.setScene(bill.getScene());
        }
    }

    @FXML
    void onClickSeeDetail(ActionEvent event) {
        Navigator.getInstance().translateSideBarMinus(paneSlide1, paneSlide2, paneTranslate, -770, 0);
    }

    @FXML
    void onClickTableView(MouseEvent event) {
        Navigator.getInstance().translateSideBarMinus(paneSlide1, paneSlide2, paneTranslate, -770, 0);
        Navigator.getInstance().changePage(bill, "../frontend/billfrontend/BillPayment.fxml");
    }

}
