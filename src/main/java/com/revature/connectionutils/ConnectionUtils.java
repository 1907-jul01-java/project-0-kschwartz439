package com.revature.connectionutils;

import java.sql.*;
//import java.util.Properties;

//Connection with the server.
public class ConnectionUtils{
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public ConnectionUtils(){
        try {
            //Could also just hard code the properties into the strings above.
            //Properties connprop = new Properties();
            //connprop.load(new FileReader("connectionutils/connection.properties"));
            this.url = "jdbc:postgresql://192.168.99.100:5432/postgres";
            this.user = "postgres";
            this.password = "postgres";
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
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
        return this.connection;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}