package com.revature;

public class Customer extends User{

    public Customer() {
        super();
    }
    
    public Customer(String firstName, String lastName, int birthday, int birthYear, int birthMonth){
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        //password
    }

}