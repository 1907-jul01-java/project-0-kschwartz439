package com.revature.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountDao{
    String answer;
    double answerD;
    int answerI;
    double accBalance1;
    double accBalance2;
    Scanner scanner = new Scanner(System.in);
    Connection connection;
    UserDao uDao = new UserDao();
    LoginDao lDao = new LoginDao();

    public void Deposit(){
        this.SelectAccount();
        System.out.println("How much would you like to deposit?\n");
        answerD = scanner.nextDouble();
        if (!scanner.hasNextDouble()){
            System.out.println("Please enter a valid argument and try again.");
            answerD = scanner.nextDouble();
        }
        try (PreparedStatement depStatement = connection.prepareStatement("SELECT * FROM accounts WHERE id = ?")) {
            depStatement.setInt(1, uDao.userId);
            ResultSet depResult = depStatement.executeQuery();
            accBalance1 = depResult.getDouble("accountBalance");
            accBalance2 = accBalance1 + answerD;
        } catch (SQLException e) {
            e.getMessage();
        }
        try (PreparedStatement dep2Statement = connection.prepareStatement("UPDATE accounts accountBalance = ? WHERE accountNumber = ?")) {
            dep2Statement.setDouble(1, accBalance2);
            dep2Statement.setInt(2, uDao.userId);
            dep2Statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
        System.out.println("Your account has been updated.\nYour new balance is " + accBalance2 + ".\nPress enter to continue.");
        scanner.next();
    }

    public void Withdraw(){
        this.SelectAccount();
        System.out.println("How much would you like to withdraw?\n");
        answerD = scanner.nextDouble();
        if (!scanner.hasNextDouble()){
            System.out.println("Please enter a valid argument and try again.");
            answerD = scanner.nextDouble();
        }
        try (PreparedStatement witStatement = connection.prepareStatement("SELECT * FROM accounts WHERE id = ?")) {
            witStatement.setInt(1, uDao.userId);
            ResultSet witResult = witStatement.executeQuery();
            accBalance1 = witResult.getDouble("accountBalance");
            if (accBalance1 < answerD){
                System.out.println("You cannot withdraw more money than you have.\nPlease try again.\n");
                this.Withdraw();
            }
            accBalance2 = accBalance1 - answerD;
        } catch (SQLException e) {
            e.getMessage();
        }
        try (PreparedStatement wit2Statement = connection.prepareStatement("UPDATE accounts accountBalance = ? WHERE id = ?")) {
            wit2Statement.setDouble(1, accBalance2);
            wit2Statement.setInt(2, uDao.userId);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void Apply(){
        System.out.println("What would you like to call your account?\n");
        answer = scanner.next();
        try (PreparedStatement appStatement = connection.prepareStatement("INSERT INTO accounts(accountName, accountBalance, approved) VALUES (?, ?, ?)")) {
            appStatement.setString(1, answer);
            appStatement.setDouble(2, 0.00);
            appStatement.setBoolean(3, false);
            PreparedStatement app3Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountName = ?");
            app3Statement.setString(1, answer);
            ResultSet appResult = app3Statement.executeQuery();
            answerI = appResult.getInt("accountNumber");
            PreparedStatement app2Statement = connection.prepareStatement("INSERT INTO usersAccounts(userRef, accountRef) VALUES (?, ?)");
            app2Statement.setInt(1, uDao.userId);
            app2Statement.setInt(2, answerI);
        } catch (SQLException e) {
            e.getMessage();
        }
        System.out.println("Your application has been accepted and is currently processing.\nOnce an admin approves it, you will be able to start banking.\nThank you and have a nice day.\n");
    }

    public void Create(){

    }

    public void ApplyJoint(){
        System.out.println("Please enter a username, or 'null' to finish entering people.\n");
        answer = scanner.next();
        if (!answer.equals(null)){
            ArrayList<userIDs> = new ArrayList<>();
            lDao.CheckUserName();
            uDao.GetUserID();
            //add UserID into the arraylist.
        }
        System.out.println("What would you like to call the account?");
        answer = scanner.next();
        try {
            PreparedStatement appJStatement = connection.prepareStatement("INSERT INTO accounts(accountName, accountBalance, approved) VALUES (?, ?, ?)");
            appJStatement.setString(1, answer);
            appJStatement.setDouble(2, 0.00);
            appJStatement.setBoolean(3, false);
            PreparedStatement appJ3Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountName = ?");
            appJ3Statement.setString(1, answer);
            ResultSet appJ3Result = appJ3Statement.executeQuery();
            answerI = appJ3Result.getInt("accountNumber");
            PreparedStatement appJ2Statement = connection.prepareStatement("INSERT INTO usersAccounts(userRef, accountRef) VALUES (?, ?)");
            appJ2Statement.setInt(1, uDao.userId);
            appJ2Statement.setInt(2, answerI);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void Delete(){

    }

    public void ViewOwn(){
        try {
            PreparedStatement voStatement = connection.prepareStatement("SELECT * FROM usersAccounts WHERE userRef = ?");
            voStatement.setInt(1, uDao.userId);
            ResultSet voResult = voStatement.executeQuery();
            answerI = voResult.getInt("accountRef");
            PreparedStatement vo2Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
            vo2Statement.setInt(1, answerI);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ViewOther(){

    }

    public void ViewAll(){
        try {
            PreparedStatement vaStatement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet vaResult = vaStatement.executeQuery();
            while (vaResult.next()){
                System.out.println(vaResult + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SelectAccount(){
        System.out.println("Which account will you be making changes to today?");
        try{
            PreparedStatement selStatement = connection.prepareStatement("SELECT * FROM usersAccounts WHERE userRef = ?");
            selStatement.setInt(1, uDao.userId);
            ResultSet selResult = selStatement.executeQuery();
            /*ArrayList(int) for the accounts.*/ = selResult.getInt("accountRef");
            //scanner, then check input against the arraylist
            int acRef = //input
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}