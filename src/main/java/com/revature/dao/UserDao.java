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
    String firstName;
    String lastName;
    int userID;
    int answerI;
    boolean b = false;
    ConnectionUtils conUtil = new ConnectionUtils();
    Connection connection = conUtil.getConnection();
    Scanner scanner = new Scanner(System.in);
    LoginDao lDao = new LoginDao();

    public void GetName(int userID){
        try (PreparedStatement nStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            nStatement.setInt(1, userID);
            ResultSet nResult = nStatement.executeQuery();
            lastName = nResult.getString("lastName");
            firstName = nResult.getString("firstName");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetUserID(String userJoint){
        try {
            PreparedStatement idStatement = connection.prepareStatement("SELECT * FROM userLogins WHERE username = ?");
            idStatement.setString(1, userJoint);
            ResultSet idResult = idStatement.executeQuery();
            idResult.next();
            userID = idResult.getInt("userId");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetAllAdmins(int userID){
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
        finally{
            CreatorDao crDao = new CreatorDao();
            crDao.menu(userID);
        }
    }

    public void GetAllEmployees(int userID){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        try {
            PreparedStatement empStatement = connection.prepareStatement("SELECT * FROM users INNER JOIN userLogins ON id = userId WHERE users.access = 'employee'");
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
        finally{
            AdminDao adDao = new AdminDao();
            adDao.menu(userID);
        }
    }

    public void GetAllCustomers(int userID){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        try {
            PreparedStatement empStatement = connection.prepareStatement("SELECT * FROM users INNER JOIN userLogins ON id = userId WHERE users.access = 'customer'");
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

    public void GetUserJoint(String unJoint){
        try {
            PreparedStatement unjStatement = connection.prepareStatement("SELECT * FROM userLogins");
            ResultSet unjResult = unjStatement.executeQuery();
            unjResult.next();
            if (unJoint==unjResult.getString("username")){
                b=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CheckUserID(int userID){
        try {
            PreparedStatement cuidStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            cuidStatement.setInt(1, userID);
            ResultSet cuidResult = cuidStatement.executeQuery();
            cuidResult.next();
            if (answerI!=cuidResult.getInt("id")){
                System.out.println("Please input a valid argument and try again.");
                this.CheckUserID(userID);
            }
            
    } catch(SQLException e){
        e.printStackTrace();
    }}

    public void CreatorGetEmployees(int userID){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();

        try {
            PreparedStatement empStatement = connection.prepareStatement("SELECT * FROM users INNER JOIN userLogins ON id = userId WHERE users.access = 'employee'");
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
        finally{
            CreatorDao crDao = new CreatorDao();
            crDao.menu(userID);
        }
    }  
    
    public void CreatorGetCustomer(int userID){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();

        try {
            PreparedStatement empStatement = connection.prepareStatement("SELECT * FROM users INNER JOIN userLogins ON id = userId WHERE users.access = 'customer'");
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
        }finally{
            CreatorDao crDao = new CreatorDao();
            crDao.menu(userID);
        }
    }

    public void AdminGetCustomer(int userID){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        try {
            PreparedStatement empStatement = connection.prepareStatement("SELECT * FROM users INNER JOIN userLogins ON id = userId WHERE users.access = 'customer'");
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
        finally{
            AdminDao adDao = new AdminDao();
            adDao.menu(userID);
        }
    }

}