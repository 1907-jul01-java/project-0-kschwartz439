package com.revature.dao;

import java.util.Scanner;
import java.sql.*;

public class LoginDao{
    Scanner scanner = new Scanner(System.in);
    String username;
    String password;
    String access;
    Connection connection;

    public void CheckPassword(){
        System.out.println("Please enter your password.\n");
        password = scanner.next();

        while (password.equals(null)){
            System.out.println("Please enter a valid argument and try again.");
            password = scanner.next();
        }
        try (PreparedStatement upStatement = connection.prepareStatement("SELECT userPass FROM userLogins WHERE ? = ? AND userPass = ?")){
            upStatement.setString(1, "username");
            upStatement.setString(2, username);
            upStatement.setString(3, password);
            ResultSet upResult = upStatement.executeQuery();
            if (!upResult.next()){
                System.out.println("Please enter a valid argument and try again.\n");
                this.CheckPassword();
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
    }
    public void CheckUserName(){
        System.out.println("Please enter your username.\n");
        username = scanner.next();

        while (username.equals(null)){
            System.out.println("Please enter a valid argument and try again.\n");
            username = scanner.next();
        }
        try (PreparedStatement unStatement = connection.prepareStatement("SELECT username FROM userLogins WHERE username = ?")) {
            unStatement.setString(1, username);
            ResultSet unResult = unStatement.executeQuery();
            if (!unResult.next()){
                System.out.println("Please enter a valid argument and try again.\n");
                this.CheckUserName();
            }
            this.CheckPassword();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void CheckAccess(){  
        try (PreparedStatement acStatement = connection.prepareStatement("SELECT access FROM users INNER JOIN userLogins WHERE username = ?")) {
            acStatement.setString(1, username);
            ResultSet acResult = acStatement.executeQuery();
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
            e.getMessage();
        }
    }

    public void Logout(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}