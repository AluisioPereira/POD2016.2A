package br.edu.ifpb.pod;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author ajp
 */
public class ConexaoFabrica {

    /**
     * Método retorna uma conexão com o banco de dados postgresql
     *
     * @return
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public Connection getConectionPostgres() throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        String url;
        String usuario;
        String senha;
        String nome;
        Properties properties = new Properties();
        properties.load(new FileInputStream(getClass().getResource("/banco/acesso.properties").toURI().getPath()));
        url = properties.getProperty("url");
        usuario = properties.getProperty("usuario");
        senha = properties.getProperty("senha");
        nome = properties.getProperty("nome");
        Class.forName(nome);
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     /**
     * Método retorna uma conexão com o banco de dados mysql
     *
     * @return
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public Connection getConectionMysql() throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        String url2;
        String usuario2;
        String senha2;
        String nome2;
        Properties properties = new Properties();
        properties.load(new FileInputStream(getClass().getResource("postgres.properties").toURI().getPath()));
        url2 = properties.getProperty("url2");
        usuario2 = properties.getProperty("usuario2");
        senha2 = properties.getProperty("senha2");
        nome2 = properties.getProperty("nome2");
        Class.forName(nome2);
        try {
            return DriverManager.getConnection(url2, usuario2, senha2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
