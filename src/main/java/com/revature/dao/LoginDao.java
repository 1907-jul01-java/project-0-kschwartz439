package com.revature.dao;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.App;
import com.revature.connectionutils.ConnectionUtils;

import java.sql.*;

public class LoginDao{
    Scanner scanner = new Scanner(System.in);
    String username;
    String password;
    String access;
    String answer;

    public void CheckPassword(int userID){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        System.out.println("Please enter your password.\n");
        password = scanner.next();
        while (password.equals(null)){
            System.out.println("Please enter a valid argument and try again.");
            password = scanner.next();
        }
        try (PreparedStatement upStatement = connection.prepareStatement("SELECT userPass FROM userLogins WHERE userId = ? AND userPass = ?")){
            upStatement.setInt(1, userID);
            upStatement.setString(2, password);
            ResultSet upResult = upStatement.executeQuery();
            if (!upResult.next()){
                System.out.println("Please enter a valid argument and try again.\n");
                this.CheckPassword(userID);
            }
            this.CheckAccess(userID);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void CreateUserName(){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        int number;
        System.out.println("Please enter your username.\n");
        username = scanner.next();
        try {
            PreparedStatement chkStatement = connection.prepareStatement("SELECT * FROM userLogins");
            ResultSet chkResult = chkStatement.executeQuery();
            chkResult.next();
            ArrayList<String> userIds = new ArrayList<String>();
            while (chkResult.next()){
                userIds.add(chkResult.getString("username"));
            }
            for (int i = 0; i < userIds.size(); i++){
                if (username == userIds.get(i)){
                    System.out.println("Please try again.");
                    this.CreateUserName();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        System.out.println("Please enter your password.\n");
        password = scanner.next();
        try {
            PreparedStatement cunStatement = connection.prepareStatement("INSERT INTO userLogins (username, userPass) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            cunStatement.setString(1, username);
            cunStatement.setString(2, password);
            cunStatement.executeUpdate();
            ResultSet cunResult = cunStatement.getGeneratedKeys();
            cunResult.next();
            number = cunResult.getInt(3);
            System.out.println("Please enter your first name.");
        String name = scanner.next();
        System.out.println("Please enter your last name.");
        String lname = scanner.next();
        try {
            PreparedStatement insStatement = connection.prepareStatement("INSERT INTO users (firstName, lastName, id, access) VALUES (?, ?, ?, 'customer')");
            insStatement.setString(1, name);
            insStatement.setString(2, lname);
            insStatement.setInt(3, number);
            insStatement.executeUpdate();
            insStatement.close();
            cunStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            System.out.println("Thank you for signing up. Returning to the main menu.\n");
            this.Startup();
        }
    }

    public void Startup(){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        boolean b = true;
        if(b=true){
        System.out.println("1. Log in.\n2. Sign up.\n3. Shutdown.\n ");
        b=true;
        answer = scanner.next();
        switch (answer){
            case "1": this.CheckUserName();
                break;
            case "2": this.CreateUserName();
                break;
            case "3": try{connection.close();}
            catch (SQLException e){e.printStackTrace();}
            b=false;
            System.out.println("Thank you for banking with us.\n");
            System.exit(1);
            break;
            default: this.Startup();
            break;
        }
    }
    }

    public void CheckUserName(){
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();

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
            int userID = unResult.getInt("userId");
            if (username.equals(unResult.getString("username"))){
                this.CheckPassword(userID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void CheckAccess(int userID){  
        ConnectionUtils conUtil = new ConnectionUtils();
        Connection connection = conUtil.getConnection();
        try (PreparedStatement acStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            acStatement.setInt(1, userID);
            ResultSet acResult = acStatement.executeQuery();
            acResult.next();
            access = acResult.getString("access");
            switch (access){
                //Customer
                case "customer": CustomerDao cDao = new CustomerDao();
                    cDao.menu(userID);
                //Employee
                case "employee": EmployeeDao eDao = new EmployeeDao();
                    eDao.menu(userID);
                //Admin
                case "admin": AdminDao aDao = new AdminDao();
                    aDao.menu(userID);
                //Creator
                case "creator": CreatorDao crDao = new CreatorDao();
                    crDao.menu(userID);
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
        this.Startup();
    }

}