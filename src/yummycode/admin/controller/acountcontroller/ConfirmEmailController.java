package yummycode.admin.controller.acountcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;

import yummycode.admin.controller.SignInController;
import yummycode.admin.databasehelper.AccountDBHelper;
import yummycode.admin.frontend.Navigator;
import yummycode.model.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ConfirmEmailController {
    @FXML
    private TextField txtEmail;
   
    @FXML
    private Button btnOk;

    @FXML
    private Label txtAlert;

    @FXML
    void onClickOk(ActionEvent event) throws MessagingException, IOException, SQLException {
        String email = txtEmail.getText();
        String emailCheck ="";
        int flag = 0;
        List<Account> listAccount = AccountDBHelper.getAllAccount();
        loop: for (Account acc : listAccount) {
                  emailCheck =  acc.getEmail();
             }
            if (email.isEmpty()) {
                  txtAlert.setText("Please fill out completely!");
            }else if(!AccountDBHelper.checkEmailRegex(email)){
                txtAlert.setText("Email is invalid.Please enter again!");
            }else if(!email.equals(emailCheck)){
                txtAlert.setText("Please enter the email that the administrator gave you!");
            }else{
                  AccountDBHelper.sendMail(email);
                  SignInController sc = new SignInController();
                  sc.InputCode(email);
                  txtEmail.clear();
            }
    }
}
