package com.revature.dao;

import java.util.Scanner;

import com.revature.connectionutils.ConnectionUtils;

import java.sql.*;

public class LoginDao{
    Scanner scanner = new Scanner(System.in);
    String username;
    String password;
    String access;

    public void CheckPassword(String username){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        System.out.println("Please enter your password.\n");
        password = scanner.next();
        while (password.equals(null)){
            System.out.println("Please enter a valid argument and try again.");
            password = scanner.next();
        }
        try (PreparedStatement upStatement = connection.prepareStatement("SELECT userPass FROM userLogins WHERE username = ? AND userPass = ?")){
            upStatement.setString(1, username);
            upStatement.setString(2, password);
            ResultSet upResult = upStatement.executeQuery();
            if (!upResult.next()){
                System.out.println("Please enter a valid argument and try again.\n");
                this.CheckPassword(username);
            }
            this.CheckAccess(username);
        }
        catch (SQLException e){
            e.getMessage();
        }
    }
    public void CheckUserName(){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        System.out.println("URL: " + conUtil.getUrl() + " password: " + conUtil.getPassword() + "User: " + conUtil.getUser() + " Connection: " + conUtil.getConnection().toString());

        System.out.println("Please enter your username.\n");
        username = scanner.next();

        while (username.equals(null)){
            System.out.println("Please enter a valid argument and try again.\n");
            username = scanner.next();
        }
        try  {
            PreparedStatement unStatement = connection.prepareStatement("SELECT * FROM userLogins WHERE username = ?");
            unStatement.setString(1, username);
            ResultSet unResult = unStatement.executeQuery();
            if (!unResult.next()){
                System.out.println("Please enter a valid argument and try again.\n");
                this.CheckUserName();
            }
            if (username.equals(unResult.getString("username"))){
                this.CheckPassword(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void CheckAccess(String username){  
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        try (PreparedStatement acStatement = connection.prepareStatement("SELECT * FROM users JOIN userLogins ON id = userId WHERE userLogins.username = ?")) {
            acStatement.setString(1, username);
            ResultSet acResult = acStatement.executeQuery();
            acResult.next();
            access = acResult.getString("access");
            switch (access){
                //Customer
                case "customer": CustomerDao cDao = new CustomerDao();
                    cDao.menu();
                //Employee
                case "employee": EmployeeDao eDao = new EmployeeDao();
                    eDao.menu();
                //Admin
                case "admin": AdminDao aDao = new AdminDao();
                    aDao.menu();
                //Creator
                case "creator": CreatorDao crDao = new CreatorDao();
                    crDao.menu();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Logout(){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        try {
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}