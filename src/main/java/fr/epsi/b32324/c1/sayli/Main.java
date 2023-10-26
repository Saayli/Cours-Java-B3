package fr.epsi.b32324.c1.sayli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Main {
    public static final String DB_URL;
    public static final String DB_USER;
    public static final String DB_PWD;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        DB_URL = bundle.getString("database.url");
        DB_USER = bundle.getString("database.user");
        DB_PWD = bundle.getString("database.password");
    }

    public static void main(String[] args) {
        try(Connection cnx = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD)){
            System.out.println(cnx);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}