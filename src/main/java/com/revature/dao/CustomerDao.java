package com.revature.dao;

import java.util.Scanner;
import java.sql.*;
import com.revature.connectionutils.*;

public class CustomerDao{
    String answer;
    String answer2;
    Scanner scanner = new Scanner(System.in);
    ConnectionUtils conUtil = new ConnectionUtils();
    Connection connection = conUtil.getConnection();
    LoginDao lDao = new LoginDao();
    UserDao uDao = new UserDao();
    AccountDao aDao = new AccountDao();

    public void menu(int userID){
        boolean a=true;
        if (a=true){System.out.println("Would you like to\n1. Check your bank accounts.\n2. Edit your current bank accounts.\n3. Apply for a new bank account.\n4. Logout.\n");
        answer = scanner.next();
        switch (answer){
            case "1": aDao.ViewOwn(userID);
            break;
            case "2":
                aDao.EditMenuCustomer(userID);
                break;
            case "3": aDao.JointMenu(userID);
                break;
            case "4": try{connection.close();}catch(SQLException e){e.printStackTrace();}
            a=false;
            lDao.Logout();
            break;
            default: this.menu(userID);
                break;
        }
    }
}
}