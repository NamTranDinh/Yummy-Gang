package yummycode.employee.databasehelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import yummycode.admin.databasehelper.ConnectDBHelper;
import yummycode.model.Bill_Model;
import yummycode.model.FoodItem_model;
import yummycode.model.Food_model;
import yummycode.model.Payment_Model;
import yummycode.model.TableMap;

public class FoodManagerDBHelper {

    public static List<FoodItem_model> getAllItemFood() {
        List<FoodItem_model> listItem = new ArrayList<>();

        try (Connection connect = ConnectDBHelper.getConnect();
                // CallableStatement stm = connect.prepareCall("{call getAllAccount()}");) {
                PreparedStatement stm = connect
                        .prepareStatement("SELECT * FROM `tblfoodmenu` WHERE `FOODSTATUS` = 'Active'");) {
            stm.execute();
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                String idFood = rs.getString("FOODID");
                String imgFood = rs.getString("FOODIMAGE");
                String nameFood = rs.getString("FOODNAME");
                Double FOODPRICE = rs.getDouble("FOODPRICE");

                FoodItem_model item = new FoodItem_model(idFood, imgFood, nameFood, FOODPRICE);

                listItem.add(item);
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return listItem;
    }

    public static List<FoodItem_model> getAllFoodByName(String input) {
        List<FoodItem_model> listItem = new ArrayList<>();

        try (Connection connect = ConnectDBHelper.getConnect();
                // CallableStatement stm = connect.prepareCall("{call getAllAccount()}");) {
                PreparedStatement stm = connect
                        .prepareStatement(
                                "SELECT * FROM `tblfoodmenu` WHERE `FOODSTATUS` = 'Active' AND `FOODNAME` LIKE " + "'%"
                                        + input + "%'");) {
            stm.execute();
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                String idFood = rs.getString("FOODID");
                String imgFood = rs.getString("FOODIMAGE");
                String nameFood = rs.getString("FOODNAME");
                Double FOODPRICE = rs.getDouble("FOODPRICE");

                FoodItem_model item = new FoodItem_model(idFood, imgFood, nameFood, FOODPRICE);

                listItem.add(item);
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return listItem;
    }

    public static List<TableMap> getAllTable() {
        List<TableMap> listTable = new ArrayList<>();

        try (Connection connect = ConnectDBHelper.getConnect();
                // CallableStatement stm = connect.prepareCall("{call getAllAccount()}");) {
                PreparedStatement stm = connect
                        .prepareStatement(
                                "SELECT `TABLEID`,`SEATSNUMBER`,`FLOORNUMBER`,`TABLESTATUS` FROM `tbltablemap`")) {
            stm.execute();
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                String tableID = rs.getString("TABLEID");
                int seat = rs.getInt("SEATSNUMBER");
                int floorNumber = rs.getInt("FLOORNUMBER");
                String tblStatus = rs.getString("TABLESTATUS");

                TableMap tbl = new TableMap(tableID, seat, floorNumber, tblStatus);

                listTable.add(tbl);
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return listTable;
    }

    public static List<Payment_Model> ShowUnpaidInvoices() {
        List<Payment_Model> listTable = new ArrayList<>();

        try (Connection connect = ConnectDBHelper.getConnect();
                PreparedStatement stm = connect
                        .prepareStatement(
                                "SELECT DISTINCT(b.TABLEID), d.DISCOUNT ,a.BILLID , a.DATETIME,c.SEATSNUMBER ,a.SUMOFPRICE FROM `tblbill` a JOIN tbldetailbill b on a.DETAILBILLID = b.DETAILBILLID join tbltablemap c on c.TABLEID = a.TABLEID JOIN tblsaledetail d on d.SALECODE = a.SALECODE WHERE a.BillStatus = 'UnPaid'")) {
            stm.execute();
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                Double price = rs.getDouble("SUMOFPRICE");
                String tableID = rs.getString("TABLEID");
                int member = rs.getInt("SEATSNUMBER");
                String idBill = rs.getString("BILLID");
                String dateTime = rs.getString("DATETIME");
                int sale = rs.getInt("DISCOUNT");

                Payment_Model tbl = new Payment_Model(price, tableID, member, idBill, dateTime, sale);

                listTable.add(tbl);
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return listTable;
    }

    public static boolean AddDishesToEachTable(String idBill, String dateTime, String tableID, String detailBillID,
            String saleCode, double sumPrice, String billStatus) {
        try (
                Connection connect = ConnectDBHelper.getConnect();
                PreparedStatement stm = connect.prepareStatement(
                        "INSERT INTO `tblbill`(`BILLID`, `DATETIME`, `TABLEID`, `DETAILBILLID`, `SALECODE`, `SUMOFPRICE`, `BillStatus`) VALUES ('?','?','?','?','?','?','?')");) {
            stm.setString(1, idBill);
            stm.setString(2, dateTime);
            stm.setString(3, tableID);
            stm.setString(4, detailBillID);
            stm.setString(5, saleCode);
            stm.setDouble(6, sumPrice);
            stm.setString(7, billStatus);

            int resultInsert;

            resultInsert = stm.executeUpdate();
            if (resultInsert > 0) {
                return true;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    public static boolean AddFoodToDetailBill(String detaiBillId, String foodId, String tableId, int foodQuantity,
            Double sumOfPrice) {
        try (
                Connection connect = ConnectDBHelper.getConnect();
                PreparedStatement stm = connect.prepareStatement(
                        "INSERT INTO `tbldetailbill`(`DETAILBILLID`, `FOODID`, `TABLEID`, `FOODQUANTITY`, `SUMOFPRICE`) VALUES ('?','?','?','?','?')");) {
            stm.setString(1, detaiBillId);
            stm.setString(2, foodId);
            stm.setString(3, tableId);
            stm.setInt(4, foodQuantity);
            stm.setDouble(5, sumOfPrice);

            int resultInsert;

            resultInsert = stm.executeUpdate();
            if (resultInsert > 0) {
                return true;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    public static List<Food_model> loadAllFoodOrdered(String idTable) {
        List<Food_model> listItem = new ArrayList<>();
        String query = "SELECT b.FOODID,c.FOODNAME, b.FOODQUANTITY, c.FOODPRICE ,b.SUMOFPRICE FROM `tblbill` a join tbldetailbill b on a.DETAILBILLID=b.DETAILBILLID join tblfoodmenu c on b.FOODID=c.FOODID WHERE a.TABLEID = ? AND a.BillStatus = ?";

        try (Connection connect = ConnectDBHelper.getConnect();
                // CallableStatement stm = connect.prepareCall("{call getAllAccount()}");) {
                PreparedStatement stm = connect
                        .prepareStatement(query);) {
            stm.setString(1, idTable);
            stm.setString(2, "UnPaid");
            stm.execute();
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                String idFood = rs.getString("FOODID");
                String name = rs.getString("FOODNAME");
                int quantity = rs.getInt("FOODQUANTITY");
                Double price = rs.getDouble("FOODPRICE");

                Food_model item = new Food_model(idFood, name, quantity, price);

                listItem.add(item);
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return listItem;
    }

    public static boolean insertDetailFoodForEachBill(String DETAILBILLID, String FOODID, String TABLEID,
            int FOODQUANTITY, Double SUMOFPRICE) {
        try (
                Connection connect = ConnectDBHelper.getConnect();
                PreparedStatement stm = connect.prepareStatement(
                        "INSERT INTO `tbldetailbill`(`DETAILBILLID`, `FOODID`, `TABLEID`, `FOODQUANTITY`, `SUMOFPRICE`) VALUES (?,?,?,?,?)");) {
            stm.setString(1, DETAILBILLID);
            stm.setString(2, FOODID);
            stm.setString(3, TABLEID);
            stm.setInt(4, FOODQUANTITY);
            stm.setDouble(5, SUMOFPRICE);

            int resultInsert;

            resultInsert = stm.executeUpdate();
            if (resultInsert > 0) {
                return true;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    public static boolean insertInfForBill(String BILLID, String DATETIME, String TABLEID, String DETAILBILLID, Double totalMoney) {
        try (
                Connection connect = ConnectDBHelper.getConnect();
                PreparedStatement stm = connect.prepareStatement(
                        "INSERT INTO `tblbill`(`BILLID`, `DATETIME`, `TABLEID`, `DETAILBILLID`, `SALECODE`, `SUMOFPRICE`) VALUES (?,?,?,?,?,?)");) {
            stm.setString(1, BILLID);
            stm.setString(2, DATETIME);
            stm.setString(3, TABLEID);
            stm.setString(4, DETAILBILLID);
            stm.setString(5, "Null");
            stm.setDouble(6, totalMoney);

            int resultInsert;

            resultInsert = stm.executeUpdate();
            if (resultInsert > 0) {
                return true;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    public static boolean UpdateQantityFood(int FOODQUANTITY, Double SUMOFPRICE, String DETAILBILLID, String FOODID,
            String TABLEID) {
        try (
                Connection snn = ConnectDBHelper.getConnect();
                PreparedStatement stm = snn.prepareStatement(
                        "UPDATE `tbldetailbill` SET `FOODQUANTITY`= ? ,`SUMOFPRICE`=? WHERE `DETAILBILLID` = ? AND `FOODID` = ? AND `TABLEID` = ?");) {
            stm.setInt(1, FOODQUANTITY);
            stm.setDouble(2, SUMOFPRICE);
            stm.setString(3, DETAILBILLID);
            stm.setString(4, FOODID);
            stm.setString(5, TABLEID);

            int resultUpdate = stm.executeUpdate();
            if (resultUpdate > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean paidBill(String idTable) {
        try (
                Connection snn = ConnectDBHelper.getConnect();
                PreparedStatement stm = snn.prepareStatement(
                        "UPDATE `tblbill` SET `BillStatus`= ? WHERE TABLEID = ?");) {

            stm.setString(1, "Paid");
            stm.setString(2, idTable);

            int resultUpdate = stm.executeUpdate();
            if (resultUpdate > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    // SELECT `FOODID`, `TABLEID` FROM `tbldetailbill` WHERE FOODID = 'F01'
    public static boolean checkExistFood(String DETAILBILLID, String FOODID, String TABLEID) {
        try (
                Connection snn = ConnectDBHelper.getConnect();
                PreparedStatement stm = snn.prepareStatement(
                        "SELECT `DETAILBILLID`, `FOODID`, `TABLEID`FROM `tbldetailbill` WHERE `DETAILBILLID` = ? AND `FOODID` = ? AND `TABLEID` = ?");) {
            stm.setString(1, DETAILBILLID);
            stm.setString(2, FOODID);
            stm.setString(3, TABLEID);

            int resultUpdate = stm.executeUpdate();
            if (resultUpdate > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static int getQuantityFood(String DETAILBILLID, String FOODID, String TABLEID) {
        int getQuantity = 0;
        try (Connection connect = ConnectDBHelper.getConnect();
                PreparedStatement stm = connect
                        .prepareStatement(
                                "SELECT `FOODQUANTITY` FROM `tbldetailbill` WHERE `DETAILBILLID` = ? AND `FOODID` = ? AND `TABLEID` = ?");) {
            stm.setString(1, DETAILBILLID);
            stm.setString(2, FOODID);
            stm.setString(3, TABLEID);
            stm.execute();
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                getQuantity = rs.getInt("FOODQUANTITY");
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return getQuantity;
    }

    public static String getBillCodeOfTable(String TABLEID) {
        String codeBill = null;
        try (Connection connect = ConnectDBHelper.getConnect();
                PreparedStatement stm = connect
                        .prepareStatement(
                                "SELECT `BILLID` FROM `tblbill` WHERE `TABLEID` = ? AND BillStatus = 'UnPaid'");) {
            stm.setString(1, TABLEID);
            stm.execute();
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                codeBill = rs.getString("BILLID");
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return codeBill;
    }

    public static List<Bill_Model> getDataForBill(String BillID) {
        List<Bill_Model> data = new ArrayList<>();
        try (Connection cnn = ConnectDBHelper.getConnect();
                PreparedStatement stm = cnn
                        .prepareStatement(
                                "SELECT a.BILLID, a.DATETIME, a.TABLEID, d.FOODNAME, b.FOODQUANTITY, d.FOODPRICE, c.DISCOUNT FROM `tblbill` a join tbldetailbill b on a.DETAILBILLID=b.DETAILBILLID join tblsaledetail c on a.SALECODE=c.SALECODE join tblfoodmenu d on d.FOODID = b.FOODID WHERE a.BILLID = ?");) {

            stm.setString(1, BillID);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String colNameFood = rs.getString("d.FOODNAME");
                int colAmount = rs.getInt("b.FOODQUANTITY");
                Double colUnitPrice = rs.getDouble("d.FOODPRICE");

                Bill_Model billPay = new Bill_Model(colNameFood, colAmount, colUnitPrice);
                data.add(billPay);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    public static boolean ChangeStatusTable(String status, String tableID) {
        try (
                Connection snn = ConnectDBHelper.getConnect();
                PreparedStatement stm = snn.prepareStatement(
                        "UPDATE `tbltablemap` SET `TABLESTATUS`= ? WHERE `TABLEID` = ?");) {
            stm.setString(1, status);
            stm.setString(2, tableID);

            int resultUpdate = stm.executeUpdate();
            if (resultUpdate > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }



}
