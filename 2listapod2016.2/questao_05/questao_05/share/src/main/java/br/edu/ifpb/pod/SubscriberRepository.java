package br.edu.ifpb.pod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ajp
 */
public class SubscriberRepository {

    String driver = "org.postgresql.Driver";
    String user = "postgres";
    String senha = "12345";
    String url = "jdbc:postgresql://localhost:5432/pod";
    Connection con = null;

    public SubscriberRepository() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        con = (Connection) DriverManager.getConnection(url, user, senha);
    }

    public void store(String uuid, Subscriber sub) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Topic VALUES (?,?)");
        preparedStatement.setString(1, uuid);
        preparedStatement.setObject(2, sub);
    }

    public Subscriber find(String uuid) {
        return null;
    }

    public String[] listUUIDs() {
        return null;
    }

}
