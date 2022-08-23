package yummycode.admin.databasehelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yummycode.model.Sale;

public class SaleDBHelper {
    public static List<Sale> getAllSaleCode() {
        List<Sale> listSale = new ArrayList<>();
        try (Connection cnn = ConnectDBHelper.getConnect();
                CallableStatement stm = cnn.prepareCall("{CALL getAllSaleCode()}");) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String SaleCode = rs.getString("SALECODE");
                String DateStart = rs.getString("DATESTART");
                String DateEnd = rs.getString("DATEEND");
                String Decription = rs.getString("DECRIPTION");
                int Discount = rs.getInt("DISCOUNT");
                String SaleStatus = rs.getString("SALESTATUS");
                listSale.add(new Sale(SaleCode, DateStart, DateEnd, Decription, Discount, SaleStatus));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listSale;
    }

    public static Boolean addNewSaleCode(Sale sale) throws SQLException {
        try (Connection connect = ConnectDBHelper.getConnect();
                CallableStatement stm = connect.prepareCall("{call addNewSaleCode(?,?,?,?,?,?)}");) {
            stm.setString(1, sale.getSaleCode());
            stm.setString(2, sale.getDateStart());
            stm.setString(3, sale.getDateEnd());
            stm.setString(4, sale.getDecription());
            stm.setInt(5, sale.getDiscount());
            stm.setString(6, sale.getSaleStatus());
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return null;
    }

    public static Boolean editSaleCode(Sale sale) throws SQLException {
        try (Connection connect = ConnectDBHelper.getConnect();
                CallableStatement stm = connect.prepareCall("{call editSaleCode(?,?,?,?,?,?)}");) {
            stm.setString(1, sale.getDateStart());
            stm.setString(2, sale.getDateEnd());
            stm.setString(3, sale.getDecription());
            stm.setInt(4, sale.getDiscount());
            stm.setString(5, sale.getSaleStatus());
            stm.setString(6, sale.getSaleCode());
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return null;
    }
}
