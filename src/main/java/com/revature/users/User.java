package com.revature.users;

import java.sql.*;

import com.revature.*;
import com.revature.utils.*;
import com.revature.users.*;

public abstract class User{
    String user;
    String password;

    public User(String adminId){
        this.user = adminId;
    }

    protected void getAccessLevel(){
        ConnectionUtil connection = new ConnectionUtil();
        this.user = user;
        this.password = password;
        //Check user against creatorId and password against creatorPass, preferable in an if statement.
        //Check user against adminId and password against adminPass, preferable in an if statement.
        //Check user against employeeId and password against employeePass, preferable in an if statement.
        //Check user against customerId and password against customerPass, preferable in an if statement.
        //If none work, just close the connection.
    }
}