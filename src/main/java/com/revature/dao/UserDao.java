package com.revature.dao;

import java.sql.*;

public class UserDao{
    String username;
    String firstName;
    String lastName;
    int userId;
    Connection connection;
    LoginDao lDao = new LoginDao();

    public void GetName(){
        username = lDao.username;
        try (PreparedStatement nStatement = connection.prepareStatement("SELECT * FROM users INNER JOIN userLogins ON userId WHERE username = ?")) {
            nStatement.setString(1, username);
            ResultSet nResult = nStatement.executeQuery();
            lastName = nResult.getString("lastName");
            firstName = nResult.getString("firstName");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void GetUserID(){
        username = lDao.username;
        try (PreparedStatement idStatement = connection.prepareStatement("SELECT * FROM userLogins WHERE username = ?")) {
            idStatement.setString(1, username);
            ResultSet idResult = idStatement.executeQuery();
            userId = idResult.getInt("userId");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

}