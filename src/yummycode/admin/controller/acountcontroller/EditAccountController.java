package yummycode.admin.controller.acountcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import yummycode.admin.databasehelper.AccountDBHelper;
import yummycode.admin.frontend.Navigator;
import yummycode.model.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EditAccountController implements Initializable {

    public static Account Account;

    @FXML
    private ChoiceBox<String> cbStatus;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtName;

    @FXML
    private ChoiceBox<String> cbType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbStatus.getItems().add("Lock");
        cbStatus.getItems().add("UnLock");
        cbStatus.setValue("UnLock");

        cbType.getItems().add("Admin");
        cbType.getItems().add("Employee");
        cbType.setValue("Employee");

        loadData(Account);

    }

    @FXML
    void onClickSubmit(ActionEvent event) throws IOException {
        boolean checkEmail = AccountDBHelper.checkEmailRegex(txtEmail.getText());
        boolean checkPass = AccountDBHelper.checkPasswordRegex(txtPassword.getText());
        int resultCheck = 0;
        if (!checkEmail) {
            resultCheck = 1;
        } else if (!checkPass) {
            resultCheck = 2;
        } else if (txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            resultCheck = 3;
        } else if (resultCheck == 0) {
            boolean resultUpdate = AccountDBHelper.EditAccount(txtName.getText(), txtEmail.getText(),
                    txtPassword.getText(),
                    cbType.getValue(), cbStatus.getValue(), Account.getId());
            if (resultUpdate) {
                Navigator.getInstance().showAlert(AlertType.INFORMATION, "Edit acc Completed");
                Navigator.getInstance().goToAccountHome();
            } else {
                Navigator.getInstance().showAlert(AlertType.ERROR, "Edit acc failed");
            }
        }

        if (resultCheck == 1) {
            Navigator.getInstance().showAlert(AlertType.ERROR, "Invalid email");
            txtEmail.setText(Account.getEmail());
        } else if (resultCheck == 2) {
            Navigator.getInstance().showAlert(AlertType.ERROR, "Invalid password");
            txtPassword.setText(Account.getPassword());
        } else if (resultCheck == 3) {
            Navigator.getInstance().showAlert(AlertType.ERROR,
                    "Password and email cannot be blank");
        }
    }

    public void loadData(Account Acc) {
        cbStatus.setValue(Acc.getStatus());
        cbType.setValue(Acc.getType());
        txtName.setText(Acc.getUserName());
        txtPassword.setText(Acc.getPassword());
        txtEmail.setText(Acc.getEmail());

    }
}