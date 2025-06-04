package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfigure {
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/social_media_db";
    private static final String dbPassword = "123";
    private static final String dbUsername = "postgres";
    public static Connection getDatabaseConnection(){
        try {
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        }catch (Exception exception){
            System.err.println("[!] ERROR during get connection with database: " + exception.getMessage());
        }
        return null;
    }
}
