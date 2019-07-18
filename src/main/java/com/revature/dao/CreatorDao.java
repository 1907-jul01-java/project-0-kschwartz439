package com.revature.dao;

import java.sql.*;
import java.util.Scanner;

import com.revature.App;

public class CreatorDao{
    String answer;
    String answer2;
    String answer3;
    String firstName;
    String lastName;
    String answer4;
    String accId;
    double accBalance;
    Scanner scanner = new Scanner(System.in);
    Connection connection;
    UserDao uDao = new UserDao();
    AccountDao aDao = new AccountDao();
    LoginDao lDao = new LoginDao();
    App app = new App();
    
    public void menu(int userID){
        boolean a = true;
        while(a=true){System.out.println("Please select what you want to do. \n1. Edit admin accounts. \n2. Edit employee accounts. \n3. Edit customer accounts. \n4. View bank accounts with User ID. \n5. Approve Accounts.\n6. Exit.");
        answer = scanner.next();
        switch (answer){
            
            //Edit accounts with the 'admin' access modifier.
            case "1": uDao.GetAllAdmins();
                break;

            //Edit accounts with the 'employee' access modifier.
            case "2": uDao.GetAllEmployees();
                break;
                
            //Edit accounts with the 'customer' access modifier.
            case "3": uDao.GetAllCustomers();
                break;

            //Edit bank accounts with a given user ID.
            case "4": aDao.ViewOther();
                break;

            //Approve Accounts.
            case "5": aDao.Create();
                break;

            //Logout.
            case "6": lDao.Logout();
                lDao.CheckUserName();
                a=false;
                break;
            
            default: this.menu(userID);
                break;
        }
    }
    }
}