/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author aluisiopereira
 */
public class Servidor4 {

    private static ServerSocket servidor4 = null;
    private static Socket cliente4 = null;
    private static ObjectInputStream input4 = null;

    private static ObjectOutputStream output4 = null;
    private static Socket node4 = null;

    private static ObjectOutputStream output2 = null;
    private static Socket node2 = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // abrindo a porta de conexão.
        servidor4 = new ServerSocket(1234);
        //se conectando ao cliente (node1)
        System.out.println("Node4 esperando cliente...");
        cliente4 = servidor4.accept();

        //lendo objeto recebido
        input4 = new ObjectInputStream(cliente4.getInputStream());

        Op1 op1 = new Op1();
        Op2 op2 = new Op2();

        while (true) {

            if (op1.equals((Op1) input4.readObject())) {
                op1 = (Op1) input4.readObject();

                System.out.println("Sum(x,y): x=" + op1.getX() + "y=" + op1.getY());
                int soma = op1.getX() + op1.getY();
                System.out.println("É igua a: " + soma);
                cliente4.getOutputStream().write(soma);

            } else if (op2.equals((Op2) input4.readObject())) {
                op2 = (Op2) input4.readObject();

                System.out.println("Diff(x,y): x=" + op2.getX() + "y=" + op2.getY());
                int diff = op1.getX() - op1.getY();
                System.out.println("É igua a: " + diff);
                cliente4.getOutputStream().write(diff);
            }
        }

    }

}
