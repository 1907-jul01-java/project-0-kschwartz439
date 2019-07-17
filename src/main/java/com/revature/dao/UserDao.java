package com.revature.dao;

import java.sql.*;
import java.util.Scanner;
import com.revature.connectionutils.*;

public class UserDao{
    String answer3;
    String answer2;
    String answer;
    String answer4;
    int accId;
    String username;
    String firstName;
    String lastName;
    int userId;
    int answerI;
    Connection connection;
    Scanner scanner = new Scanner(System.in);
    LoginDao lDao = new LoginDao();

    public void GetName(){
        username = lDao.username;
        try (PreparedStatement nStatement = connection.prepareStatement("SELECT * FROM users INNER JOIN userLogins ON id = userId WHERE userLogins.username = ?")) {
            nStatement.setString(1, username);
            ResultSet nResult = nStatement.executeQuery();
            lastName = nResult.getString("lastName");
            firstName = nResult.getString("firstName");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void GetUserID(){
        username = lDao.username;
        try (PreparedStatement idStatement = connection.prepareStatement("SELECT * FROM userLogins WHERE username = ?")) {
            idStatement.setString(1, username);
            ResultSet idResult = idStatement.executeQuery();
            userId = idResult.getInt("userId");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void GetAllAdmins(){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();

        try {
            PreparedStatement empStatement = connection.prepareStatement("SELECT * FROM users INNER JOIN userLogins ON id = userId WHERE users.access = 'admin'");
            ResultSet empResult = empStatement.executeQuery();
            while (empResult.next()){
                System.out.println(empResult.getString("username") + " id:" + empResult.getInt("id") + "\n");
                empResult.next();
            }
            System.out.println("Please enter the id of the account you would like to edit or enter a new id to add a new account to the admin listing.\n");
            answerI = scanner.nextInt();
            PreparedStatement adStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            adStatement.setInt(1, answerI);
            ResultSet adResult = adStatement.executeQuery();
            adResult.next();
            answerI = adResult.getInt("id");
            accId = answerI;
            Integer answerII = answerI;
            if (!answerII.equals(0)){
                if (answerII.equals(adResult.getInt("id"))){
                    System.out.println("Please enter the first name of account holder.\n");
                    answer2 = scanner.next();
                    if (!answer2.equals(null)){
                        firstName = answer2;
                    }
                        System.out.println("Please enter the last name of account holder.\n");
                        answer2 = scanner.next();
                        if (!answer2.equals(null)){
                            lastName = answer2;
                        }
                            System.out.println("Please enter the access level for this account or enter 'delete' to delete the account.");
                            answer4 = scanner.next();
                            switch (answer4){
                                case "employee": try (PreparedStatement emp2Statment = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, access = ? WHERE id = ?")) {
                                    emp2Statment.setString(1, firstName);
                                    emp2Statment.setString(2, lastName);
                                    emp2Statment.setString(3, "employee");
                                    emp2Statment.setInt(4, accId);
                                    emp2Statment.executeUpdate();
                                    System.out.println("The account has been updated. Thank you.\n");
                                } catch (SQLException e) {
                                    e.getMessage();
                                }
                                    break;
                                case "customer": try (PreparedStatement emp2Statement = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, access = ? WHERE id = ?")) {
                                    emp2Statement.setString(1, firstName);
                                    emp2Statement.setString(2, lastName);
                                    emp2Statement.setString(3, "customer");
                                    emp2Statement.setInt(4, accId);
                                    emp2Statement.executeUpdate();
                                    System.out.println("The account has been updated. Thank you.\n");
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                    break;
                                case "delete":  try (PreparedStatement emp2Statement = connection.prepareStatement("DELETE * FROM users WHERE id = ?")) {
                                    emp2Statement.setString(1, answer);
                                    try (PreparedStatement emp3Statement = connection.prepareStatement("DELETE * FROM userLogins WHERE userId = ?")){
                                        emp3Statement.setString(1, answer);
                                        emp3Statement.executeUpdate();
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                case "admin": try {
                                    PreparedStatement ad2Statement = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, access = ? WHERE id = ?");
                                    ad2Statement.setString(3, "admin");
                                    ad2Statement.setInt(4, accId);
                                    ad2Statement.setString(1, firstName);
                                    ad2Statement.setString(2, lastName);
                                    ad2Statement.executeUpdate();
                                }
                                catch (SQLException e){
                                    e.printStackTrace();
                                }
                                    break;
                                default:
                                  break;
                            }
                        }
                    }
            }
         catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetAllEmployees(){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        try {
            PreparedStatement empStatement = connection.prepareStatement("SELECT * FROM users INNER JOIN userLogins ON id = userId WHERE users.access = 'admin'");
            ResultSet empResult = empStatement.executeQuery();
            while (empResult.next()){
                System.out.println(empResult.getString("username") + " id:" + empResult.getInt("id") + "\n");
                empResult.next();
            }
            System.out.println("Please enter the id of the account you would like to edit or enter a new id to add a new account to the admin listing.\n");
            answerI = scanner.nextInt();
            PreparedStatement adStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            adStatement.setInt(1, answerI);
            ResultSet adResult = adStatement.executeQuery();
            adResult.next();
            answerI = adResult.getInt("id");
            accId = answerI;
            Integer answerII = answerI;
            if (!answerII.equals(0)){
                if (answerII.equals(adResult.getInt("id"))){
                    System.out.println("Please enter the first name of account holder.\n");
                    answer2 = scanner.next();
                    if (!answer2.equals(null)){
                        firstName = answer2;
                    }
                        System.out.println("Please enter the last name of account holder.\n");
                        answer2 = scanner.next();
                        if (!answer2.equals(null)){
                            lastName = answer2;
                        }
                            System.out.println("Please enter the access level for this account or enter 'delete' to delete the account.");
                            answer4 = scanner.next();
                            switch (answer4){
                                case "employee": try (PreparedStatement emp2Statment = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, access = ? WHERE id = ?")) {
                                    emp2Statment.setString(1, firstName);
                                    emp2Statment.setString(2, lastName);
                                    emp2Statment.setString(3, "employee");
                                    emp2Statment.setInt(4, accId);
                                    emp2Statment.executeUpdate();
                                    System.out.println("The account has been updated. Thank you.\n");
                                } catch (SQLException e) {
                                    e.getMessage();
                                }
                                    break;
                                case "customer": try (PreparedStatement emp2Statement = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, access = ? WHERE id = ?")) {
                                    emp2Statement.setString(1, firstName);
                                    emp2Statement.setString(2, lastName);
                                    emp2Statement.setString(3, "customer");
                                    emp2Statement.setInt(4, accId);
                                    emp2Statement.executeUpdate();
                                    System.out.println("The account has been updated. Thank you.\n");
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                    break;
                                case "delete":  try (PreparedStatement emp2Statement = connection.prepareStatement("DELETE * FROM users WHERE id = ?")) {
                                    emp2Statement.setString(1, answer);
                                    try (PreparedStatement emp3Statement = connection.prepareStatement("DELETE * FROM userLogins WHERE userId = ?")){
                                        emp3Statement.setString(1, answer);
                                        emp3Statement.executeUpdate();
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                default:
                                  break;
                            }
                        }
                    }
            }
         catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetAllCustomers(){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        try {
            PreparedStatement empStatement = connection.prepareStatement("SELECT * FROM users INNER JOIN userLogins ON id = userId WHERE users.access = 'admin'");
            ResultSet empResult = empStatement.executeQuery();
            while (empResult.next()){
                System.out.println(empResult.getString("username") + " id:" + empResult.getInt("id") + "\n");
                empResult.next();
            }
            System.out.println("Please enter the id of the account you would like to edit or enter a new id to add a new account to the admin listing.\n");
            answerI = scanner.nextInt();
            PreparedStatement adStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            adStatement.setInt(1, answerI);
            ResultSet adResult = adStatement.executeQuery();
            adResult.next();
            answerI = adResult.getInt("id");
            accId = answerI;
            Integer answerII = answerI;
            if (!answerII.equals(0)){
                if (answerII.equals(adResult.getInt("id"))){
                    System.out.println("Please enter the first name of account holder.\n");
                    answer2 = scanner.next();
                    if (!answer2.equals(null)){
                        firstName = answer2;
                    }
                        System.out.println("Please enter the last name of account holder.\n");
                        answer2 = scanner.next();
                        if (!answer2.equals(null)){
                            lastName = answer2;
                        }
                            System.out.println("Please enter the access level for this account or enter 'delete' to delete the account.");
                            answer4 = scanner.next();
                            switch (answer4){
                                case "customer": try (PreparedStatement emp2Statement = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, access = ? WHERE id = ?")) {
                                    emp2Statement.setString(1, firstName);
                                    emp2Statement.setString(2, lastName);
                                    emp2Statement.setString(3, "customer");
                                    emp2Statement.setInt(4, accId);
                                    emp2Statement.executeUpdate();
                                    System.out.println("The account has been updated. Thank you.\n");
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                    break;
                                case "delete":  try (PreparedStatement emp2Statement = connection.prepareStatement("DELETE * FROM users WHERE id = ?")) {
                                    emp2Statement.setString(1, answer);
                                    try (PreparedStatement emp3Statement = connection.prepareStatement("DELETE * FROM userLogins WHERE userId = ?")){
                                        emp3Statement.setString(1, answer);
                                        emp3Statement.executeUpdate();
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                default:
                                  break;
                            }
                        }
                    }
            }
         catch (SQLException e) {
            e.printStackTrace();
        }
    }
}