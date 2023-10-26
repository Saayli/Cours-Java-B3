package fr.epsi.b32324.c1.sayli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Database {
    ResourceBundle monFichierConf = ResourceBundle.getBundle("config");
    public final String DB_URL = monFichierConf.getString("database.url");
    public final String DB_USER = monFichierConf.getString("database.user");
    public final String DB_PWD = monFichierConf.getString("database.password");

    public static void main(String[] args) {
        Database database = new Database();
        database.testConnection();
    }

    public void testConnection(){
        try(Connection cnx = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD)){
            System.out.println(cnx);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}