package yummycode.admin.controller.salecontroller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import yummycode.admin.databasehelper.SaleDBHelper;
import yummycode.admin.frontend.Navigator;
import yummycode.model.RandomGenerator;
import yummycode.model.Sale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddNewSaleCodeController implements Initializable {

    @FXML
    private ChoiceBox<String> cbStatus;

    @FXML
    private Button btnSubmit;

    @FXML
    private ChoiceBox<Integer> cbDiscount;

    @FXML
    private DatePicker dpStartDay;

    @FXML
    private DatePicker dpEndDay;

    @FXML
    private TextArea txtDecription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbStatus.getItems().add("Off");
        cbStatus.getItems().add("On");
        cbStatus.setValue("On");
        cbDiscount.setValue(5);
        for (int i = 5; i <= 100; i++) {
            if ((i % 5) == 0) {
                cbDiscount.getItems().add(i);
            }
        }

    }

    @FXML
    void onClickSubmit(ActionEvent event) throws SQLException, IOException {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String decription = txtDecription.getText();
        RandomGenerator passwordGenerator = new RandomGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String saleCode = passwordGenerator.generate(8);
        String sale ="";
        List<Sale> listSale = SaleDBHelper.getAllSaleCode();
        for(Sale o:listSale){
             sale = o.getSaleCode();
        }
        if(!saleCode.equals(sale)){
            if(decription.isEmpty()  ||  dpEndDay.getValue() == null){
                Navigator.getInstance().showAlert(AlertType.ERROR, "Please fill out the form!");
            }else if(dpStartDay.getValue() == null){
                Sale sale2 = new Sale(saleCode,formatter.format(currentDate).toString(),formatter.format(dpEndDay.getValue()).toString(),decription,cbDiscount.getValue(),cbStatus.getValue());
                SaleDBHelper.addNewSaleCode(sale2);
                Navigator.getInstance().goToSaleDetails();
            }else {
                Sale sale2 = new Sale(saleCode,formatter.format(dpStartDay.getValue()).toString(),formatter.format(dpEndDay.getValue()).toString(),decription,cbDiscount.getValue(),cbStatus.getValue());
                SaleDBHelper.addNewSaleCode(sale2);
                Navigator.getInstance().goToSaleDetails();
            }
        }
    }
    

}
