package com.revature.dao;

import java.sql.*;
import java.util.Scanner;

import com.revature.App;
import com.revature.connectionutils.*;

public class CreatorDao{
    String answer;
    String answer2;
    String answer3;
    String firstName;
    String lastName;
    String answer4;
    String accId;
    double accBalance;
    boolean a = true;
    boolean b = true;
    Scanner scanner = new Scanner(System.in);
    ConnectionUtils conUtil = new ConnectionUtils();
    Connection connection = conUtil.getConnection();
    UserDao uDao = new UserDao();
    AccountDao aDao = new AccountDao();
    LoginDao lDao = new LoginDao();
    App app = new App();
    
    public void menu(int userID){
        if(a=true){System.out.println("Please select what you want to do. \n1. Edit admin accounts. \n2. Edit employee accounts. \n3. Edit customer accounts. \n4. View bank accounts with User ID. \n5. Approve Accounts.\n6. View your own accounts.\n7. Edit your accounts.\n8. Apply for an account.\n9. More Options.");
        answer = scanner.next();
        switch (answer){
            
            //Edit accounts with the 'admin' access modifier.
            case "1": uDao.GetAllAdmins(userID);
                break;

            //Edit accounts with the 'employee' access modifier.
            case "2": uDao.CreatorGetEmployees(userID);
                break;
                
            //Edit accounts with the 'customer' access modifier.
            case "3": uDao.CreatorGetCustomer(userID);
                break;

            //Edit bank accounts with a given user ID.
            case "4": aDao.ViewOtherCreator(userID);
                break;

            //Approve Accounts.
            case "5": aDao.Create(userID);
                break;
                
            //View own bank accounts
            case "6": aDao.ViewOwnCreator(userID);
            break;

            //Edit current bank accounts
            case "7": aDao.EditMenuCreator(userID);
            break;

            //Apply for a bank account.
            case "8": aDao.JointMenuCreator(userID);
            break;

            //Logout.
            case "9": this.menu2(userID);
                break;
            
            default: this.menu(userID);
                break;
        }
    }
    }

    public void menu2(int userID){
        System.out.println("\nMenu 2:\n1. Add a user to an existing account.\n2. Remove a user from an existing joint account.\n3. Close a bank account.\n4. Go back.\n5. Logout.");
        answer = scanner.next();
        if (b=true){
            switch (answer){
                case "1": aDao.AddJointCreator(userID);
                break;
                case "2": aDao.RemoveCreator(userID);
                break;
                case "3": aDao.DeleteCreator(userID);
                break;
                case "4": this.menu(userID);
                break;
                case "5": try{connection.close();}catch(SQLException e){e.printStackTrace();}
                a=false;
                b=false;
                lDao.Logout();
                break;
                default: this.menu2(userID);
                break;
            }
        }
    }
}