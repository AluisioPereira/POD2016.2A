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
public class Servidor4 {

    private static ServerSocket servidor4 = null;
    private static Socket cliente4 = null;
    private static ObjectInputStream input4 = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // abrindo a porta de conexão.
        servidor4 = new ServerSocket(1234);

        while (true) {
            //se conectando ao cliente (node1)
            System.out.println("Node4 esperando cliente...");
            cliente4 = servidor4.accept();

            //lendo objeto recebido
            input4 = new ObjectInputStream(cliente4.getInputStream());
            Operacao op = (Operacao) input4.readObject();
            if (op.getId() == 1) {
                System.out.println("Sum(x,y): x=" + op.getX() + " y=" + op.getY());
                int soma = op.getX() + op.getY();
                System.out.println("É igua a: " + soma);

                cliente4.getOutputStream().write(soma);
                System.out.println("Retornando resposta...");
            } else if (op.getId() == 2) {
                System.out.println("Diff(x,y): x=" + op.getX() + " y=" + op.getY());
                int diff = op.getX() - op.getY();
                System.out.println("É igua a: " + diff);
                Integer d = new Integer (diff);
                cliente4.getOutputStream().write(d.byteValue());

                System.out.println("Retornando resposta...");
            }
            input4.close();
            cliente4.close();

        }

    }
}
