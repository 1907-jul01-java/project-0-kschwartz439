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
        if (a=true){System.out.println("Please select what you want to do.\n1. Edit employee accounts. \n2. Edit customer accounts. \n3. View bank accounts with User ID. \n4. Approve an account.\n5. View your bank accounts.\n6. Edit your bank accounts.\n7. Apply for an account.\n8. Add a user to an existing account.\n9. Next menu.");
        answer = scanner.next();
        switch (answer){
            //Edit accounts with the 'employee' access modifier.
            case "1": uDao.GetAllEmployees(userID);
                break;
                
            //Edit accounts with the 'customer' access modifier.
            case "2": uDao.AdminGetCustomer(userID);
                break;

            //Edit bank accounts with a given user ID.
            case "3": aDao.ViewOtherAdmin(userID);
                break;

            //Approve accounts.
            case "4": aDao.CreateAdmin(userID);
                break;

            //View own bank accounts
            case "5": aDao.ViewOwnAdmin(userID);
            break;

            //Edit current bank accounts
            case "6": aDao.EditMenuAdmin(userID);
            break;

            //Apply for a bank account.
            case "7": aDao.JointMenuAdmin(userID);
            break;

            //Add to a joint account.
            case "8": aDao.AddJoint(userID);
            break;

            case "9": a=false;
            this.menu2(userID);
            break;
            
            default: this.menu(userID);
                break;
        }
    }
}

    public void menu2(int userID){
        System.out.println("1. Remove a user from an account.\n2. Close an account.\n3. Go back.\n4. Logout.");
        boolean a = true;
        if (a=true){
            answer = scanner.next();
            switch (answer){
                case "1": aDao.Remove(userID);
                break;
                case "2": aDao.DeleteAdmin(userID);
                break;
                case "3": this.menu(userID);
                a=false;
                break;
                case "4": try{connection.close();}catch(SQLException e){e.printStackTrace();}
                a=false;
                lDao.Logout();
                break;
                default:
                break;
            }
        }
    }
}