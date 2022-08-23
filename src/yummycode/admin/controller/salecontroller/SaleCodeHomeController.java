package yummycode.admin.controller.salecontroller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import yummycode.admin.databasehelper.SaleDBHelper;
import yummycode.admin.frontend.Navigator;
import yummycode.model.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

public class SaleCodeHomeController implements Initializable {
    @FXML
    private AnchorPane MainPane;

    @FXML
    private TableView<Sale> tblSaleDetails;

    @FXML
    private TableColumn<Sale, String> tcStartDay;

    @FXML
    private TableColumn<Sale, String> tcEndDay;

    @FXML
    private TableColumn<Sale, String> tcSalesCodes;

    @FXML
    private TableColumn<Sale, String> tcDecription;

    @FXML
    private TableColumn<Sale, Integer> tcDiscount;

    @FXML
    private TableColumn<Sale, String> tcStatus;

    @FXML
    private Pane paneSlide2;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane paneTranslate;

    @FXML
    private StackPane contentArea;

    @FXML
    private Pane paneSlide1;

    @FXML
    private Button btnMainEdit;

    @FXML
    private Button btnMainSearch;

    @FXML
    private Button btnMainAdd;

    @FXML
    private Button btnBack1;

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

    ObservableList<Sale> listSale = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneSearchTranslate.setTranslateY(-300);
        Navigator.getInstance().translateSideBarMinus(paneSlide1, paneSlide2, paneTranslate, -770, 0);

        List<Sale> listData;
        listData = SaleDBHelper.getAllSaleCode();
        listSale.addAll(listData);
        tblSaleDetails.setItems(this.listSale);
        tcStartDay.setCellValueFactory(CellData -> CellData.getValue().getDateStartProperty());
        tcEndDay.setCellValueFactory(CellData -> CellData.getValue().getDateEndProperty());
        tcSalesCodes.setCellValueFactory(CellData -> CellData.getValue().getSaleCodeProperty());
        tcDecription.setCellValueFactory(CellData -> CellData.getValue().getDecriptionProperty());
        tcDiscount.setCellValueFactory(CellData -> CellData.getValue().getDiscountProperty());
        tcStatus.setCellValueFactory(CellData -> CellData.getValue().getSaleStatusProperty());

        FilteredList<Sale> filteredData = new FilteredList<>(listSale , b ->true);
        txtSearch.textProperty().addListener((observable, oldValue , newValue) ->{
            filteredData.setPredicate(Sale -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                   return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if(Sale.getSaleCode().toLowerCase().indexOf(searchKeyword) > -1){
                   return true;
                }else if(Sale.getDateStart().toLowerCase().indexOf(searchKeyword) > -1){
                   return true;
                }else if(Sale.getDateEnd().toLowerCase().indexOf(searchKeyword) > -1){
                   return true;
                }else if(Sale.getDecription().toLowerCase().indexOf(searchKeyword) > -1){
                   return true;
                }else if(String.valueOf(Sale.getDiscount()).indexOf(searchKeyword) > -1){
                  return true;
                }else if(Sale.getSaleStatus().toLowerCase().indexOf(searchKeyword) > -1){
                  return true;
                }else{
                    return false;
                }
            });
        });
        SortedList<Sale> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblSaleDetails.comparatorProperty());
        tblSaleDetails.setItems(sortedData);
    }

    @FXML
    void onClickAdd(ActionEvent event) {
        Navigator.getInstance().translateSideBarMinus(paneSlide1, paneSlide2, paneTranslate, -770, 0);
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
    void onClickEdit(ActionEvent event) {
        Navigator.getInstance().translateSideBarMinus(paneSlide1, paneSlide2, paneTranslate, -770, 0);
    }

    @FXML
    void onClickHidden(ActionEvent event) {
        Object Node;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onClickMainAdd(ActionEvent event) {
        Navigator.getInstance().translateSideBarPlus(paneSlide1, paneSlide2, paneTranslate, 0, -600);
        Navigator.getInstance().changePage(contentArea, "../frontend/salefrontend/AddNewSaleCodeUi.fxml");
    }

    @FXML
    void onClickMainEdit(ActionEvent event) throws IOException {
        Object Node;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Sale sale = tblSaleDetails.getSelectionModel().getSelectedItem();
        if (sale == null) {
            Navigator.getInstance().showAlert(AlertType.ERROR, "Please select one!");
        } else {
            Navigator.getInstance().translateSideBarPlus(paneSlide1, paneSlide2, paneTranslate,0,-600);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../frontend/salefrontend/EditSaleCodeUI.fxml"));
            Parent root = loader.load();
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(root);
            EditSaleCodeController controller = loader.getController();
            controller.LoadSaleData(sale);
            stage.setScene(contentArea.getScene());
        }
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
    void onClickOverPane(MouseEvent event) {

    }

    @FXML
    void onClickTableView(MouseEvent event) {
        Navigator.getInstance().translateSideBarMinus(paneSlide1, paneSlide2, paneTranslate, -770, 0);
        Navigator.getInstance().translateSideYBarMinus(paneSlide1, paneSlide2, paneSearchTranslate, -300, 0);
    }

}
