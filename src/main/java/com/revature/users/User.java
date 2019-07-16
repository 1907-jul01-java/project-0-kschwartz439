package com.revature.users;

import java.sql.*;
import java.util.Scanner;

public class User{
    protected String user;
    protected String password;
    protected String userPass;
    protected String username;
    Connection connection;
    Scanner scanner = new Scanner(System.in);

    public User(Scanner scanner, Connection connection){

    }




    public void Close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("System refused to close");
        }
    }

}