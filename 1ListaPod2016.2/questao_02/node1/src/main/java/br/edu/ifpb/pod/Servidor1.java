/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author aluisiopereira
 */
public class Servidor1 {

    private static ServerSocket servidor1 = null;
    private static Socket cliente1 = null;
    private static ObjectInputStream input1 = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // abrindo a porta de conexão.
        servidor1 = new ServerSocket(1099);
        //se conectando ao cliente (node1)
        System.out.println("Node1 esperando cliente...");

        cliente1 = servidor1.accept();

        System.out.println("conexao estabelecedia");
        //lendo objeto recebido
        input1 = new ObjectInputStream(cliente1.getInputStream());
        while (true) {

            Op1 op1 = (Op1) input1.readObject();

            if (op1 != null) {
                System.out.println("Sabendo que x = " + op1.getX() + " e y = " + op1.getY() + "\n A soma entre ele é igual a: " + (op1.getX() + op1.getY()));
            } else {
                System.out.println("Não resolveu encaminahdo para node3");
            }
            System.out.println("Node1 operação resolvida. \n ------------");
        }

    }

}
