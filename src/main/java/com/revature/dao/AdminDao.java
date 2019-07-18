package com.revature.dao;

import java.sql.*;
import java.util.Scanner;
import com.revature.connectionutils.*;

public class AdminDao{
    String answer;
    Scanner scanner = new Scanner(System.in);
    ConnectionUtils conUtil = new ConnectionUtils();
    Connection connection = conUtil.getConnection();
    UserDao uDao = new UserDao();
    LoginDao lDao = new LoginDao();
    AccountDao aDao = new AccountDao();

    public void menu(int userID){
        boolean a = true;
        while (a=true){System.out.println("Please select what you want to do.\n1. Edit employee accounts. \n2. Edit customer accounts. \n3. View bank accounts with User ID. \n4. Approve an account.\n5. Exit.");
        answer = scanner.next();
        switch (answer){
            //Edit accounts with the 'employee' access modifier.
            case "1": uDao.GetAllEmployees();
                break;
                
            //Edit accounts with the 'customer' access modifier.
            case "2": uDao.GetAllCustomers();
                break;

            //Edit bank accounts with a given user ID.
            case "3": aDao.ViewOther();
                break;

            //Approve accounts.
            case "4": aDao.Create();
                break;

            //Logout.
            case "5": lDao.Logout();
                lDao.CheckUserName();
                a=false;
                break;
            
            default: this.menu(userID);
                break;
        }
    }
}
}