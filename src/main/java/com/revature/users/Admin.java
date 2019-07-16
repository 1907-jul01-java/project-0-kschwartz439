package com.revature.users;

import java.util.Scanner;

import java.sql.*;

public class Admin{
    Scanner scanner;
    String answer;
    Connection connection;
    String firstName;
    String lastName;
    String access;
    String answer2;
    String answer3;
    String accId;
    String answer4;
    int accBalance;

    //Create Admin UI
    public Admin(){
        

    }

    public void menu(){
        System.out.println("Please choose what you want to do. \n 1. Edit employee accounts. \n 2. Edit customer accounts. \n 3. Edit bank accounts. \n 4. Logout.\n");
        answer = scanner.next();
        while (!answer.equals("1") || !answer.equals("2") || !answer.equals("3") || !answer.equals("4")){
            System.out.println("Please enter a valid argument and try again.\n");
            answer = scanner.next();
        }
        switch (answer){
            
            //Edit accounts with the 'employee' access modifier.
            case "1": try (PreparedStatement empStatement = connection.prepareStatement("SELECT * FROM users WHERE access = ?")) {
                empStatement.setString(1, "employee");
                ResultSet empResult = empStatement.executeQuery();;
                while (empResult.next()){
                    System.out.println(empResult.next() + "\n");
                }
                System.out.println("Please enter the id of the account you would like to edit or enter a new id to add a new account to the employee listing");
                answer3 = scanner.next();
                while (answer3.equals(null)){
                    System.out.println("Please enter a valid argument and try again.\n");
                    answer3 = scanner.next();
                }
                while (answer3!=null){
                    if (answer3.equals(empResult.getString("id"))){
                        System.out.println("Please enter the first name of account holder.\n");
                        answer2 = scanner.next();
                        while (answer2!=null){
                            firstName = answer2;
                            System.out.println("Please enter the last name of account holder.\n");
                            answer2 = scanner.next();
                            while (answer2!=null){
                                lastName = answer2;
                                System.out.println("Please enter the access level for this account or enter 'delete' to delete the account.");
                                answer2 = scanner.next();
                                while (!answer2.equals("employee") || !answer2.equals("customer") || !answer2.equals("break")){
                                    System.out.println("Please enter a valid option and try again.");
                                    answer2 = scanner.next();
                                }
                                switch (answer2){
                                    case "employee": try (PreparedStatement emp2Statment = connection.prepareStatement("UPDATE users VALUES (?, ?, , ?)")) {
                                        emp2Statment.setString(1, firstName);
                                        emp2Statment.setString(2, lastName);
                                        emp2Statment.setString(3, answer2);
                                        emp2Statment.executeUpdate();
                                        System.out.println("The account has been updated. Thank you.\n");
                                    } catch (SQLException e) {
                                        e.getMessage();
                                    }
                                        break;
                                    case "customer": try (PreparedStatement emp2Statement = connection.prepareStatement("UPDATE VALUES (?, ?, , ?) FROM users")) {
                                        emp2Statement.setString(1, firstName);
                                        emp2Statement.setString(2, lastName);
                                        emp2Statement.setString(3, answer2);
                                        emp2Statement.executeUpdate();
                                        System.out.println("The account has been updated. Thank you.\n");
                                    } catch (SQLException e) {
                                        e.getMessage();
                                    }
                                        break;
                                    case "delete":  try (PreparedStatement emp2Statement = connection.prepareStatement("DELETE * FROM users WHERE id = ?")) {
                                        emp2Statement.setString(1, answer);
                                        try (PreparedStatement emp3Statement = connection.prepareStatement("DELETE * FROM userLogins WHERE userId = ?")){
                                            emp3Statement.setString(1, answer);
                                            emp3Statement.executeUpdate();
                                        }
                                    } catch (SQLException e) {
                                        e.getMessage();
                                    }
                                        break;
                                }
                            }
                        }
                    }
                }
                    
                }
             catch (SQLException e) {
                e.getMessage();
            }
                break;

            //Edit accounts with the 'customer' access modifier.
            case "2": try (PreparedStatement empStatement = connection.prepareStatement("SELECT * FROM users WHERE access = ?")) {
                empStatement.setString(1, "customer");
                ResultSet empResult = empStatement.executeQuery();;
                while (empResult.next()){
                    System.out.println(empResult.next() + "\n");
                }
                System.out.println("Please enter the id of the account you would like to edit or enter a new id to add a new account to the employee listing");
                answer3 = scanner.next();
                while (answer3!=null){
                    if (answer.equals(empResult.getString("id"))){
                        System.out.println("Please enter the first name of account holder.\n");
                        answer2 = scanner.next();
                        while (answer2!=null){
                            firstName = answer2;
                            System.out.println("Please enter the last name of account holder.\n");
                            answer2 = scanner.next();
                            while (answer2!=null){
                                lastName = answer2;
                                System.out.println("Please enter the access level for this account or enter 'delete' to delete the account.");
                                answer2 = scanner.next();
                                while (!answer2.equals("employee") || !answer2.equals("customer") || !answer2.equals("break")){
                                    System.out.println("Please enter a valid option and try again.");
                                    answer2 = scanner.next();
                                }
                                switch (answer2){
                                    case "employee": try (PreparedStatement emp2Statment = connection.prepareStatement("UPDATE users VALUES (?, ?, , ?)")) {
                                        emp2Statment.setString(1, firstName);
                                        emp2Statment.setString(2, lastName);
                                        emp2Statment.setString(3, answer2);
                                        emp2Statment.executeUpdate();
                                        System.out.println("The account has been updated. Thank you.\n");
                                    } catch (SQLException e) {
                                        e.getMessage();
                                    }
                                        break;
                                    case "customer": try (PreparedStatement emp2Statement = connection.prepareStatement("UPDATE VALUES (?, ?, , ?) FROM users")) {
                                        emp2Statement.setString(1, firstName);
                                        emp2Statement.setString(2, lastName);
                                        emp2Statement.setString(3, answer2);
                                        emp2Statement.executeUpdate();
                                        System.out.println("The account has been updated. Thank you.\n");
                                    } catch (SQLException e) {
                                        e.getMessage();
                                    }
                                        break;
                                    case "delete":  try (PreparedStatement emp2Statement = connection.prepareStatement("DELETE * FROM users WHERE id = ?")) {
                                        emp2Statement.setString(1, answer);
                                        try (PreparedStatement emp3Statement = connection.prepareStatement("DELETE * FROM userLogins WHERE userId = ?")){
                                            emp3Statement.setString(1, answer);
                                            emp3Statement.executeUpdate();
                                        }
                                    } catch (SQLException e) {
                                        e.getMessage();
                                    }
                                        break;
                                }
                            }
                        }
                    }
                }
                    
                }
             catch (SQLException e) {
                e.getMessage();
            }
                break;

            //Edit bank accounts with a given user ID.
            case "3": System.out.println("Please enter the ID of the user whose accounts you want to edit.\n");
            answer2 = scanner.next();
            while(answer2.equals(null)){
                System.out.println("Please enter a valid ID and try again.\n");
                answer2 = scanner.next();
            }
            ResultSet accResult;
            try (PreparedStatement accStatement = connection.prepareStatement("SELECT * FROM usersAccounts INNER JOIN accounts WHERE id = ?")) {
                accStatement.setString(1, answer2);
                accResult = accStatement.executeQuery();
                while (!accResult.next()){
                    System.out.println("Please enter a valid ID and try again.\n");
                    answer2 = scanner.next();
                    try (PreparedStatement acc2Statement = connection.prepareStatement("SELECT * FROM usersAccounts INNER JOIN accounts WHERE id = ?")) {
                        acc2Statement.setString(1, answer2);
                        accResult = acc2Statement.executeQuery();
                    } catch (SQLException e) {
                        e.getMessage();
                    }
                }
                System.out.println("Please enter the account number of the account you wish to edit.\n");
                while(accResult.next()){
                    System.out.println(accResult.getString("accountNumber\n"));
                }
                answer3 = scanner.next();
                while (answer3.equals(null) || answer3.equals(accResult.getString("accountNumber"))){
                    System.out.println("Please enter a valid account number and try again.\n");
                    answer3 = scanner.next();
                }
                accId = answer3;
                System.out.println("Would you like to\n1. Edit the balance.\n2. Edit the account name.\n3. Change approval status.\n4. Delete the account.");
                answer3 = scanner.next();
                while (!answer3.equals("1") || !answer3.equals("2") || !answer3.equals("3") || !answer3.equals("4")){
                    System.out.println("Please enter a valid argument and try again.");
                    switch (answer3){
                        case "1": System.out.println("Please enter the change with '+' or '-'.\n");
                            answer4 = scanner.next();
                            while (answer4.equals(null) || !answer.startsWith("+") || !answer.startsWith("-")){
                                System.out.println("Please enter a valid change and try again.\n");
                                answer4 = scanner.next();
                            }
                            if (answer4.startsWith("+")){

                            }
                            if (answer4.startsWith("-")){
                                int answer5 = Integer.parseInt(answer4.substring(1, answer4.length()));
                                while (answer5 > accResult.getInt("accountBalance")){
                                    System.out.println("You cannot take out more money than is in the account. Please enter a valid argument and try again.\n");
                                    answer4 = scanner.next();
                                    answer5 = Integer.parseInt(answer4.substring(1, answer4.length()));
                                }
                                while (answer5 < accResult.getInt("accountBalance")){
                                    accBalance = (accResult.getInt("accountBalance") - answer5);
                                    try (PreparedStatement balStatement = connection.prepareStatement("UPDATE ? FROM accounts WHERE id = ?")) {
                                        balStatement.setInt(1, accBalance);
                                        balStatement.setString(2, accId);
                                        balStatement.executeUpdate();
                                    } catch (SQLException e) {
                                        e.getMessage();
                                    }
                                }
                            }
                            break;
                        case "2": System.out.println("Please enter the new account name.\n");
                            answer4 = scanner.next();
                            while (answer4.equals(null)){
                                System.out.println("Please enter a valid argument and try again.\n");
                                answer4 = scanner.next();
                            }
                            try (PreparedStatement accNameStatement = connection.prepareStatement("UPDATE accountName FROM accounts WHERE id = ?")) {
                                accNameStatement.setString(2, accId);
                                accNameStatement.executeUpdate(answer4);
                            } catch (SQLException e) {
                                e.getMessage();
                            }
                            break;
                        case "3": System.out.println("Please enter the new approval status.\n");
                            answer4 = scanner.next();
                            while (answer4.equals(null) || !answer4.equals("true") || !answer4.equals("false")){
                                System.out.println("Please enter a valid argument and try again.\n");
                                answer4 = scanner.next();
                            }
                            if (answer4.equals("true")){
                                try (PreparedStatement accAppStatement = connection.prepareStatement("UPDATE approved FROM accounts WHERE id = ?")) {
                                    accAppStatement.setString(1, accId);
                                    accAppStatement.executeUpdate(answer4);
                                } catch (SQLException e) {
                                    e.getMessage();
                                }
                                System.out.println("The account has been updated. Thank you.\n");
                            }
                            if (answer4.equals("false")){
                                try (PreparedStatement accAppStatement = connection.prepareStatement("UPDATE approved FROM accounts WHERE id = ?")) {
                                    accAppStatement.setString(1, accId);
                                    accAppStatement.executeUpdate(answer4);
                                } catch (SQLException e) {
                                    e.getMessage();
                                }
                                System.out.println("The account has been updated. Thank you.\n");
                            }
                            break;
                        case "4":
                            break;
                    }
                }
            } catch (SQLException e) {
                e.getMessage();
            }
                break;
            case "4": try {
                connection.close();
            } catch (SQLException e) {
                e.getMessage();
            }
                break;
        }
    }

}