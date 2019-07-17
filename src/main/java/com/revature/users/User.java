package com.revature.users;

import java.sql.*;
import java.util.Scanner;

public abstract class User{
    protected String user;
    protected String password;
    protected String userPass;
    protected String username;
    Connection connection;
    Scanner scanner = new Scanner(System.in);
}