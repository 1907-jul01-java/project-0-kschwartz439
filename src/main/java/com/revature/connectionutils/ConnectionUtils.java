package com.revature.connectionutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Scanner;

//Connection with the server.
public class ConnectionUtils{
    public ConnectionUtils(){
        String url = "jdbc:postgresql://192.168.99.100:5432/postgres";
        String user = "KellyCr";
        String password = "p4ssw0rd";
        String query;

        String admin_id;

        try (Connection connection = DriverManager.getConnection(url, user, password)){
            Scanner scanner = new Scanner(System.in);

            while (true){
                PreparedStatement pstatement = connection.prepareCall("get_admin_id(admin_id)");
                pstatement.setString(/*# of rows*/, admin_id);
                //if ResultSet != null, check password
                //if password != adminPass, ask for a correct username/password
                PreparedStatement eStatement = connection.prepareCall("get_employee_id(employee_id)");
                //if get_employee_id != null, check password
                //if password != employeePass, ask for a correct username/password
                PreparedStatement cStatement = connection .prepareCall("get_customer_id(customer_id)");
                //if get_customer_id != null, check password
                // if password != customer pass, ask for a correct username/password
                //if all = null, ask for a correct username/password
            }
        }
        catch (SQLException e){

        }
    }
}