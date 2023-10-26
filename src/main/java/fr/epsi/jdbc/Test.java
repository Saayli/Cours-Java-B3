package fr.epsi.jdbc;

import fr.epsi.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Test {
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
        Test test = new Test();
        try(Connection cnx = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD)){
            Statement monStatement = cnx.createStatement();
            test.testInsert(monStatement);// -- Exercice 1 - TP2
            test.testUpdate(monStatement);// -- Exercice 2 - TP2
            test.testDelete(monStatement);// -- Exercice 3 - TP2
            test.testSelect(monStatement);// -- Exercice 4 - TP2
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void testInsert(Statement monStatement) throws SQLException {
        int nb = monStatement.executeUpdate("INSERT INTO fournisseur (NOM) VALUES ('La Maison de la Peinture')", Statement.RETURN_GENERATED_KEYS);
        System.out.printf("Le nombre de ligne(s) créé(s) est : %d%n", nb);
        try(ResultSet rs = monStatement.getGeneratedKeys()){
            if(rs.next()){
                System.out.printf("La clé générée est : %d%n", rs.getInt(1)); // le 1 dans getInt correspond à l'id du label;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void testUpdate(Statement monStatement) throws SQLException {
        int nb = monStatement.executeUpdate("UPDATE fournisseur SET NOM = 'La Maison des Peintures' WHERE NOM = 'La Maison de la Peinture'");
        System.out.println(nb);
    }

    public void testDelete(Statement monStatement) throws SQLException {
        int nb = monStatement.executeUpdate("DELETE FROM fournisseur WHERE NOM = 'La Maison des Peintures'");
        System.out.println(nb);
    }

    public void testSelect(Statement monStatement) throws SQLException {
        ResultSet curseur = monStatement.executeQuery("SELECT * FROM fournisseur");
        ArrayList<Fournisseur> listeFournisseur = new ArrayList<>();
        while (curseur.next()){
            Integer id = curseur.getInt("ID");
            String nom = curseur.getString("NOM");
            listeFournisseur.add(new Fournisseur(id, nom));
        }
        listeFournisseur.forEach(System.out::println);
    }
}
