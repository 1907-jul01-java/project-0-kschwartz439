package com.revature.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import com.revature.connectionutils.*;

public class AccountDao{
    String answer;
    double answerD;
    int answerI;
    double accBalance1;
    double accBalance2;
    Scanner scanner = new Scanner(System.in);
    ConnectionUtils conUtil = new ConnectionUtils();
    Connection connection = conUtil.getConnection();
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
        try {
            PreparedStatement appStatement = connection.prepareStatement("INSERT INTO accounts (accountName, accountBalance, approved) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            appStatement.setString(1, answer);
            appStatement.setDouble(2, 0.00);
            appStatement.setBoolean(3, false);
            appStatement.executeUpdate();
            ResultSet app2Result = appStatement.getGeneratedKeys();
            app2Result.next();
            answerI = app2Result.getInt(1);
            PreparedStatement app2Statement = connection.prepareStatement("INSERT INTO usersAccounts (userRef, accountRef) VALUES (?, ?)");
            app2Statement.setInt(1, uDao.userId);
            app2Statement.setInt(2, answerI);
            app2Statement.executeUpdate();
            app2Statement.close();
            appStatement.close();
        } catch (SQLException e) {
            e.getMessage();
        }
        System.out.println("Your application has been accepted and is currently processing.\nOnce an admin approves it, you will be able to start banking.\nThank you and have a nice day.\n");
    }

    public void Create(){
        try {
            PreparedStatement crStatement = connection.prepareStatement("SELECT * FROM accounts WHERE approved = false");
            ResultSet crResult = crStatement.executeQuery();
            while (crResult.next()){
                System.out.println("Account Name: "+crResult.getString("accountName")+", Account Number: "+crResult.getInt("accountNumber")+", Status: "+crResult.getBoolean("approved"));
                crResult.next();
            }
            System.out.println("Please enter the account number of the account you wish to approve.\n");
            answerI = scanner.nextInt();
            PreparedStatement cr2Statement = connection.prepareStatement("UPDATE accounts SET approved = true WHERE accountNumber = ?");
            cr2Statement.setInt(1, answerI);
            System.out.println("Thank you for approving the account.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ApplyJoint(){
        System.out.println("Please enter a username, or 'null' to finish entering people.\n");
        answer = scanner.next();
        ArrayList<Integer> userIds = new ArrayList<Integer>();
        userIds.add(uDao.userId);
        while (!answer.equals(null)){
            lDao.CheckUserName();
            uDao.GetUserID();
            userIds.add(uDao.userId);
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
            for (int i = 0; i < userIds.size(); i++){
                PreparedStatement appJ2Statement = connection.prepareStatement("INSERT INTO usersAccounts(userRef, accountRef) VALUES (?, ?)");
                appJ2Statement.setInt(1, userIds.get(i));
                appJ2Statement.setInt(2, answerI);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Delete(){

    }

    public void ViewOwn(){
        try {
            PreparedStatement voStatement = connection.prepareStatement("SELECT * FROM usersAccounts WHERE userRef = ?");
            voStatement.setInt(1, uDao.userId);
            ResultSet voResult = voStatement.executeQuery();
            ArrayList<Integer> answers = new ArrayList<Integer>();
            while (voResult.next()){
                answers.add(voResult.getInt("accountRef"));
                System.out.println("Account: " + voResult.getInt("accountRef"));
                voResult.next();
            }
            PreparedStatement vo2Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
            vo2Statement.setInt(1, answerI);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ViewOther(){
        System.out.println("Please enter the user ID.\n");
        answerI = scanner.nextInt();
        Integer answerII = answerI;
        while (answerII.equals(null)){
            System.out.println("Please enter a valid argument and try again.");
            answerII = scanner.nextInt();
        }
        try {
            PreparedStatement voStatement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccounts ON accountNumber = accountRef WHERE userRef = ?");
            voStatement.setInt(1, answerI);
            ResultSet voResult = voStatement.executeQuery();
            while (voResult.next()){
                System.out.println("account: " + voResult.getString("accountName") + ", account number: " + voResult.getString("accountNumber") + ", balance: " + voResult.getDouble("accountBalance") + ", status:" + voResult.getBoolean("approved"));
                voResult.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            while (selResult.next()){
                System.out.println(selResult.getInt("accountNumber"));
                selResult.next();
            }
            answerI = scanner.nextInt();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}