package com.revature;

import com.revature.users.*;
import java.sql.*;
import java.util.Scanner;
import com.revature.connectionutils.*;

public class Check extends ConnectionUtils implements Access{

        protected String user;
        protected String password;
        protected String userPass;
        protected String username;
        Connection connection;
        Scanner scanner = new Scanner(System.in);

    public Check(){

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
        
        //Connection with the user from the application, not the server.

    
        //Try to make the password using some kind of cryptography if possible/applicable.
        //Do a join with all of the applicable keys to call only the accounts that are available to the user.
        //Require employees to have a username or some kind of indentifying information to look up customer info.
        //Give admins access to the whole database so the can open/close accounts.
        //Make a 'God' access that can create admin accounts so admins don't run around making multiple admin accounts.
    
}