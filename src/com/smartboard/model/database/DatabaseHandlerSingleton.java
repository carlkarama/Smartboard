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
            String url = "jdbc:mysql://smartboard.sneakervillain.com:3306/sneake23_smartboard";
            String user = "sneake23_admin_smartboard";
            String pass = "cK19962131!!";

            try {
                 databaseHandlerSingleton = DriverManager.getConnection(url, user, pass);
                 System.out.println("Connection is successful");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static synchronized Connection getDatabaseHandlerSingleton() {
        return databaseHandlerSingleton;
    }
}
