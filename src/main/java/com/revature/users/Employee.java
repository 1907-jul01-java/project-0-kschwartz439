package com.revature.users;

import java.util.Scanner;
import java.sql.*;

public class Employee extends User{
    //Create Employee UI
    String answer;
    Scanner scanner;
    String answer2;
    Connection connection;
    String answer3;
    String answer4;
    String accId;
    int accBalance;

    public Employee(){
        
    }
    
    public void menu(){
        System.out.println("Please choose what you want to do. \n 1. Edit bank accounts. \n 2. Logout.\n");
        answer = scanner.next();
        while (!answer.equals("1") || !answer.equals("2")){
            System.out.println("Please enter a valid argument and try again.\n");
            answer = scanner.next();
        }
        switch (answer){
            //Edit bank accounts with a given user Id.
            case "1": System.out.println("Please enter the ID of the user whose accounts you want to edit.\n");
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
                System.out.println("Would you like to\n1. Edit the balance. \n2. Edit the account name. \n3. Go back.");
                answer3 = scanner.next();
                while (!answer3.equals("1") || !answer3.equals("2")){
                    System.out.println("Please enter a valid argument and try again.");
                    answer3 = scanner.next();
                }
                switch (answer3){
                    case "1": System.out.println("Please enter the change with '+' or '-'.\n");
                        answer4 = scanner.next();
                        while (answer4.equals(null) || !answer.startsWith("+") || !answer.startsWith("-")){
                            System.out.println("Please enter a valid change and try again.\n");
                            answer4 = scanner.next();
                        }
                        if (answer4.startsWith("+")){
                            int answer5 = Integer.parseInt(answer4.substring((answer4.indexOf("+") + 1), answer4.length()));
                            accBalance = (accResult.getInt("accountBalance") + answer5);
                            try (PreparedStatement balStatement = connection.prepareStatement("UPDATE ? FROM accounts WHERE id = ?")) {
                                balStatement.setInt(1, accBalance);
                                balStatement.setString(2, accId);
                                balStatement.executeUpdate();
                            } catch (SQLException e) {
                                e.getMessage();
                            }
                        }
                        if (answer4.startsWith("-")){
                            int answer5 = Integer.parseInt(answer4.substring((answer4.indexOf("-") + 1), answer4.length()));
                            while (answer5 > accResult.getInt("accountBalance")){
                                System.out.println("You cannot take out more money than is in the account. Please enter a valid argument and try again.\n");
                                answer4 = scanner.next();
                                answer5 = Integer.parseInt(answer4.substring((answer4.indexOf("-") + 1), answer4.length()));
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
                        case "3":
                            break;
                    }
            } catch (SQLException e) {
                e.getMessage();
            }
                break;

            //Logout.
            case "2": 
            try {
                connection.close();
            } catch (SQLException e) {
                e.getMessage();
            }
            Check check = new Check(scanner, connection);
            check.ask();
                break;
        }
    }
}