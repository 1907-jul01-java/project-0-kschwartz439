package com.revature.dao;

import java.sql.*;
import java.util.Scanner;
import com.revature.connectionutils.*;

public class EmployeeDao{
    String answer;
    Scanner scanner = new Scanner(System.in);
    ConnectionUtils conUtil = new ConnectionUtils();
    Connection connection = conUtil.getConnection();
    LoginDao lDao = new LoginDao();
    UserDao uDao = new UserDao();
    AccountDao aDao = new AccountDao();

    public void menu(){
        boolean a = true;
        while (a=true){System.out.println("Please select what you want to do.\n1. Edit bank accounts. \n2. Exit.");
        answer = scanner.next();
        switch (answer){
            //Edit bank accounts with a given user ID.
            case "1": aDao.ViewOther();
                break;

            //Logout.
            case "2": lDao.Logout();
                lDao.CheckUserName();
                a=false;
                break;
            
            default: this.menu();
                break;
        }
    }
}
}