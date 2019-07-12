package com.revature.connectionutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionUtils{
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
}