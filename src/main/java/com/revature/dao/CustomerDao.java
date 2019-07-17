package com.revature.dao;

import java.util.Scanner;
import java.sql.*;

public class CustomerDao{
    String answer;
    String answer2;
    Scanner scanner = new Scanner(System.in);
    Connection connection;
    LoginDao lDao = new LoginDao();
    UserDao uDao = new UserDao();
    AccountDao aDao = new AccountDao();

    public void menu(){
        boolean a=true;
        while (a=true){System.out.println("Would you like to\n1. Check your bank accounts.\n2. Edit your current bank accounts.\n3. Apply for a new bank account.\n4. Logout.\n");
        answer = scanner.next();
        switch (answer){
            case "1": aDao.ViewOwn();
            break;
            case "2":
                System.out.println("Would you like to\n1. Make a deposit.\n2. Withdraw money.\n3. Close the account.");
                answer2 = scanner.next();
                switch (answer2){
                    case "1": aDao.Deposit();
                    break;
                    case "2": aDao.Withdraw();
                    break;
                    case "3": aDao.Delete();
                }
            case "3": System.out.println("Will this account be\n1. A personal account.\n2. A joint account.");
                answer2 = scanner.next();
                switch (answer2){
                    case "1": aDao.Apply();
                    break;
                    case "2": aDao.ApplyJoint();
                    break;
                }
                break;
            case "4": lDao.Logout();
            lDao.CheckUserName();
            a=false;
            break;
            default:
                break;
        }
    }
}
}