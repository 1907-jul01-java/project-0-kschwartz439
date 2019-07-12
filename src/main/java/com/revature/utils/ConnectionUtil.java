package com.revature.utils;

import java.sql.*;
import java.util.Properties;
import java.io.*;

public class ConnectionUtil{
    private Connection connection;
    public String url, user, password;
    public String checkId;

    public ConnectionUtil(){
        try{
            Properties properties = new Properties();
            properties.load(new FileReader("application.properties"));
            this.url = properties.getProperty("url");
            this.user = properties.getProperty("user");
            this.password = properties.getProperty("password");

            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        }
        catch (SQLException e){

        }
        catch (IOException e){

        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void close(){
        try {
            this.connection.close();
        }
        catch (SQLException e){

        }
    }
}