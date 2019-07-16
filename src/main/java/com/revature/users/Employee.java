package com.revature.users;

import java.util.Scanner;

public class Employee{
    //Create Employee UI
    String answer;
    Scanner scanner;

    public Employee(){
        
    }
    
    public void menu(){
        System.out.println("Please choose what you want to do. \n 1. Edit bank accounts. \n 2. Exit.\n");
        answer = scanner.next();
        while (!answer.equals("1") || !answer.equals("2")){
            System.out.println("Please enter a valid argument and try again.\n");
            answer = scanner.next();
        }
        switch (answer){
            case "1": 
                break;
            case "2": 
                break;
        }
    }
}