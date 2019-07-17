package com.revature.users;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Customer extends User{
    //Create Customer UI
    String answer;
    Scanner scanner;
    Connection connection;
    int userId;
    String answer2;
    String answer3;
    String answer4;
    int answerInt;

    public Customer(int userId){
        this.userId = userId;
    }

    public void menu(){
        System.out.println("Would you like to\n1. Check your bank accounts.\n2. Edit your current bank accounts.\n3. Apply for a new bank account.\n4. Logout.\n");
        answer = scanner.next();
        while (!answer.equals("1") || !answer.equals("2") || !answer.equals("3") || !answer.equals("4")){
            System.out.println("Please enter a valid argument and try again.\n");
            answer = scanner.next();
        }
        switch (answer){
            //Check bank accounts.
            case "1": try (PreparedStatement cbStatement = connection.prepareStatement("SELECT * FROM accounts WHERE id = ?")) {
                cbStatement.setInt(1, userId);
                ResultSet cbResult = cbStatement.executeQuery();
                while (cbResult.next()){
                    System.out.println(cbResult.next() + "\n");
                }
                System.out.println("Press enter to go back.\n");
                scanner.next();
            } catch (SQLException e) {
                e.getMessage();
            }
                break;

            //Edit current bank accounts.
            case "2": try (PreparedStatement cbStatement = connection.prepareStatement("SELECT * FROM accounts WHERE id = ?")) {
                cbStatement.setInt(1, userId);
                ResultSet cbResult = cbStatement.executeQuery();
                while (cbResult.next()){
                    System.out.println(cbResult.next() + "\n");
                }
                System.out.println("Please enter the ID of the account number of the account you would like to modify.");
                answer2 = scanner.next();
                while (!answer2.equals(cbResult.getString("accountNumber"))){
                    System.out.println("Please enter a valid argument and try again.");
                    answer2 = scanner.next();
                }
                System.out.println("Would you like to\n1. Deposit money into your account.\n2. Withdraw money from your account.\n3. Delete the account.");
                answer3 = scanner.next();
                while (!answer3.equals("1") || !answer3.equals("2") || !answer3.equals("3")){
                    System.out.println("Please enter a valid argument and try again.");
                    answer3 = scanner.next();
                }
                switch (answer3){
                    //Deposit
                    case "1":
                    //Withdraw
                    case "2":
                    //Delete
                    case "3": System.out.println("Do you really want to delete this account? Y/N\n");
                    answer4 = scanner.next();
                    while (!answer4.equalsIgnoreCase("y") || !answer4.equalsIgnoreCase("yes") || !answer4.equalsIgnoreCase("n") || !answer4.equalsIgnoreCase("no")){
                        System.out.println("Please enter a valid argument and try again.\n");
                        answer4 = scanner.next();
                    }
                    if (answer4.equalsIgnoreCase("y") || answer4.equalsIgnoreCase("yes")){
                        try (PreparedStatement delStatement = connection.prepareStatement("DELETE * FROM accounts WHERE id = ?")) {
                            delStatement.setString(1, answer2);
                            delStatement.executeUpdate();
                            System.out.println("The account has been deleted. Thank you.\n");
                        } catch (SQLException e) {
                            e.getMessage();
                        }
                    }
                    else if (answer4.equalsIgnoreCase("n") || answer4.equalsIgnoreCase("no")){
                        System.out.println("Exiting back to previous menu.\n");
                    }
                }
                } catch(SQLException e){
                    e.getMessage();
                }
                break;

            //Apple for a new bank account.
            case "3": System.out.println("Will this be a\n1. joint account\n2. personal account\n");
                answer2 = scanner.next();
                while (!answer2.equals("1") || !answer2.equals("2")){
                    System.out.println("Please enter a valid argument and try again.\n");
                    answer2 = scanner.next();
                }
                switch (answer2){
                    //Joint Account
                    case "1": System.out.println("Please enter the number of people applying for this account today.\n");
                        answerInt = scanner.nextInt();
                        while (answerInt==0){
                            System.out.println("Please enter a valid argument and try again.\n");
                            answerInt = scanner.nextInt();
                        }
                        ArrayList<customer> = new ArrayList<>();
                        for (int i = 1; i < answerInt; i++){
                            System.out.println("Please enter the username.\n");
                            answer3 = scanner.next();
                        }
                    //Personal Account
                    case "2":
                }
                break;
            
            //Logout.
            case "4":
                break;
        }
    }

}