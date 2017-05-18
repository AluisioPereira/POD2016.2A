package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ajp
 */
public class Principal1 {

    private static ServerSocket servidor1 = null;
    private static Socket cliente1 = null;
    private static ObjectInputStream input1 = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException, SQLException {

        // abrindo a porta de conexão.
        servidor1 = new ServerSocket(1099);
        //se conectando ao cliente (node2)

        while (true) {
            System.out.println("..................................");
            System.out.println("Node1 esperando nova notificações...");
            cliente1 = servidor1.accept();
            //lendo objeto recebido
            input1 = new ObjectInputStream(cliente1.getInputStream());
            System.out.println("node1 foi notificado.");
            ArrayList<Pessoa> listaPessoas = (ArrayList<Pessoa>) input1.readObject();
            System.out.println("Pessoas no banco:");
            System.out.println("----------------------");
            for (Pessoa p : listaPessoas) {
                System.out.println("Id: " + p.getId() + " | Nome: " + p.getNome());   
            }
            System.out.println("----------------------");

            if (listaPessoas != null) {
                cliente1.getOutputStream().write(1);
                System.out.println("Notificação encerrada.");
            } else {
                cliente1.getOutputStream().write(0);
                System.out.println("node1 recebeu vazia a notificação.");
            }
            input1.close();
            cliente1.close();

        }
    }

}
