package com.revature.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import com.revature.connectionutils.*;

public class AccountDao{
    String answer;
    double answerD;
    int accountID;
    int answerI;
    double accBalance1;
    double accBalance2;
    BigDecimal accBalance;
    Scanner scanner = new Scanner(System.in);
    ConnectionUtils conUtil = new ConnectionUtils();
    Connection connection = conUtil.getConnection();
    UserDao uDao = new UserDao();
    LoginDao lDao = new LoginDao();

    public void Deposit(int userID){
        this.SelectAccount(userID);
        System.out.println("How much would you like to deposit?\n");
        answerD = scanner.nextDouble();
        try {
            PreparedStatement depStatement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccounts ON accountNumber = accountRef WHERE usersAccounts.userRef = ?");
            depStatement.setInt(1, userID);
            ResultSet depResult = depStatement.executeQuery();
            depResult.next();
            accBalance1 = depResult.getDouble("accountBalance");
            accBalance2 = accBalance1 + answerD;
            accBalance = (BigDecimal.valueOf(accBalance2));
            depStatement.close();
            try {
                PreparedStatement dep2Statement = connection.prepareStatement("UPDATE accounts SET accountBalance = ? WHERE accountNumber = ?");
                dep2Statement.setBigDecimal(1, accBalance);
                dep2Statement.setInt(2, accountID);
                dep2Statement.executeUpdate();
                dep2Statement.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Your account has been updated.\nYour new balance is " + accBalance2 + ".\n");
    }

    public void Withdraw(int userID){
        this.SelectAccount(userID);
        System.out.println("How much would you like to withdraw?\n");
        answerD = scanner.nextDouble();
        try (PreparedStatement witStatement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?")) {
            witStatement.setInt(1, accountID);
            ResultSet witResult = witStatement.executeQuery();
            witResult.next();
            accBalance = witResult.getBigDecimal("accountBalance");
            accBalance1 = accBalance.doubleValue();
            if (accBalance1 < answerD){
                System.out.println("You cannot withdraw more money than you have.\nPlease try again.\n");
                this.Withdraw(userID);
            }
            accBalance2 = accBalance1 - answerD;
            accBalance = BigDecimal.valueOf(accBalance2);
            System.out.println("Thank you. Your new account balance is: " + accBalance + ".\n");
            witStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement wit2Statement = connection.prepareStatement("UPDATE accounts SET accountBalance = ? WHERE accountNumber = ?")) {
            wit2Statement.setBigDecimal(1, accBalance);
            wit2Statement.setInt(2, accountID);
            wit2Statement.executeUpdate();
            wit2Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Apply(int userID){
        System.out.println("What would you like to call your account?\n");
        answer = scanner.next();
        try {
            PreparedStatement appStatement = connection.prepareStatement("INSERT INTO accounts (accountName, accountBalance, approved) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            appStatement.setString(1, answer);
            appStatement.setInt(2, 0);
            appStatement.setBoolean(3, false);
            appStatement.executeUpdate();
            ResultSet app2Result = appStatement.getGeneratedKeys();
            app2Result.next();
            answerI = app2Result.getInt(2);
            appStatement.close();
            PreparedStatement app2Statement = connection.prepareStatement("INSERT INTO usersAccounts (userRef, accountRef) VALUES (?, ?)");
            app2Statement.setInt(1, userID);
            app2Statement.setInt(2, answerI);
            app2Statement.executeUpdate();
            app2Statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Your application has been accepted and is currently processing.\nOnce an admin approves it, you will be able to start banking.\nThank you and have a nice day.\n");
    }

    public void Create(){
        try {
            PreparedStatement crStatement = connection.prepareStatement("SELECT * FROM accounts WHERE approved = ?");
            crStatement.setBoolean(1, false);
            ResultSet crResult = crStatement.executeQuery();
            while (crResult.next()){
                System.out.println("Account Name: "+crResult.getString("accountName")+", Account Number: "+crResult.getInt("accountNumber")+", Status: "+crResult.getBoolean("approved"));
                crResult.next();
            }
            System.out.println("Please enter the account number of the account you wish to approve.\n");
            answerI = scanner.nextInt();
            PreparedStatement cr2Statement = connection.prepareStatement("UPDATE accounts SET approved = true WHERE accountNumber = ?");
            cr2Statement.setInt(1, answerI);
            cr2Statement.executeUpdate();
            System.out.println("Thank you for approving the account.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ApplyJoint(int userID){
        System.out.println("Please enter a username, or 'null' to finish entering people.\n");
        answer = scanner.next();
        ArrayList<Integer> userIds = new ArrayList<Integer>();
        while (!answer.equals("null")){
            //make JointUsername method
            //get UserID from GetUserID
            //add userID to arraylist
        }
        System.out.println("What would you like to call the account?");
        answer = scanner.next();
        try {
            PreparedStatement appJStatement = connection.prepareStatement("INSERT INTO accounts(accountName, accountBalance, approved) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            appJStatement.setString(1, answer);
            appJStatement.setInt(2, 0);
            appJStatement.setBoolean(3, false);
            appJStatement.executeUpdate();
            ResultSet appJResult = appJStatement.getGeneratedKeys();
            appJResult.next();
            answerI = appJResult.getInt(3);
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

    public void ViewOwn(int userID){
        try {
            PreparedStatement voStatement = connection.prepareStatement("SELECT * FROM usersAccounts WHERE userRef = ?");
            voStatement.setInt(1, userID);
            ResultSet voResult = voStatement.executeQuery();
            voResult.next();
            int accountID = voResult.getInt("accountRef");
            PreparedStatement vo2Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ? AND approved = true");
            vo2Statement.setInt(1, accountID);
            ResultSet vo2Result = vo2Statement.executeQuery();
            while (vo2Result.next()){
                System.out.println("Account: " + vo2Result.getInt("accountNumber") + ", Account Name: " + vo2Result.getString("accountName") + ", Balance: " + vo2Result.getBigDecimal("accountBalance"));
                vo2Result.next();
            }

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

    public void SelectAccount(int userID){
        System.out.println("Which account will you be making changes to today?");
        try{
            PreparedStatement selStatement = connection.prepareStatement("SELECT * FROM usersAccounts WHERE userRef = ?");
            selStatement.setInt(1, userID);
            ResultSet selResult = selStatement.executeQuery();
            while (selResult.next()){
                System.out.println(selResult.getInt("accountRef"));
                selResult.next();
            }
            answerI = scanner.nextInt();
            accountID = answerI;
            this.accountID = accountID;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}