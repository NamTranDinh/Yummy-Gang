package yummycode.admin.databasehelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yummycode.model.TableMap;
public class TableMapDbHelper {
    public static List<TableMap> getAllTable() throws SQLException {
        List<TableMap> listTab = new ArrayList<>();

        try (Connection connect = ConnectDBHelper.getConnect();
                CallableStatement stm = connect.prepareCall("{call getAllTable()}");) {
            stm.execute();
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                String tableId = rs.getString("TABLEID");
                String tableName = rs.getString("TABLENAME");
                int seatsNumber = rs.getInt("SEATSNUMBER");
                int floorNumber = rs.getInt("FLOORNUMBER");
                String tableStatus = rs.getString("TABLESTATUS");
                TableMap tab = new TableMap(tableId, tableName, seatsNumber, floorNumber, tableStatus);
                listTab.add(tab);
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return listTab;
    }

    public static Boolean addNewTable(TableMap tbl) throws SQLException {
        try (Connection connect = ConnectDBHelper.getConnect();
                CallableStatement stm = connect.prepareCall("{call addNewTable(?,?,?,?,?)}");) {
            stm.setString(1, tbl.getTableId());
            stm.setString(2, tbl.getTableName());
            stm.setInt(3, tbl.getSeatsNumber());
            stm.setInt(4, tbl.getFloorsNumber());
            stm.setString(5, tbl.getTableStatus());
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return null;
    }

    public static Boolean editTableMap(TableMap tbl) throws SQLException {
        try (Connection connect = ConnectDBHelper.getConnect();
                CallableStatement stm = connect.prepareCall("{call editTableMap(?,?,?,?,?)}");) {
            stm.setString(1, tbl.getTableName());
            stm.setInt(2, tbl.getSeatsNumber());
            stm.setInt(3, tbl.getFloorsNumber());
            stm.setString(4, tbl.getTableStatus());
            stm.setString(5, tbl.getTableId());
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return null;
    }
}
