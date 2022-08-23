/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yummycode.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.SimpleStyleableStringProperty;


/**
 *
 * @author Van Luan Nguyen
 */
public class Account {

    private StringProperty id;
    private StringProperty username;
    private StringProperty email;
    private StringProperty password;
    private StringProperty type;
    private StringProperty status;
   

    public Account() {
    }

    public Account(String id,String username, String email, String password, String type, String status) {
        this.id = new SimpleStringProperty(id);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.type = new SimpleStringProperty(type);
        this.status = new SimpleStringProperty(status);
    }

    public void setId(String id) {
        this.id.setValue(id);
    }
    public void setUsername(String username) {
        this.username.setValue(username);
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public void setPassword(String password) {
        this.password.setValue(password);
    }

    public void setType(String type) {
        this.type.setValue(type);
    }

    public void setStatus(String status) {
        this.status.setValue(status);
    }

    public String getId() {
        return id.getValue();
    }
    public String getUserName() {
        return username.getValue();
    }
    public String getEmail() {
        return email.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }

    public String getType() {
        return type.getValue();
    }

    public String getStatus() {
        return status.getValue();
    }
    public StringProperty getIdProperty() {
        return id;
    }
    public StringProperty getUserNameProperty() {
        return username;
    }
    public StringProperty getEmailProperty() {
        return email;
    }

    public StringProperty getPasswordProperty() {
        return password;
    }

    public StringProperty getTypeProperty() {
        return type;
    }

    public StringProperty getStatusProperty() {
        return status;
    }
}
