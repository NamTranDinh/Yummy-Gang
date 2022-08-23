package yummycode.admin.controller.tablecontroller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import yummycode.admin.databasehelper.TableMapDbHelper;
import yummycode.admin.frontend.Navigator;

import yummycode.model.TableMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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

public class TableMapController implements Initializable {
    @FXML
    private AnchorPane MainPane;

    @FXML
    private TableView<TableMap> tblTableMap;

    @FXML
    private TableColumn<TableMap, String> tcTableID;

    @FXML
    private TableColumn<TableMap, String> tcTableName;

    @FXML
    private TableColumn<TableMap, Integer> tcSeatsNumber;

    @FXML
    private TableColumn<TableMap, Integer> tcFloorsNumber;

    @FXML
    private TableColumn<TableMap, String> tcTableStatus;

    @FXML
    private Pane paneSlide1;

    @FXML
    private Button btnMainEdit;

    @FXML
    private Button btnMainSearch;

    @FXML
    private Button btnMainAdd;

    @FXML
    private Button btnMainBack;

    @FXML
    private Pane paneSlide2;

    @FXML
    private StackPane contentArea;

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
    private AnchorPane PaneShow;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnHidden;

    @FXML
    private AnchorPane paneSearchTranslate;

    @FXML
    private TextField txtSearch;

    ObservableList<TableMap> listTab = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneSearchTranslate.setTranslateY(-300);
        paneTranslate.setTranslateX(-700);
        paneSlide1.setVisible(true);
        paneSlide2.setVisible(false);
        List<TableMap> listData;
        try {
            listData = TableMapDbHelper.getAllTable();
            listTab.addAll(listData);
            tblTableMap.setItems(this.listTab);
            tcTableID.setCellValueFactory(CellData -> CellData.getValue().getTableIdProperty());
            tcTableName.setCellValueFactory(CellData -> CellData.getValue().getTableNameProperty());
            tcSeatsNumber.setCellValueFactory(CellData -> CellData.getValue().getSeatsNumberProperty());
            tcFloorsNumber.setCellValueFactory(CellData -> CellData.getValue().getFloorsNumberProperty());
            tcTableStatus.setCellValueFactory(CellData -> CellData.getValue().getTableStatusProperty());
        } catch (SQLException e) {

            e.printStackTrace();
        }

        FilteredList<TableMap> filteredData = new FilteredList<>(listTab , b ->true);
        txtSearch.textProperty().addListener((observable, oldValue , newValue) ->{
            filteredData.setPredicate(TableMap-> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                   return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if(String.valueOf(TableMap.getFloorsNumber()).toLowerCase().indexOf(searchKeyword) > -1){
                   return true;
                }else if(String.valueOf(TableMap.getSeatsNumber()).toLowerCase().indexOf(searchKeyword) > -1){
                   return true;
                }else if(TableMap.getTableId().toLowerCase().indexOf(searchKeyword) > -1){
                   return true;
                }else if(TableMap.getTableName().indexOf(searchKeyword) > -1){
                   return true;
                }else if(TableMap.getTableStatus().indexOf(searchKeyword) > -1){
                  return true;
                }else{
                    return false;
                }
            });
        });
        SortedList<TableMap> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblTableMap.comparatorProperty());
        tblTableMap.setItems(sortedData);

    }

    @FXML
    void onClickTableView(MouseEvent event) {
        Navigator.getInstance().translateSideBarMinus(paneSlide1, paneSlide2, paneTranslate, -770, 0);
        Navigator.getInstance().translateSideYBarMinus(paneSlide1, paneSlide2, paneSearchTranslate, -300, 0);
    }

    @FXML
    void onClickAdd(ActionEvent event) {
        Navigator.getInstance().translateSideBarMinus(paneSlide1, paneSlide2, paneTranslate, -770, 0);
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
    void onClickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminHome2();
    }

    @FXML
    void onClickEdit(ActionEvent event) {
        Navigator.getInstance().translateSideBarMinus(paneSlide1, paneSlide2, paneTranslate, -770, 0);
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
    void onClickMainAdd(ActionEvent event) {
        Navigator.getInstance().changePage(contentArea, "../frontend/tablefrontend/AddNewTableUI.fxml");

        Navigator.getInstance().translateSideBarPlus(paneSlide1, paneSlide2, paneTranslate, 0, -600);

    }

    @FXML
    void onClickMainBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminHome2();
    }

    @FXML
    void onClickMainEdit(ActionEvent event) throws IOException {
        //Truyền dữ liệu giữa 2 form .
        Object Node;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TableMap tbl = tblTableMap.getSelectionModel().getSelectedItem();
        if (tbl == null) {
            Navigator.getInstance().showAlert(AlertType.ERROR, "Please select one!");
        } else {
            Navigator.getInstance().translateSideBarPlus(paneSlide1, paneSlide2, paneTranslate,0,-600);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../frontend/tablefrontend/EditTableUI.fxml"));
            Parent root = loader.load();
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(root);

            EditTableController controller = loader.getController();
            controller.LoadData(tbl);

            stage.setScene(contentArea.getScene());
        }
    }
}
