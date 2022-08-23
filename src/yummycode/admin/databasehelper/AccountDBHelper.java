package yummycode.admin.databasehelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.mysql.cj.xdevapi.Type;

import javax.mail.Message;

import yummycode.admin.frontend.Navigator;
import javafx.scene.control.Alert;
import yummycode.model.Account;
import yummycode.model.RandomGenerator;

public class AccountDBHelper {
    // This function will get all Account from database
    public static List<Account> getAllAccount() {
        List<Account> listAcc = new ArrayList<>();

        try (Connection connect = ConnectDBHelper.getConnect();
                CallableStatement stm = connect.prepareCall("{CALL getAllAccount()}");) {
            stm.execute();
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                String id = rs.getString("ACCOUNTID");
                String username = rs.getString("NAME");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                String type = rs.getString("TYPE");
                String status = rs.getString("STATUS");

                Account acc = new Account(id, username, email, password, type, status);

                listAcc.add(acc);
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.getMessage());
        }
        return listAcc;
    }

    public static boolean addNewAccount(String AccID, String Name, String Email, String Pass, String Type) {
        // String query = "INSERT INTO `tblaccount`( `ACCOUNTID`, `NAME`, `EMAIL`,
        // `PASSWORD`, `TYPE`) VALUES (?,?,?,?,?)";

        try (
                Connection cnn = ConnectDBHelper.getConnect();
                CallableStatement stm = cnn.prepareCall("{CALL AddNewAccount(?,?,?,?,?)}");) {
            stm.setString(1, AccID);
            stm.setString(2, Name);
            stm.setString(3, Email);
            stm.setString(4, Pass);
            stm.setString(5, Type);

            int resultInsert;

            resultInsert = stm.executeUpdate();
            if (resultInsert > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.getMessage());
        }

        return false;
    }

    public static boolean EditAccount(String name, String email, String pass, String type, String status,
            String idAcc) {
        // String query = "UPDATE `tblaccount` SET `NAME`= ? ,`EMAIL`= ? ,`PASSWORD`=
        // ?,`TYPE`= ? ,`STATUS`= ? WHERE `ACCOUNTID` = ? ";

        try (
                Connection snn = ConnectDBHelper.getConnect();
                CallableStatement stm = snn.prepareCall("{CALL EditAccount(?,?,?,?,?,?)}");) {
            stm.setString(1, name);
            stm.setString(2, email);
            stm.setString(3, pass);
            stm.setString(4, type);
            stm.setString(5, status);
            stm.setString(6, idAcc);

            int resultUpdate = stm.executeUpdate();
            if (resultUpdate > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static List<Account> searchAccount(String input) {
        List<Account> listAcc = new ArrayList<>();
        String query = "";
        int Existed = 0;
        for (int i = 0; i < input.length(); i++) {
            Existed = input.indexOf("@");
        }

        if (Existed != -1) {
            query = "SELECT * FROM `tblaccount` WHERE `EMAIL` like " + "'%" + input + "%'";
        } else if (input.trim().startsWith("#")) {
            query = "SELECT * FROM `tblaccount` WHERE `ACCOUNTID` like " + "'%" + input + "%'";
        } else {
            query = "SELECT * FROM `tblaccount` WHERE `NAME` like  " + "'%" + input + "%'";
            // query = "{CALL searchByNameAcc("+ input +")}";

        }

        try (Connection connect = ConnectDBHelper.getConnect();
                PreparedStatement stm = connect.prepareStatement(query);) {
            stm.execute();
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                String id = rs.getString("ACCOUNTID");
                String username = rs.getString("NAME");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                String type = rs.getString("TYPE");
                String status = rs.getString("STATUS");

                Account acc = new Account(id, username, email, password, type, status);

                listAcc.add(acc);
            }
        } catch (Exception e) {
            System.out.println("Loi:" + e.getMessage());
        }

        return listAcc;
    }

    public static boolean DeleteAccount(String ID) {
        String query = "{CALL deleteAccByID(?)}";
        try (
                Connection cnn = ConnectDBHelper.getConnect();
                CallableStatement stm = cnn.prepareCall(query);) {
            stm.setString(1, ID);

            int resultUpdate = stm.executeUpdate();
            if (resultUpdate > 0) {
                return true;
            }

        } catch (Exception e) {
        }
        return false;

    }

    public static String randomString() {
        String characters = "abcdefghijklmnopqrstuvwmxyzABCDEFGHIJKLMNOPQRSTUVWMXYZ1234567890";
        String randomString = "";
        int length = 7;
        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        for (int i = 0; i < text.length; i++) {
            randomString += text[i];
        }
        System.out.print(randomString);
        return randomString;
    }

    public static String getOTP(String email) throws SQLException {
        try (Connection conn = ConnectDBHelper.getConnect()) {
            String query = "SELECT OTP FROM tblaccount where EMAIL = ? Limit 1";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("OTP");
            }
        }
        return null;
    }

    public static String getPassword(String email) throws SQLException {
        try (Connection conn = ConnectDBHelper.getConnect()) {
            String query = "SELECT PASSWORD FROM tblaccount where EMAIL = ? Limit 1";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("PASSWORD");
            }
        }
        return null;

    }

    //

    public static boolean checkEmailAlreadyExist(Account Acc) {
        // for (Acc : ) {

        // }

        return false;

    }

    //
    public static Boolean checkEmailRegex(String email) {
        String regex = "[a-zA-Z0-9_\\.]{3,20}@[a-zA-Z0-9]{3,10}\\.[a-zA-Z0-9]{2,5}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean match = matcher.matches();
        return match;
    }

    public static Boolean checkPasswordRegex(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        boolean match = matcher.matches();
        return match;
    }

    public static void AdminLogIn(String email, String password) throws IOException {
        String emailCheck = "";
        String passwordCheck = "";
        String status = "";
        String type = "";
        List<Account> listAccount = AccountDBHelper.getAllAccount();
        loop: for (int i = 0; i <= listAccount.size(); i++) {
            emailCheck = listAccount.get(i).getEmail();
            passwordCheck = listAccount.get(i).getPassword();
            status = listAccount.get(i).getStatus();
            type = listAccount.get(i).getType();
            if (email.isEmpty() || password.isEmpty()) {
                Navigator.getInstance().showAlert(Alert.AlertType.ERROR, "Please fill out the form!");
            } else if (!checkEmailRegex(email)) {
                Navigator.getInstance().showAlert(Alert.AlertType.ERROR, "Email is invalid");
            } else {

                /* if (!checkPasswordRegex(password)) {
                    Navigator.getInstance().showAlert(Alert.AlertType.ERROR,
                    "Password must be 10 characters. Include numbers,lowercase letters, uppercase letters.");
                } else  */if (status.equalsIgnoreCase("Locked")) {
                    Navigator.getInstance().showAlert(Alert.AlertType.ERROR, "Your Account is Locked!");
                } else if (!email.equals(emailCheck) || !password.equals(passwordCheck)) {
                    Navigator.getInstance().showAlert(Alert.AlertType.ERROR, "Email or Password is wrong!");
                } else {
                    if (type.equalsIgnoreCase("Admin")) {
                        Navigator.getInstance().goToAdminHome2();
                      
                    } else if (type.equalsIgnoreCase("Employee")) {
                        Navigator.getInstance().goToEmployeeTableMap();
                        break loop;
                    }
                }
            }
        }
    }

    public static void updatePassword(String email) {
        RandomGenerator passwordGenerator = new RandomGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String password = passwordGenerator.generate(10);
        try (Connection connect = ConnectDBHelper.getConnect();
                CallableStatement stm = connect.prepareCall("{call updatePassword(?,?)}");) {
            stm.setString(1, password);
            stm.setString(2, email);
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Loi:" + e.getMessage());
        }
    }

    public static void updateOTP(String email, String OTP) {
        try (Connection connect = ConnectDBHelper.getConnect();
                CallableStatement stm = connect.prepareCall("{call updateOTP(?,?)}");) {
            stm.setString(1, OTP);
            stm.setString(2, email);
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Loi:" + e.getMessage());
        }
    }

    public static void sendMail(String toEmail) throws MessagingException, SQLException {
        String usermail = "vanluanthcs.com@gmail.com";
        String password = "nguyenvanluan";
        RandomGenerator passwordGenerator = new RandomGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String OTP = passwordGenerator.generate(7);
        if (!OTP.equals(getOTP(toEmail))) {
            updateOTP(toEmail, OTP);
        }
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usermail, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setSubject("Email from luan");

        Address addressTo = new InternetAddress(toEmail);
        message.setSubject("YUMMY GANG");
        message.setRecipient(Message.RecipientType.TO, addressTo);
        MimeMultipart multipart = new MimeMultipart();

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("<h1>" + OTP + "</h1>", "text/html");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        Transport.send(message);
        System.out.print("DONE");
    }

}
