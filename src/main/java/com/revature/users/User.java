package com.revature.users;

import java.sql.*;
import java.util.Scanner;
import com.revature.*;

public class User extends Check{
    protected String user;
    protected String password;
    protected String userPass;
    protected String username;
    Connection connection;
    Scanner scanner = new Scanner(System.in);

    public User(){
        super();
    }


    public User(Connection connection){
        this.connection = connection;
    }

    public User(String user, String password, Connection connection){
        this.connection = connection;

        System.out.println("Do you already have an account? Y/N");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")){
            System.out.println("Would you like to create an account? Y/N");
            answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")){
                System.out.println("Thank you and have a nice day.");
            }
            else if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")){
                System.out.println("Please enter a username, then press enter.");
                user = scanner.nextLine();
                while (user.equalsIgnoreCase("exit") || user.equalsIgnoreCase("login")){
                    System.out.println("Please enter a valid username and try again.");
                    user = scanner.nextLine();
                }
                //cut out anything from " " onwards
                while (!user.equals(null) && !user.equals("")){
                    scanner.nextLine();
                    try (PreparedStatement userStatement = connection.prepareStatement("select username from userLogins where username = ?")){
                        userStatement.setString(1, user);
                        ResultSet userResult = userStatement.executeQuery();
                        if (userResult.next()){
                            System.out.println("Username already exists, please create a valid login.");
                            //DO NOT CREATE
                        }
                    }
                    catch (SQLException e){
                        e.getMessage();
                    }
                }
                System.out.println("Please enter a password, then press enter.");
                //Make sure the password isn't null here.
                //Maybe refactor again? It's getting close to time, but still, there feels like more that can be done.
                password = scanner.nextLine();
            }
            else{
                System.out.println("Please try again later.");
            }
        }
        else{
            System.out.println("Please enter your username, then press enter.");
            user = scanner.nextLine();
            System.out.println("Please enter your password, then press enter.");
            password = scanner.nextLine();
        }



        try (PreparedStatement passStatement = connection.prepareStatement("select userPass from userLogins where username = ?")) {
            passStatement.setString(1, user);
			ResultSet resultSet = passStatement.executeQuery();
			if (password == resultSet.getString(userPass) && user == resultSet.getString(username)){
                //push through to other classes
            }
            else{
                System.out.println("Please input a valid username and password and try again.");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void Close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("System refused to close");
        }
    }

}