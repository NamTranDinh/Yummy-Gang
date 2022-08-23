package yummycode.admin.controller;

import java.io.IOException;

import yummycode.admin.databasehelper.AccountDBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AdminFormController {
   
    @FXML
    private AnchorPane Pane_AdminForm;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogIn1;

    @FXML
    private Button btnForgotAccount;

    @FXML
    void onClickForgotAccount(ActionEvent event) {

    }

    @FXML
    void onClickLogIn(ActionEvent event) throws IOException {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        AccountDBHelper.AdminLogIn(email, password);
    }
       
    }


