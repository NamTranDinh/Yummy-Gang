package yummycode.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class TableMap {
    private StringProperty tableId;
    private StringProperty tableName;
    private ObjectProperty<Integer> seatsNumber;
    private ObjectProperty<Integer> floorsNumber;
    private StringProperty tableStatus;

    String tableID;
    int seat;
    int floorNumber;
    String tblStatus;

    /////////////////////////
    
    public TableMap(String tableId, String tableName, int seatsNumber, int floorsNumber, String tableStatus) {
        this.tableId = new SimpleStringProperty(tableId);
        this.tableName = new SimpleStringProperty(tableName);
        this.seatsNumber = new SimpleObjectProperty<Integer>(seatsNumber);
        this.floorsNumber = new SimpleObjectProperty<Integer>(floorsNumber);
        this.tableStatus = new SimpleStringProperty(tableStatus);
    }

    public void setTableId(String tableId) {
        this.tableId.setValue(tableId);
    }

    public void setTableName(String tableName) {
        this.tableName.setValue(tableName);
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber.setValue(seatsNumber);
    }

    public void setFloorsNumber(int floorsNumber) {
        this.floorsNumber.setValue(floorsNumber);
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus.setValue(tableStatus);
    }

    public String getTableId() {
        return tableId.getValue();
    }

    public String getTableName() {
        return tableName.getValue();
    }

    public int getSeatsNumber() {
        return seatsNumber.getValue();
    }

    public int getFloorsNumber() {
        return floorsNumber.getValue();
    }

    public String getTableStatus() {
        return tableStatus.getValue();
    }

    public StringProperty getTableIdProperty() {
        return tableId;
    }

    public StringProperty getTableNameProperty() {
        return tableName;
    }

    public ObjectProperty<Integer> getSeatsNumberProperty() {
        return seatsNumber;
    }

    public ObjectProperty<Integer> getFloorsNumberProperty() {
        return floorsNumber;
    }

    public StringProperty getTableStatusProperty() {
        return tableStatus;
    }

    ////////////////////////

    

    public String getTableID() {
        return tableID;
    }

    public TableMap(String tableID, int seat, int floorNumber, String tblStatus) {
        this.tableID = tableID;
        this.seat = seat;
        this.floorNumber = floorNumber;
        this.tblStatus = tblStatus;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getTblStatus() {
        return tblStatus;
    }

    public void setTblStatus(String tblStatus) {
        this.tblStatus = tblStatus;
    }

    

}
