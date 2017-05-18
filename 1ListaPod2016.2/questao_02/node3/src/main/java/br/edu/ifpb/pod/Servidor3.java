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
public class Servidor3 {

    private static ServerSocket servidor3 = null;
    private static Socket cliente3 = null;
    private static ObjectInputStream input3 = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // abrindo a porta de conexão.
        servidor3 = new ServerSocket(1234);
        //se conectando ao cliente (node1)
        System.out.println("Node3 esperando cliente...");
        cliente3 = servidor3.accept();

        //lendo objeto recebido
        input3 = new ObjectInputStream(cliente3.getInputStream());

        while (true) {

            Op2 op2 = (Op2) input3.readObject();

            if (op2 != null) {
                System.out.println("Sabendo que x = " + op2.getX() + " e y = " + op2.getY() + "\n A diferença entre eles é igual a: " + (op2.getX() - op2.getY()));
            } else {
                System.out.println("Node3 não resolveu! \n encaminhando...\n para node1 \n ou \n para node2");
            }
            System.out.println("Node3 operação resolvida. \n ------------");

        }

    }

}
