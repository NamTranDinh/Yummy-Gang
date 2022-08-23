package yummycode.mainload;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;

import yummycode.admin.databasehelper.AccountDBHelper;
import yummycode.admin.frontend.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main  extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //stage.initStyle(StageStyle.TRANSPARENT);  
        Navigator.getInstance().setState(stage);
        Navigator.getInstance().goToEmployeeTableMap();
    } 
}
