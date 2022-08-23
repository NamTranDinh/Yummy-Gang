package yummycode.employee.controller.tablecontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import yummycode.admin.frontend.Navigator;
import yummycode.employee.databasehelper.FoodManagerDBHelper;
import yummycode.model.TableMap;

public class TableMap_controller implements Initializable {

    @FXML
    private AnchorPane tablesOn1stFloor;

    @FXML
    private AnchorPane tableOn2ndFloor;

    @FXML
    private GridPane containerTable1stFloor;

    @FXML
    private GridPane containerTable2ndFloor;

    @FXML
    void onclickPayment(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoPayment();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            int row1 = 1, row2 = 1;
            int col1 = 0, col2 = 0;
            List<TableMap> listTable = new ArrayList<>(FoodManagerDBHelper.getAllTable());
            for (TableMap tableMap : listTable) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../frontend/TableItem.fxml"));

                Pane pane = fxmlLoader.load();

                TableItem_controller setItem = fxmlLoader.getController();
                setItem.setData(tableMap);

                if (tableMap.getFloorNumber() == 1) {
                    if (col1 == 4) {
                        col1 = 0;
                        row1++;
                    }
                    containerTable1stFloor.add(pane, col1++, row1);
                    GridPane.setMargin(pane, new Insets(12));
                }else if (tableMap.getFloorNumber() == 2) {
                    if (col2 == 4) {
                        col2 = 0;
                        row2++;
                    }
                    containerTable2ndFloor.add(pane, col2++, row2);
                    GridPane.setMargin(pane, new Insets(12));
                }

            }

        } catch (IOException e) {
            System.out.println("loi" + e.getMessage());
        }

    }

}
