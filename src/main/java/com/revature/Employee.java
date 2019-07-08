package com.revature;

public class Employee extends User{

    public Employee() {
        super();
    }
    
    public Employee(String firstName, String lastName, int birthday, int birthYear, int birthMonth){
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        //password
    }

}