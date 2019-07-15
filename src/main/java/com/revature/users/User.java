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

        System.out.println("Please enter a username, then press enter.");
        user = scanner.nextLine();
        System.out.println("Please enter a password, then press enter.");
        password = scanner.nextLine();

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