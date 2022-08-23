package yummycode.admin.databasehelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import yummycode.model.BillOrder_Model;
import yummycode.model.BillPayment_Model;
import javafx.scene.control.Button;

public class BillOrderDBHelper {

     public static List<BillOrder_Model> getAllBillOrder() {
        List<BillOrder_Model> listBillOrder = new ArrayList<>();
        try (Connection cnn = ConnectDBHelper.getConnect();
                CallableStatement stm = cnn.prepareCall("{CALL getAllBillOrder()}");) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                String bILLID = rs.getString("BILLID");
                String dATETIME = rs.getString("DATETIME");
                String tABLEID = rs.getString("TABLEID");
                String dETAILBILLID = rs.getString("DETAILBILLID");
                String sUMOFPRICES = rs.getString("SUMOFPRICE");
                String Status =rs.getString("BillStatus");
                String SaleCode = rs.getString("SALECODE");
                String Discount = rs.getString("DISCOUNT");
                listBillOrder.add(new BillOrder_Model(bILLID, dATETIME, tABLEID, dETAILBILLID, sUMOFPRICES,Status,SaleCode,Discount));

            }

        } catch (Exception e) {
             System.out.println(e.getMessage());
        }

        return listBillOrder;
    } 

    public static List<BillPayment_Model> getDataForBill(String BillID) {
        List<BillPayment_Model> data = new ArrayList<>();
        try (Connection cnn = ConnectDBHelper.getConnect();
                CallableStatement stm = cnn.prepareCall("{CALL getDataForBill(?)}");) {

            stm.setString(1, BillID);
            ResultSet rs = stm.executeQuery();
         
            while (rs.next()) {
               
                String fOODNAME = rs.getString("FOODNAME");
                String fOODQUANTITY = rs.getString("FOODQUANTITY");
                String fOODPRICE = rs.getString("FOODPRICE");
                String sUMOFPRICE = rs.getString("SUMOFPRICE");

                BillPayment_Model billPay = new BillPayment_Model(fOODNAME, fOODQUANTITY, fOODPRICE, sUMOFPRICE);
                data.add(billPay);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

  /*   public static List<BillOrder_Model> filterDate(String dateStart, String dateEnd) {
        List<BillOrder_Model> data = new ArrayList<>();
        try (Connection cnn = ConnectDBHelper.getConnect();
                CallableStatement stm = cnn.prepareCall("{CALL filterDate(?,?)}");) {

            stm.setString(1, dateStart);
            stm.setString(2, dateEnd);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String bILLID = rs.getString("BILLID");
                String dATETIME = rs.getString("DATETIME");
                String tABLEID = rs.getString("TABLEID");
                String dETAILBILLID = rs.getString("DETAILBILLID");
                String sUMOFPRICES = rs.getString("SUMOFPRICE");

                data.add(new BillOrder_Model(bILLID, dATETIME, tABLEID, dETAILBILLID, sUMOFPRICES));
            }

        } catch (Exception e) {

        }
        return data;
    } */
}
