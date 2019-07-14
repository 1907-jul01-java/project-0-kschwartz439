package com.revature.connectionutils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

//Connection with the server.
public class ConnectionUtils{
    String url;
    String user;
    String password;
    Connection connection;

    public ConnectionUtils(){
        try {
            //Could also just hard code the properties into the strings above.
            Properties connprop = new Properties();
            connprop.load(new FileReader("connectionutils/connection.properties"));
            this.url = connprop.getProperty("url");
            this.user = connprop.getProperty("user");
            this.password = connprop.getProperty("password");
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            e.getMessage();
        }
        catch (IOException e){
            e.getMessage();
        }
    }

    public void close(){
        try{
            this.connection.close();
        } catch (SQLException e){
            e.getMessage();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}