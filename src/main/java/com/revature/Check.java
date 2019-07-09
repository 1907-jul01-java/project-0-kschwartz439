package com.revature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

interface Check {
    public static void main(String[] args){
        
        //Connection with the user from the application, not the server.
        try (ServerSocket sc = new ServerSocket(8080)){
            while (true){
                try (Socket socket = sc.accept();
                    OutputStream os = socket.getOutputStream()){
                    BufferedReader bread = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line = bread.readLine();
                    while (!line.isEmpty()){
                        System.out.println(line);
                        bread.readLine();
                    }
                    String response = "HTTP:/1.1 200 OK\r\nContent-Type: application.json\r\n\r\n{'name':'placeholder'";
                    os.write(response.getBytes("UTF-8"));
                }
            }
        }
        catch (IOException ex){
            
        }
    
        //Don't forget a username and password.
        //Try to make the password using some kind of cryptography if possible/applicable.
        //For the database, make the username the key value of the 'Users' section.
        //Do a join with all of the applicable keys to call only the accounts that are available to the user.
        //Require employees to have a username or some kind of indentifying information to look up customer info.
        //Give admins access to the whole database so the can open/close accounts.
        //Make a 'God' access that can create admin accounts so admins don't run around making multiple admin accounts.
    }
}