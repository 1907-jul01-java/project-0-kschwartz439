package com.revature.users;

import java.util.Scanner;
import java.sql.*;

public class Customer{
    //Create Customer UI
    Scanner scanner;
    String response;
    String firstName;
    String lastName;
    String response2;
    String response3;
    String user;
    String password;
    Connection connection;
    
    User login = new User(user, password, connection);

    public Customer(){
        System.out.println("Please choose what you want to do. \n 1. Edit your account. \n 2. Make changes to your banking accounts. \n 3. Exit.");
        response = scanner.next();
        if (!response.equals("1") || !response.equals("2") || !response.equals("3")){
            System.out.println("Please enter a valid option and try again.");
        }
        while (!response.equals("3")){
            if (response.equals("1")){
                System.out.println("Please re-enter your information. \n Please enter your first name.");
                firstName = scanner.next();
                System.out.println("Please enter your last name");
                lastName = scanner.next();
                System.out.println("Thank you, " + firstName + " " + lastName + ".");
            }
            if (response.equals("2")){
                System.out.println("Would you like to \n 1. Make changes to existing accounts or \n 2. Open a new account. \n Enter 3 to return to the previous menu.");
                response2 = scanner.next();
                while (!response2.equals("3")){
                    if (response2.equals("1")){
                        System.out.println("Please choose which account you want to make changes to.");
                    }
                    if (response2.equals("2")){
                        System.out.println("Is this 1. your own account or is this 2. a joint account?");
                        response3 = scanner.next();
                        if (response3.equals("1")){
                            System.out.println("Your application to open a new account has been added. You will be able to see it if the application gets approved.");
                        }
                        if (response3.equals("2")){
                            System.out.println("Please enter the username of the second account.");
                            user = scanner.next();
                            System.out.println("Please enter the password of the second account.");
                            password = scanner.next();
                        }
                    }
                }
            }
        }
    }

    public void Close(){
        try{
            scanner.close();
        }
        catch (Exception e){
            e.getMessage();
        }
    }
}