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

    public void menu(int userID){
        boolean a = true;
        if (a=true){System.out.println("Please select what you want to do.\n1. Edit bank accounts. \n2. View your accounts.\n3. Edit your accounts.\n4. Apply for an account.\n5. Logout");
        answer = scanner.next();
        switch (answer){
            //Edit bank accounts with a given user ID.
            case "1": aDao.ViewOther(userID);
                break;
                
            //View own bank accounts
            case "2": aDao.ViewOwnEmployee(userID);
            break;

            //Edit current bank accounts
            case "3": aDao.EditMenuEmployee(userID);
            break;

            //Apply for a bank account.
            case "4": aDao.JointMenuEmployee(userID);
            break;

            //Logout.
            case "5": try{connection.close();}catch(SQLException e){e.printStackTrace();}
            a=false;
            lDao.Logout();
                break;
            
            default: this.menu(userID);
                break;
        }
    }
}
}