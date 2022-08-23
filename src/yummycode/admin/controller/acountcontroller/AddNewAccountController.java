package yummycode.admin.controller.acountcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import yummycode.admin.databasehelper.AccountDBHelper;
import yummycode.admin.frontend.Navigator;
import yummycode.model.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddNewAccountController implements Initializable {

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
        // cbStatus.getItems().add("Lock");
        // cbStatus.getItems().add("UnLock");
        // cbStatus.setValue("UnLock");

        cbType.getItems().add("Admin");
        cbType.getItems().add("Employee");
        cbType.setValue("Employee");

    }

    @FXML
    void onClickSubmit(ActionEvent event) throws IOException {
        ObservableList<Account> listAcc = FXCollections.observableArrayList(AccountDBHelper.getAllAccount());

        boolean checkEmail = AccountDBHelper.checkEmailRegex(txtEmail.getText());
        boolean checkPass = AccountDBHelper.checkPasswordRegex(txtPassword.getText());
        int resultCheck = 0;
        boolean checked = false;

        // Check email exists
        for (Account AlreadyExists : listAcc) {
            if (AlreadyExists.getEmail().equals(txtEmail.getText())) {
                resultCheck = 4;
            }
        }

        // Check inout regex
        if (txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty() ||
                txtName.getText().isEmpty()) {
            resultCheck = 1;
        } else if (!checkEmail) {
            resultCheck = 2;
        } else if (!checkPass) {
            resultCheck = 3;
        } else if (resultCheck == 0) {
            String IdAcc = "#" + String.valueOf(Navigator.getInstance().random(10000));
            // Check ID Already exists
            loop: for (Account checkIDAlreadyExists : listAcc) {
                if (checkIDAlreadyExists.getId().equals(IdAcc)) {
                    IdAcc = "#" + String.valueOf(Navigator.getInstance().random(10000));
                    continue loop;
                } else {
                    checked = true;
                    break loop;
                }
            }

            if (checked) {
                boolean resultUpdate = AccountDBHelper.addNewAccount(IdAcc,
                        txtName.getText(), txtEmail.getText(),
                        txtPassword.getText(), cbType.getValue());
                if (resultUpdate) {
                    Navigator.getInstance().showAlert(AlertType.INFORMATION,
                            "Add new acc Completed");
                    Navigator.getInstance().goToAccountHome();
                } else {
                    Navigator.getInstance().showAlert(AlertType.ERROR, "Add new acc failed");
                }
            }
        }

        

        if (resultCheck == 2) {
            Navigator.getInstance().showAlert(AlertType.ERROR, "Invalid email");
        } else if (resultCheck == 3) {
            Navigator.getInstance().showAlert(AlertType.ERROR, "Invalid password");
        } else if (resultCheck == 1) {
            Navigator.getInstance().showAlert(AlertType.ERROR,
                    "Password , email or username cannot be blank");
        } else if (resultCheck == 4) {
            Navigator.getInstance().showAlert(AlertType.ERROR,
                    "Email account already exists");
        }

    

        if (resultCheck == 2) {
            Navigator.getInstance().showAlert(AlertType.ERROR, "Invalid email");
        } else if (resultCheck == 3) {
            Navigator.getInstance().showAlert(AlertType.ERROR, "Invalid password");
        } else if (resultCheck == 1) {
            Navigator.getInstance().showAlert(AlertType.ERROR,
                    "Password , email or username cannot be blank");
        } else if (resultCheck == 4) {
            Navigator.getInstance().showAlert(AlertType.ERROR,
                    "Email account already exists");
        }

    }
}
