package com.java.designpatterns.creational.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/jdbc";
    private String username = "root";
    private String password = "root";

    // always private constuctor for Singleton Class
    private DbConnection(){
        try{
            Class.forName("org.mysql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception ex){
            System.out.println("Something is wrong with the DB connection String : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DbConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DbConnection();
        }
        return instance;
    }
}
