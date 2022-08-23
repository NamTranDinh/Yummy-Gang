package yummycode.admin.controller;
import java.io.IOException;

import yummycode.admin.databasehelper.AccountDBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
public class EmployeeFormController {
    @FXML
    private AnchorPane Pane_EmployeeForm;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtEmployeePassword;

    @FXML
    private Button btnForgotPassword;

    @FXML
    private Button btnEmployeeLogin;

    @FXML
    void onClickEmployeeForgotPassword(ActionEvent event) {

    }

    @FXML
    void onClickEmployeeLogin(ActionEvent event) throws IOException {
        String password = txtEmployeePassword.getText();
        String username = txtUsername.getText();
        // AccountDBHelper.EmployeeLoginIn(username, password);
    }
}
