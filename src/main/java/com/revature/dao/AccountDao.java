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
    int userID;
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
        this.EditMenuEmployee(userID);
    }

    public void DepositCustomer(int userID){
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
        this.EditMenuCustomer(userID);
    }

    public void DepositAdmin(int userID){
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
        this.EditMenuAdmin(userID);
    }

    public void DepositCreator(int userID){
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
        this.EditMenuCreator(userID);
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
        this.EditMenuCustomer(userID);
    }

    public void WithdrawEmployee(int userID){
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
        this.EditMenuEmployee(userID);
    }

    public void WithdrawAdmin(int userID){
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
        this.EditMenuAdmin(userID);
    }

    public void WithdrawCreator(int userID){
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
        this.EditMenuCreator(userID);
    }

    public void Transer(int userID){
        System.out.println("Please choose the account you would like to transfer from.\n");
        try {
            PreparedStatement trStatement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccounts ON accountNumber = accountRef WHERE usersAccounts.userRef = ?");
            trStatement.setInt(1, userID);
            ResultSet trResult = trStatement.executeQuery();
            while (trResult.next()){
                trResult.next();
                System.out.println("Account: "+trResult.getString("accountName")+", Account Number: "+trResult.getInt("accountNumber")+", Balance: "+trResult.getBigDecimal("accountBalance"));
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }        
        answerI = scanner.nextInt();
        int answer2I = answerI;
        System.out.println("Please enter the ID of the user you wish to transfer money to.\n");
        answerI = scanner.nextInt();
        uDao.CheckUserID(answerI);
        answerI = uDao.answerI;
        System.out.println("How much would you like to transfer?\n");
        answerD = scanner.nextDouble();
        try {
            PreparedStatement tr2Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
            tr2Statement.setInt(1, answer2I);
            ResultSet tr2Result = tr2Statement.executeQuery();
            tr2Result.next();
            if (answerD>tr2Result.getBigDecimal("accountBalance").doubleValue()){
                System.out.println("You cannot transfer more funds than you have. Please enter a valid argument and try again.\n");
                answerD = scanner.nextDouble();
            }
            else{
                accBalance = BigDecimal.valueOf(tr2Result.getBigDecimal("accountBalance").doubleValue() - answerD);
                try {
                    PreparedStatement tr3Statement = connection.prepareStatement("UPDATE accounts (accountBalance) VALUES (?) WHERE accountNumber = ?");
                    tr3Statement.setBigDecimal(1, accBalance);
                    tr3Statement.setInt(2, answer2I);
                    tr3Statement.executeUpdate();
                    System.out.println("Your new balance is: "+accBalance+".\n");
                    PreparedStatement tr4Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
                    tr4Statement.setInt(1, answerI);
                    ResultSet tr4Result = tr4Statement.executeQuery();
                    tr4Result.next();
                    accBalance = BigDecimal.valueOf(tr4Result.getBigDecimal("accountBalance").doubleValue() + answerD);
                    PreparedStatement tr5Statement = connection.prepareStatement("UPDATE accounts (accountBalance) VALUES (?) WHERE accountNumber = ?");
                    tr5Statement.setBigDecimal(1, accBalance);
                    tr5Statement.setInt(2, answerI);
                    tr5Statement.executeUpdate();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Thank you for banking with us.\n");
        this.EditMenuCustomer(userID);
    }

    public void TranserEmployee(int userID){
        System.out.println("Please choose the account you would like to transfer from.\n");
        try {
            PreparedStatement trStatement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccounts ON accountNumber = accountRef WHERE usersAccounts.userRef = ?");
            trStatement.setInt(1, userID);
            ResultSet trResult = trStatement.executeQuery();
            while (trResult.next()){
                trResult.next();
                System.out.println("Account: "+trResult.getString("accountName")+", Account Number: "+trResult.getInt("accountNumber")+", Balance: "+trResult.getBigDecimal("accountBalance"));
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }        
        answerI = scanner.nextInt();
        int answer2I = answerI;
        System.out.println("Please enter the ID of the user you wish to transfer money to.\n");
        answerI = scanner.nextInt();
        uDao.CheckUserID(answerI);
        answerI = uDao.answerI;
        System.out.println("How much would you like to transfer?\n");
        answerD = scanner.nextDouble();
        try {
            PreparedStatement tr2Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
            tr2Statement.setInt(1, answer2I);
            ResultSet tr2Result = tr2Statement.executeQuery();
            tr2Result.next();
            if (answerD>tr2Result.getBigDecimal("accountBalance").doubleValue()){
                System.out.println("You cannot transfer more funds than you have. Please enter a valid argument and try again.\n");
                answerD = scanner.nextDouble();
            }
            else{
                accBalance = BigDecimal.valueOf(tr2Result.getBigDecimal("accountBalance").doubleValue() - answerD);
                try {
                    PreparedStatement tr3Statement = connection.prepareStatement("UPDATE accounts (accountBalance) VALUES (?) WHERE accountNumber = ?");
                    tr3Statement.setBigDecimal(1, accBalance);
                    tr3Statement.setInt(2, answer2I);
                    tr3Statement.executeUpdate();
                    System.out.println("Your new balance is: "+accBalance+".\n");
                    PreparedStatement tr4Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
                    tr4Statement.setInt(1, answerI);
                    ResultSet tr4Result = tr4Statement.executeQuery();
                    tr4Result.next();
                    accBalance = BigDecimal.valueOf(tr4Result.getBigDecimal("accountBalance").doubleValue() + answerD);
                    PreparedStatement tr5Statement = connection.prepareStatement("UPDATE accounts (accountBalance) VALUES (?) WHERE accountNumber = ?");
                    tr5Statement.setBigDecimal(1, accBalance);
                    tr5Statement.setInt(2, answerI);
                    tr5Statement.executeUpdate();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Thank you for banking with us.\n");
        this.EditMenuEmployee(userID);
    }

    public void TranserAdmin(int userID){
        System.out.println("Please choose the account you would like to transfer from.\n");
        try {
            PreparedStatement trStatement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccounts ON accountNumber = accountRef WHERE usersAccounts.userRef = ?");
            trStatement.setInt(1, userID);
            ResultSet trResult = trStatement.executeQuery();
            while (trResult.next()){
                trResult.next();
                System.out.println("Account: "+trResult.getString("accountName")+", Account Number: "+trResult.getInt("accountNumber")+", Balance: "+trResult.getBigDecimal("accountBalance"));
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }        
        answerI = scanner.nextInt();
        int answer2I = answerI;
        System.out.println("Please enter the ID of the user you wish to transfer money to.\n");
        answerI = scanner.nextInt();
        uDao.CheckUserID(answerI);
        answerI = uDao.answerI;
        System.out.println("How much would you like to transfer?\n");
        answerD = scanner.nextDouble();
        try {
            PreparedStatement tr2Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
            tr2Statement.setInt(1, answer2I);
            ResultSet tr2Result = tr2Statement.executeQuery();
            tr2Result.next();
            if (answerD>tr2Result.getBigDecimal("accountBalance").doubleValue()){
                System.out.println("You cannot transfer more funds than you have. Please enter a valid argument and try again.\n");
                answerD = scanner.nextDouble();
            }
            else{
                accBalance = BigDecimal.valueOf(tr2Result.getBigDecimal("accountBalance").doubleValue() - answerD);
                try {
                    PreparedStatement tr3Statement = connection.prepareStatement("UPDATE accounts (accountBalance) VALUES (?) WHERE accountNumber = ?");
                    tr3Statement.setBigDecimal(1, accBalance);
                    tr3Statement.setInt(2, answer2I);
                    tr3Statement.executeUpdate();
                    System.out.println("Your new balance is: "+accBalance+".\n");
                    PreparedStatement tr4Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
                    tr4Statement.setInt(1, answerI);
                    ResultSet tr4Result = tr4Statement.executeQuery();
                    tr4Result.next();
                    accBalance = BigDecimal.valueOf(tr4Result.getBigDecimal("accountBalance").doubleValue() + answerD);
                    PreparedStatement tr5Statement = connection.prepareStatement("UPDATE accounts (accountBalance) VALUES (?) WHERE accountNumber = ?");
                    tr5Statement.setBigDecimal(1, accBalance);
                    tr5Statement.setInt(2, answerI);
                    tr5Statement.executeUpdate();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Thank you for banking with us.\n");
        this.EditMenuAdmin(userID);
    }

    public void TranserCreator(int userID){
        System.out.println("Please choose the account you would like to transfer from.\n");
        try {
            PreparedStatement trStatement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccounts ON accountNumber = accountRef WHERE usersAccounts.userRef = ?");
            trStatement.setInt(1, userID);
            ResultSet trResult = trStatement.executeQuery();
            while (trResult.next()){
                trResult.next();
                System.out.println("Account: "+trResult.getString("accountName")+", Account Number: "+trResult.getInt("accountNumber")+", Balance: "+trResult.getBigDecimal("accountBalance"));
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }        
        answerI = scanner.nextInt();
        int answer2I = answerI;
        System.out.println("Please enter the ID of the user you wish to transfer money to.\n");
        answerI = scanner.nextInt();
        uDao.CheckUserID(answerI);
        answerI = uDao.answerI;
        System.out.println("How much would you like to transfer?\n");
        answerD = scanner.nextDouble();
        try {
            PreparedStatement tr2Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
            tr2Statement.setInt(1, answer2I);
            ResultSet tr2Result = tr2Statement.executeQuery();
            tr2Result.next();
            if (answerD>tr2Result.getBigDecimal("accountBalance").doubleValue()){
                System.out.println("You cannot transfer more funds than you have. Please enter a valid argument and try again.\n");
                answerD = scanner.nextDouble();
            }
            else{
                accBalance = BigDecimal.valueOf(tr2Result.getBigDecimal("accountBalance").doubleValue() - answerD);
                try {
                    PreparedStatement tr3Statement = connection.prepareStatement("UPDATE accounts (accountBalance) VALUES (?) WHERE accountNumber = ?");
                    tr3Statement.setBigDecimal(1, accBalance);
                    tr3Statement.setInt(2, answer2I);
                    tr3Statement.executeUpdate();
                    System.out.println("Your new balance is: "+accBalance+".\n");
                    PreparedStatement tr4Statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
                    tr4Statement.setInt(1, answerI);
                    ResultSet tr4Result = tr4Statement.executeQuery();
                    tr4Result.next();
                    accBalance = BigDecimal.valueOf(tr4Result.getBigDecimal("accountBalance").doubleValue() + answerD);
                    PreparedStatement tr5Statement = connection.prepareStatement("UPDATE accounts (accountBalance) VALUES (?) WHERE accountNumber = ?");
                    tr5Statement.setBigDecimal(1, accBalance);
                    tr5Statement.setInt(2, answerI);
                    tr5Statement.executeUpdate();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Thank you for banking with us.\n");
        this.EditMenuCreator(userID);
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
        this.EditMenuCustomer(userID);
    }

    public void ApplyCreator(int userID){
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
        this.EditMenuCreator(userID);
    }

    public void ApplyAdmin(int userID){
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
        this.EditMenuAdmin(userID);
    }

    public void ApplyEmployee(int userID){
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
        this.EditMenuEmployee(userID);
    }

    public void Create(int userID){
        try {
            PreparedStatement crStatement = connection.prepareStatement("SELECT * FROM accounts WHERE approved = ?");
            crStatement.setBoolean(1, false);
            ResultSet crResult = crStatement.executeQuery();
            while (crResult.next()){
                crResult.next();
                System.out.println("Account Name: "+crResult.getString("accountName")+", Account Number: "+crResult.getInt("accountNumber")+", Status: "+crResult.getBoolean("approved"));
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
        finally{
            CreatorDao crDao = new CreatorDao();
            crDao.menu(userID);
        }
    }

    public void CreateAdmin(int userID){
        try {
            PreparedStatement crStatement = connection.prepareStatement("SELECT * FROM accounts WHERE approved = ?");
            crStatement.setBoolean(1, false);
            ResultSet crResult = crStatement.executeQuery();
            while (crResult.next()){
                crResult.next();
                System.out.println("Account Name: "+crResult.getString("accountName")+", Account Number: "+crResult.getInt("accountNumber")+", Status: "+crResult.getBoolean("approved"));
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
        finally{
            AdminDao adDao = new AdminDao();
            adDao.menu(userID);
        }
    }

    public void ApplyJoint(int userID){
        System.out.println("Please enter a username, or 'null' to finish entering people.\n");
        answer = scanner.next();
        ArrayList<String> usernames = new ArrayList<String>();
        while (!answer.equals("null")){
            usernames.add(answer);
            answer = scanner.next();
        }
        ArrayList<Integer> userIDs = new ArrayList<Integer>();
        for (int i = 0; i < usernames.size(); i++)
        {
        uDao.GetUserJoint(usernames.get(i));
        if (uDao.b=true){
            uDao.GetUserID(usernames.get(i));
            userIDs.add(uDao.userID);
        }
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
            for (int i = 0; i < userIDs.size(); i++){
                PreparedStatement appJ2Statement = connection.prepareStatement("INSERT INTO usersAccounts(userRef, accountRef) VALUES (?, ?)");
                appJ2Statement.setInt(1, userIDs.get(i));
                appJ2Statement.setInt(2, answerI);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } this.EditMenuCustomer(userID);
    }

    public void ApplyJointEmployee(int userID){
        System.out.println("Please enter a username, or 'null' to finish entering people.\n");
        answer = scanner.next();
        ArrayList<String> usernames = new ArrayList<String>();
        while (!answer.equals("null")){
            usernames.add(answer);
            answer = scanner.next();
        }
        ArrayList<Integer> userIDs = new ArrayList<Integer>();
        for (int i = 0; i < usernames.size(); i++)
        {
        uDao.GetUserJoint(usernames.get(i));
        if (uDao.b=true){
            uDao.GetUserID(usernames.get(i));
            userIDs.add(uDao.userID);
        }
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
            for (int i = 0; i < userIDs.size(); i++){
                PreparedStatement appJ2Statement = connection.prepareStatement("INSERT INTO usersAccounts(userRef, accountRef) VALUES (?, ?)");
                appJ2Statement.setInt(1, userIDs.get(i));
                appJ2Statement.setInt(2, answerI);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } this.EditMenuEmployee(userID);
    }

    public void ApplyJointAdmin(int userID){
        System.out.println("Please enter a username, or 'null' to finish entering people.\n");
        answer = scanner.next();
        ArrayList<String> usernames = new ArrayList<String>();
        while (!answer.equals("null")){
            usernames.add(answer);
            answer = scanner.next();
        }
        ArrayList<Integer> userIDs = new ArrayList<Integer>();
        for (int i = 0; i < usernames.size(); i++)
        {
        uDao.GetUserJoint(usernames.get(i));
        if (uDao.b=true){
            uDao.GetUserID(usernames.get(i));
            userIDs.add(uDao.userID);
        }
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
            for (int i = 0; i < userIDs.size(); i++){
                PreparedStatement appJ2Statement = connection.prepareStatement("INSERT INTO usersAccounts(userRef, accountRef) VALUES (?, ?)");
                appJ2Statement.setInt(1, userIDs.get(i));
                appJ2Statement.setInt(2, answerI);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.EditMenuAdmin(userID);
    }

    public void ApplyJointCreator(int userID){
        System.out.println("Please enter a username, or 'null' to finish entering people.\n");
        answer = scanner.next();
        ArrayList<String> usernames = new ArrayList<String>();
        while (!answer.equals("null")){
            usernames.add(answer);
            answer = scanner.next();
        }
        ArrayList<Integer> userIDs = new ArrayList<Integer>();
        for (int i = 0; i < usernames.size(); i++)
        {
        uDao.GetUserJoint(usernames.get(i));
        if (uDao.b=true){
            uDao.GetUserID(usernames.get(i));
            userIDs.add(uDao.userID);
        }
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
            for (int i = 0; i < userIDs.size(); i++){
                PreparedStatement appJ2Statement = connection.prepareStatement("INSERT INTO usersAccounts(userRef, accountRef) VALUES (?, ?)");
                appJ2Statement.setInt(1, userIDs.get(i));
                appJ2Statement.setInt(2, answerI);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }this.EditMenuCreator(userID);
    }

    public void AddJoint(int userID){
        try {
            PreparedStatement ajStatement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet ajResult = ajStatement.executeQuery();
            System.out.println("Which account would you like to add someone to?\n");
            while (ajResult.next()){
                ajResult.next();
                System.out.println("Account: "+ajResult.getString("accountName")+", Account Number: "+ajResult.getInt("accountNumber"));
            }
            answerI = scanner.nextInt();
            System.out.println("\nWho is this account being linked to?");
            PreparedStatement aj2Statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet aj2Result = aj2Statement.executeQuery();
            while (aj2Result.next()){
                aj2Result.next();
                System.out.println("Name: "+aj2Result.getString("firstName")+" "+aj2Result.getString("lastName")+", User ID: "+aj2Result.getInt("id")+".");
            }
            int answer2I = scanner.nextInt();
            PreparedStatement aj3Statement = connection.prepareStatement("INSERT INTO usersAccounts (userRef, accountRef) VALUES (?, ?)");
            aj3Statement.setInt(1, answer2I);
            aj3Statement.setInt(2, answerI);
            aj3Statement.executeUpdate();
            System.out.println("\nThank you. The user has been added to the acocunt.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            AdminDao aDao = new AdminDao();
            aDao.menu(userID);
        }
    }

    public void AddJointCreator(int userID){
        try {
            PreparedStatement ajStatement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet ajResult = ajStatement.executeQuery();
            System.out.println("Which account would you like to add someone to?\n");
            while (ajResult.next()){
                ajResult.next();
                System.out.println("Account: "+ajResult.getString("accountName")+", Account Number: "+ajResult.getInt("accountNumber"));
            }
            answerI = scanner.nextInt();
            System.out.println("\nWho is this account being linked to?");
            PreparedStatement aj2Statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet aj2Result = aj2Statement.executeQuery();
            while (aj2Result.next()){
                aj2Result.next();
                System.out.println("Name: "+aj2Result.getString("firstName")+" "+aj2Result.getString("lastName")+", User ID: "+aj2Result.getInt("id")+".");
            }
            int answer2I = scanner.nextInt();
            PreparedStatement aj3Statement = connection.prepareStatement("INSERT INTO usersAccounts (userRef, accountRef) VALUES (?, ?)");
            aj3Statement.setInt(1, answer2I);
            aj3Statement.setInt(2, answerI);
            aj3Statement.executeUpdate();
            System.out.println("\nThank you. The user has been added to the acocunt.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            CreatorDao crDao = new CreatorDao();
            crDao.menu(userID);
        }
    } 

    public void DeleteAdmin(int userID){
        System.out.println("Please enter the account number of the account you wish delete.\n");
        try {
            PreparedStatement delStatement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet delResult = delStatement.executeQuery();
            delResult.next();
            System.out.println("Account: "+delResult.getString("accountName")+", Account Number: "+delResult.getInt("accountNumber")+", Account Balance: "+delResult.getBigDecimal("accountBalance"));
            while (delResult.next()){
                delResult.next();
                System.out.println("Account: "+delResult.getString("accountName")+", Account Number: "+delResult.getInt("accountNumber")+", Account Balance: "+delResult.getBigDecimal("accountBalance"));
            }
            delStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        answerI = scanner.nextInt();
        try {
            PreparedStatement del2Statement = connection.prepareStatement("DELETE * FROM accounts WHERE accountNumber = ?");
            del2Statement.setInt(1, answerI);
            del2Statement.executeUpdate();
            System.out.println("The account has been deleted.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            AdminDao adDao = new AdminDao();
            adDao.menu(userID);
        }
    }

    public void DeleteCreator(int userID){
        System.out.println("Please enter the account number of the account you wish delete.\n");
        try {
            PreparedStatement delStatement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet delResult = delStatement.executeQuery();
            delResult.next();
            System.out.println("Account: "+delResult.getString("accountName")+", Account Number: "+delResult.getInt("accountNumber")+", Account Balance: "+delResult.getBigDecimal("accountBalance"));
            while (delResult.next()){
                delResult.next();
                System.out.println("Account: "+delResult.getString("accountName")+", Account Number: "+delResult.getInt("accountNumber")+", Account Balance: "+delResult.getBigDecimal("accountBalance"));
            }
            delStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        answerI = scanner.nextInt();
        try {
            PreparedStatement del2Statement = connection.prepareStatement("DELETE * FROM accounts WHERE accountNumber = ?");
            del2Statement.setInt(1, answerI);
            del2Statement.executeUpdate();
            System.out.println("The account has been deleted.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            CreatorDao crDao = new CreatorDao();
            crDao.menu(userID);
        }
    }

    public void Delete(int userID){
        System.out.println("Please enter the account number of the account you wish delete.\n");
        try {
            PreparedStatement delStatement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet delResult = delStatement.executeQuery();
            delResult.next();
            System.out.println("Account: "+delResult.getString("accountName")+", Account Number: "+delResult.getInt("accountNumber")+", Account Balance: "+delResult.getBigDecimal("accountBalance"));
            while (delResult.next()){
                delResult.next();
                System.out.println("Account: "+delResult.getString("accountName")+", Account Number: "+delResult.getInt("accountNumber")+", Account Balance: "+delResult.getBigDecimal("accountBalance"));
            }
            delStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        answerI = scanner.nextInt();
        try {
            PreparedStatement del2Statement = connection.prepareStatement("DELETE * FROM accounts WHERE accountNumber = ?");
            del2Statement.setInt(1, answerI);
            del2Statement.executeUpdate();
            System.out.println("The account has been deleted.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            CustomerDao cDao = new CustomerDao();
            cDao.menu(userID);
        }
    }

    public void DeleteEmployee(int userID){
        System.out.println("Please enter the account number of the account you wish delete.\n");
        try {
            PreparedStatement delStatement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet delResult = delStatement.executeQuery();
            delResult.next();
            System.out.println("Account: "+delResult.getString("accountName")+", Account Number: "+delResult.getInt("accountNumber")+", Account Balance: "+delResult.getBigDecimal("accountBalance"));
            while (delResult.next()){
                delResult.next();
                System.out.println("Account: "+delResult.getString("accountName")+", Account Number: "+delResult.getInt("accountNumber")+", Account Balance: "+delResult.getBigDecimal("accountBalance"));
            }
            delStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        answerI = scanner.nextInt();
        try {
            PreparedStatement del2Statement = connection.prepareStatement("DELETE * FROM accounts WHERE accountNumber = ?");
            del2Statement.setInt(1, answerI);
            del2Statement.executeUpdate();
            System.out.println("The account has been deleted.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            EmployeeDao emDao = new EmployeeDao();
            emDao.menu(userID);
        }
    }

    public void Remove(int userID){
        System.out.println("Please choose an account to remove someone from.\n");
        try {
            PreparedStatement reStatement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccounts ON accountNumber = accountRef GROUP BY usersAccounts.accountsRef HAVING COUNT(*) > 1");
            ResultSet reResult = reStatement.executeQuery();
            while (reResult.next()){
                reResult.next();
                System.out.println("Account: "+reResult.getString("accountName")+", Account Number: "+reResult.getInt("accountNumber")+".");
            }
            answerI = scanner.nextInt();
            PreparedStatement re2Statement = connection.prepareStatement("SELECT * FROM users INNER JOIN usersAccounts ON id = userRef WHERE accountRef = ?");
            re2Statement.setInt(1, answerI);
            ResultSet re2Result = re2Statement.executeQuery();
            System.out.println("Choose who you wish to remove from this account.\n");
            while (re2Result.next()){
                re2Result.next();
                System.out.println("Name: "+re2Result.getString("firstName")+" "+re2Result.getString("lastName")+", User ID: "+re2Result.getInt("id")+".");
            }            
            int answer2I = scanner.nextInt();
            PreparedStatement re3Statement = connection.prepareStatement("DELETE * FROM usersAccounts WHERE userRef = ? AND accountRef = ?");
            re3Statement.setInt(1, answer2I);
            re3Statement.setInt(2, answerI);
            re3Statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            AdminDao adDao = new AdminDao();
            adDao.menu(userID);
        }
    }

    public void RemoveCreator(int userID){
        System.out.println("Please choose an account to remove someone from.\n");
        try {
            PreparedStatement reStatement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccounts ON accountNumber = accountRef GROUP BY usersAccounts.accountsRef HAVING COUNT(*) > 1");
            ResultSet reResult = reStatement.executeQuery();
            while (reResult.next()){
                reResult.next();
                System.out.println("Account: "+reResult.getString("accountName")+", Account Number: "+reResult.getInt("accountNumber")+".");
            }
            answerI = scanner.nextInt();
            PreparedStatement re2Statement = connection.prepareStatement("SELECT * FROM users INNER JOIN usersAccounts ON id = userRef WHERE accountRef = ?");
            re2Statement.setInt(1, answerI);
            ResultSet re2Result = re2Statement.executeQuery();
            System.out.println("Choose who you wish to remove from this account.\n");
            while (re2Result.next()){
                re2Result.next();
                System.out.println("Name: "+re2Result.getString("firstName")+" "+re2Result.getString("lastName")+", User ID: "+re2Result.getInt("id")+".");
            }            
            int answer2I = scanner.nextInt();
            PreparedStatement re3Statement = connection.prepareStatement("DELETE * FROM usersAccounts WHERE userRef = ? AND accountRef = ?");
            re3Statement.setInt(1, answer2I);
            re3Statement.setInt(2, answerI);
            re3Statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            CreatorDao crDao = new CreatorDao();
            crDao.menu(userID);
        }
    }

    public void ViewOwn(int userID){
        try {
            PreparedStatement vo2Statement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccounts ON accountNumber = accountRef WHERE usersAccounts.userRef = ? AND accounts.approved = true");
            vo2Statement.setInt(1, userID);
            ResultSet vo2Result = vo2Statement.executeQuery();
            while (vo2Result.next()){
                System.out.println("Account: " + vo2Result.getInt("accountNumber") + ", Account Name: " + vo2Result.getString("accountName") + ", Balance: " + vo2Result.getBigDecimal("accountBalance"));
                vo2Result.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            CustomerDao cDao = new CustomerDao();
            cDao.menu(userID);
        }
    }

    public void ViewOwnEmployee(int userID){
        try {
            PreparedStatement vo2Statement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccount ON accountNumber = accountRef WHERE usersAccounts.userRef = ? AND accounts.approved = true");
            vo2Statement.setInt(1, userID);
            ResultSet vo2Result = vo2Statement.executeQuery();
            while (vo2Result.next()){
                System.out.println("Account: " + vo2Result.getInt("accountNumber") + ", Account Name: " + vo2Result.getString("accountName") + ", Balance: " + vo2Result.getBigDecimal("accountBalance"));
                vo2Result.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            EmployeeDao emDao = new EmployeeDao();
            emDao.menu(userID);
        }
    }

    public void ViewOwnAdmin(int userID){
        try {
            PreparedStatement vo2Statement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccounts ON accountNumber = accountRef WHERE usersAccounts.userRef = ? AND accounts.approved = true");
            vo2Statement.setInt(1, userID);
            ResultSet vo2Result = vo2Statement.executeQuery();
            while (vo2Result.next()){
                System.out.println("Account: " + vo2Result.getInt("accountNumber") + ", Account Name: " + vo2Result.getString("accountName") + ", Balance: " + vo2Result.getBigDecimal("accountBalance"));
                vo2Result.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            AdminDao adDao = new AdminDao();
            adDao.menu(userID);
        }
    }

    public void ViewOwnCreator(int userID){
        try {
            PreparedStatement vo2Statement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN usersAccounts ON accountNumber = accountRef WHERE usersAccounts.userRef = ? AND accounts.approved = true");
            vo2Statement.setInt(1, userID);
            ResultSet vo2Result = vo2Statement.executeQuery();
            while (vo2Result.next()){
                System.out.println("Account: " + vo2Result.getInt("accountNumber") + ", Account Name: " + vo2Result.getString("accountName") + ", Balance: " + vo2Result.getBigDecimal("accountBalance"));
                vo2Result.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            CreatorDao crDao = new CreatorDao();
            crDao.menu(userID);
        }
    }

    public void ViewOther(int userID){
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
        finally{
            EmployeeDao emDao = new EmployeeDao();
            emDao.menu(userID);
        }
    }

    public void ViewOtherCreator(int userID){
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
        finally{
            CreatorDao crDao = new CreatorDao();
            crDao.menu(userID);
        }
    }

    public void ViewOtherAdmin(int userID){
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
        finally{
            AdminDao adDAo = new AdminDao();
            adDAo.menu(userID);
        }
    }

    public void ViewAll(int userID){
        try {
            PreparedStatement vaStatement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet vaResult = vaStatement.executeQuery();
            while (vaResult.next()){
                System.out.println(vaResult + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            AdminDao adDAo = new AdminDao();
            adDAo.menu(userID);
        }
    }

    public void ViewAllCreator(int userID){
        try {
            PreparedStatement vaStatement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet vaResult = vaStatement.executeQuery();
            while (vaResult.next()){
                System.out.println(vaResult + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            CreatorDao crDao = new CreatorDao();
            crDao.menu(userID);
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
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void EditMenuCustomer(int userID){
        CustomerDao cDao = new CustomerDao();
        System.out.println("Would you like to\n1. Make a deposit.\n2. Withdraw money.\n3. Transfer funds.\n4. Close the account.\n5. Go back.");
        boolean c = true;
        String answer2;
        if (c = true){
                answer2 = scanner.next();
                switch (answer2){
                    case "1": this.DepositCustomer(userID);
                    break;
                    case "2": this.Withdraw(userID);
                    break;
                    case "3": this.Transer(userID);
                    break;
                    case "4": this.Delete(userID);
                    break;
                    case "5": cDao.menu(userID);
                    c=false;
                    break;
                    default:
                    break;
                }
            }
    }

    public void EditMenuCreator(int userID){
        CreatorDao crDao = new CreatorDao();
        System.out.println("Would you like to\n1. Make a deposit.\n2. Withdraw money.\n3. Transfer funds.\n4. Close the account.\n5. Go back.");
        boolean c = true;
        String answer2;
        if (c = true){
                answer2 = scanner.next();
                switch (answer2){
                    case "1": this.DepositCreator(userID);
                    break;
                    case "2": this.WithdrawCreator(userID);
                    break;
                    case "3": this.TranserCreator(userID);
                    break;
                    case "4": this.DeleteCreator(userID);
                    break;
                    case "5": crDao.menu(userID);
                    c=false;
                    break;
                    default:
                    break;
                }
            }
    }

    public void EditMenuAdmin(int userID){
        AdminDao adDao = new AdminDao();
        System.out.println("Would you like to\n1. Make a deposit.\n2. Withdraw money.\n3. Transfer funds.\n4. Close the account.\n5. Go back.");
        boolean c = true;
        String answer2;
        if (c = true){
                answer2 = scanner.next();
                switch (answer2){
                    case "1": this.DepositAdmin(userID);
                    break;
                    case "2": this.WithdrawAdmin(userID);
                    break;
                    case "3": this.TranserAdmin(userID);
                    break;
                    case "4": this.DeleteAdmin(userID);
                    break;
                    case "5": adDao.menu(userID);
                    c=false;
                    break;
                    default:
                    break;
                }
            }
    }

    public void EditMenuEmployee(int userID){
        EmployeeDao emDao = new EmployeeDao();
        System.out.println("Would you like to\n1. Make a deposit.\n2. Withdraw money.\n3. Transfer funds.\n4. Close the account.\n5. Go back.");
        boolean c = true;
        String answer2;
        if (c = true){
                answer2 = scanner.next();
                switch (answer2){
                    case "1": this.Deposit(userID);
                    break;
                    case "2": this.WithdrawEmployee(userID);
                    break;
                    case "3": this.TranserEmployee(userID);
                    break;
                    case "4": this.DeleteEmployee(userID);
                    break;
                    case "5": emDao.menu(userID);
                    c=false;
                    break;
                    default:
                    break;
                }
            }
    }

    public void JointMenu(int userID){
        CustomerDao cDao = new CustomerDao();
        boolean c = true;
        String answer2;
        if (c=true){
            System.out.println("Will this account be\n1. A personal account.\n2. A joint account.\n3. Go back.");
                answer2 = scanner.next();
                switch (answer2){
                    case "1": this.Apply(userID);
                    break;
                    case "2": this.ApplyJoint(userID);
                    break;
                    case "3": cDao.menu(userID);
                    c=false;
                    break;
                    default:
                    break;
                }
        }
    }

    public void JointMenuCreator(int userID){
        CreatorDao crDao = new CreatorDao();
        boolean c = true;
        String answer2;
        if (c=true){
            System.out.println("Will this account be\n1. A personal account.\n2. A joint account.\n3. Go back.");
                answer2 = scanner.next();
                switch (answer2){
                    case "1": this.ApplyCreator(userID);
                    break;
                    case "2": this.ApplyJointCreator(userID);
                    break;
                    case "3": crDao.menu(userID);
                    c=false;
                    break;
                    default:
                    break;
                }
        }
    }

    public void JointMenuAdmin(int userID){
        AdminDao adDao = new AdminDao();
        boolean c = true;
        String answer2;
        if (c=true){
            System.out.println("Will this account be\n1. A personal account.\n2. A joint account.\n3. Go back.");
                answer2 = scanner.next();
                switch (answer2){
                    case "1": this.ApplyAdmin(userID);
                    break;
                    case "2": this.ApplyJointAdmin(userID);
                    break;
                    case "3": adDao.menu(userID);
                    c=false;
                    break;
                    default:
                    break;
                }
        }
    }

    public void JointMenuEmployee(int userID){
        EmployeeDao emDao = new EmployeeDao();
        boolean c = true;
        String answer2;
        if (c=true){
            System.out.println("Will this account be\n1. A personal account.\n2. A joint account.\n3. Go back.");
                answer2 = scanner.next();
                switch (answer2){
                    case "1": this.ApplyEmployee(userID);
                    break;
                    case "2": this.ApplyJointEmployee(userID);
                    break;
                    case "3": emDao.menu(userID);
                    c=false;
                    break;
                    default:
                    break;
                }
        }
    }
}