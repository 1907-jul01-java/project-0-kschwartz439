package com.revature;

public class Admin extends User{
    
    public Admin() {
        super();
    }
    
    public Admin(String firstName, String lastName, int birthday, int birthYear, int birthMonth){
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        //password
    }

}