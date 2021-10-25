package com.smartboard.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  @author Carl Karama
 *  @implNote We only want one instance to the database. So we use a creational design pattern called
 *            Singleton. So every time we call getDatabaseHandlerSingleton() we get the same instance of
 *            databaseHandlerSingleton.
 *
 *  @version 1.0 */


public final class DatabaseHandlerSingleton {

    private static Connection databaseHandlerSingleton;

    static {
            String url = "jdbc:mysql://localhost:3306/smartboard";
            String user = "root";
            String pass = "root";

            try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 databaseHandlerSingleton = DriverManager.getConnection(url, user, pass);
                 //System.out.println("Connection is successful");
            }
            catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    public static Connection getDatabaseHandlerSingleton() {
        return databaseHandlerSingleton;
    }
}
