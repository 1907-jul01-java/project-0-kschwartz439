package com.revature.users;

import java.sql.*;
import java.util.Scanner;

public class Check{
        Connection connection;
        Scanner scanner;
        String type;
        String firstName;
        String lastName;
        String username;
        int userId;
        String userAccess;

    public Check(Scanner scanner, Connection connection){
        this.connection = connection;
        this.scanner = scanner;
    }

    //Login
    public String ask(){
        String answer;
        System.out.println("Do you have an account? Y/N");
        answer = scanner.next();
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")){
            return this.user();
        }
        else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")){
            this.creation(type);
        }
        else{
            System.out.println("Please enter a valid argument and try again. \n");
            this.ask();
        }
        return null;
    }

    //Validate
    private String user(){
        String answer;
        System.out.println("Please enter your username, then press Enter.");
        answer = scanner.next();
        try (PreparedStatement uStatement = connection.prepareStatement("SELECT * FROM userLogins WHERE username = ? JOIN users")) {
            uStatement.setString(1, answer);
            ResultSet userResult = uStatement.executeQuery();
            if (userResult.next()){
                System.out.println("Please enter your password.");
                answer = scanner.next();
                if (answer.equals(userResult.getString("userPass"))){
                    System.out.println("Welcome, " + userResult.getString("firstName") + " " + userResult.getString("lastName") + ".\n");
                    userAccess = userResult.getString("access");
                    this.access();
                }
                else{
                    System.out.println("Invalid Password. \n");
                    this.ask();
                }
            }
            else{
                System.out.println("Invalid Username. \n");
                this.ask();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }
    
    //Creation
    public String creation(String type){
        this.type = type;
        String username, password, answer;
        System.out.println("Please enter your username. \n");
        answer = scanner.next();
        if (answer!=null){
            try (PreparedStatement userNames = connection.prepareStatement("SELECT username FROM userLogins WHERE username = ?")) {
                userNames.setString(1, answer);
                ResultSet userSet = userNames.executeQuery();
                if (userSet.next()){
                    System.out.println("Please try again with a unique username. \n");
                    this.creation(type);
                }
                else{
                    username = answer;
                    System.out.println("Please enter your password. \n");
                    answer = scanner.next();
                    if (answer!=null){
                        password = answer;
                        try (PreparedStatement userUpdate = connection.prepareStatement("INSERT VALUES (?, ?) INTO userLogins")) {
                            userUpdate.setString(1, username);
                            userUpdate.setString(2, password);
                            userUpdate.executeUpdate();
                            PreparedStatement idUser = connection.prepareStatement("SELECT userId FROM userLogins WHERE username = ?");
                            idUser.setString(1, username);
                            ResultSet userMiddle = idUser.executeQuery();
                            userId = userMiddle.getInt("userId");
                        } catch (SQLException e) {
                            e.getMessage();
                        }
                        this.person(userId);
                    }
                    else{
                        System.out.println("Please try again. \n");
                        this.creation(type);
                    }
                }
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        else{
            System.out.println("Please try again. \n");
            this.creation(type);
        }

        return null;
    }

    //Personal Information
    public String person(int userId){
        String answer;
        System.out.println("Please enter your first name. \n");
        answer = scanner.next();
        if (answer!=null){
            firstName = answer;
            System.out.println("Please enter your last name. \n");
            answer = scanner.next();
            if (answer!=null){
                lastName = answer;
                try (PreparedStatement usersStatement = connection.prepareStatement("INSERT VALUES (firstName, lastName) INTO users WHERE id = ?")) {
                    usersStatement.setInt(1, userId);
                    usersStatement.executeQuery();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
            else{
                System.out.println("Please try again. \n");
                this.person(userId);
            }
        }
        else{
            System.out.println("Please try again. \n");
            this.person(userId);
        }
        return null;
    }

    public void access(){
        switch (userAccess){
            case "customer": Customer customer = new Customer(userId);
                customer.menu();
                break;
            case "employee": Employee employee = new Employee();
                employee.menu();
                break;
            case "admin":
                Admin admin = new Admin();
                admin.menu();
                break;
            case "creator": Creator creator = new Creator();
                creator.menu();
                break;
        }
    }

    public void Close(){

    }
}