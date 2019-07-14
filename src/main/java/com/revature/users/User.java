package com.revature.users;

import java.sql.*;

public class User{
    protected String user;
	protected String userPass;
    Connection connection;

    public User(){

    }

    public User(Connection connection){
        this.connection = connection;
    }

    public User(String user, Connection connection){
        this.connection = connection;

        try (PreparedStatement passStatement = connection.prepareStatement("select userPass from userLogins where username = ?")) {
            passStatement.setString(1, user);
			ResultSet resultSet = passStatement.executeQuery();
			this.userPass = resultSet.getString(userPass);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

}