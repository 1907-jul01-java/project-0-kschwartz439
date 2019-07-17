package com.revature;

import com.revature.dao.*;

public class App {
    public static void main(String[] args) {
        LoginDao lDao = new LoginDao();
        lDao.CheckUserName();
        lDao.CheckAccess();
        
    }
}
