package yummycode.admin.databasehelper;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDBHelper {
    private static final String HOST_NAME = "localhost";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    // private static final String DB_NAME = "yummygang";
    private static final String DB_NAME = "yummymanager";
    private static final String CONNECT_URL = "jdbc:mysql://" + HOST_NAME + "/" + DB_NAME;

    // Connecting phpByAdmin.
    public static Connection getConnect() {
        try {
            Connection connect = DriverManager.getConnection(CONNECT_URL, USER_NAME, PASSWORD);
            System.out.println("thanh cong!");
            return connect;
        } catch (Exception e) {
            System.out.println("Loi ket noi" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
