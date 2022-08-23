package yummycode.admin.controller.billcontroller;
import yummycode.admin.databasehelper.BillOrderDBHelper;
import yummycode.model.BillOrder_Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
public class SearchBillController {
    @FXML
    private DatePicker dateStart;

    @FXML
    private DatePicker dateEnd;

    @FXML
    private Button btnApply;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    void onClickApply(ActionEvent event) {
        String dateBegin = dateStart.getValue().toString();
        String dateStop = dateEnd.getValue().toString();

        //ObservableList<BillOrder_Model> data = FXCollections.observableArrayList(BillOrderDBHelper.filterDate(dateBegin, dateStop));

      /*setDataForCellTblBillOrder(data,tblBillOrder);
        onclickSeeDetailBill();*/
    }

    @FXML
    void onClickSearch(ActionEvent event) {

    }
}
