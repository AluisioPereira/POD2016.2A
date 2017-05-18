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
public class Servidor2 {

    private static ServerSocket servidor2 = null;
    private static Socket cliente2 = null;
    private static ObjectInputStream input2 = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // abrindo a porta de conexão.
        servidor2 = new ServerSocket(1088);
        //se conectando ao cliente (node1)

        System.out.println("Node2 esperando cliente...");

        cliente2 = servidor2.accept();

        //lendo objeto recebido
        input2 = new ObjectInputStream(cliente2.getInputStream());
        while (true) {

            Op1 op1 = (Op1) input2.readObject();

            if (op1 != null) {
                System.out.println("Sabendo que x = " + op1.getX() + " e y = " + op1.getY() + "\n A soma entre ele é igual a: " + (op1.getX() + op1.getY()));

            } else {
                System.out.println("Não resolveu encaminando para node3");
            }

            System.out.println("Node2 operação resolvida. \n ------------");
        }

    }

}
