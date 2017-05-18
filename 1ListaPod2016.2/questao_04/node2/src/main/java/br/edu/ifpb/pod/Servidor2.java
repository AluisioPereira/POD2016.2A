
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 *
 * @author ajp
 */
public class Servidor2 {

    private static ServerSocket servidor2 = null;
    private static Socket cliente2 = null;
    private static ObjectInputStream input2 = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException, SQLException {

        // abrindo a porta de conexão.
        servidor2 = new ServerSocket(1099);
        //se conectando ao cliente (node1)

        while (true) {
            System.out.println("Node2 esperando cliente...");
            cliente2 = servidor2.accept();
            //lendo objeto recebido
            input2 = new ObjectInputStream(cliente2.getInputStream());
            Pessoa pessoa = (Pessoa) input2.readObject();

            System.out.println("Replicando ao bancos");
            
            
            boolean cadastrouPost = insertPostgres(pessoa);

            boolean cadastrouMy = insertMysql(pessoa);

            if (cadastrouPost && cadastrouMy) {
                cliente2.getOutputStream().write(1);
                System.out.println("Replicação feita com sucesso");
            } else {
                cliente2.getOutputStream().write(0);
                System.out.println("Não foi possivel ser feito a replicação");
            }
            input2.close();
            cliente2.close();

        }
    }

    public static synchronized boolean insertPostgres(Pessoa pessoa) throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        PessoaBoPostgres clienteBoPostgres = new PessoaBoPostgres();
        System.out.println("adicionando pessoa no postgres.");
        boolean cadastrouPost = clienteBoPostgres.adicionarCliente(pessoa);
        return cadastrouPost;
    }

    public static synchronized boolean insertMysql(Pessoa pessoa) throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        PessoaBoMysql clienteBoMysql = new PessoaBoMysql();
        System.out.println("adicionando pessoa no mysql");
        boolean cadastrouMy = clienteBoMysql.adicionarCliente(pessoa);
        return cadastrouMy;
    }

}
