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
public class Servidor2 {

    private static ServerSocket servidor3 = null;
    private static Socket cliente3 = null;
    private static ObjectInputStream input3 = null;

    private static ObjectOutputStream output3 = null;
    private static Socket node3 = null;

    private static ObjectOutputStream output4 = null;
    private static Socket node4 = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // abrindo a porta de conexão.
        servidor3 = new ServerSocket(1099);
        //se conectando ao cliente (node1)
        System.out.println("Node2 esperando cliente...");
        cliente3 = servidor3.accept();

        //lendo objeto recebido
        input3 = new ObjectInputStream(cliente3.getInputStream());

        Op1 op1 = new Op1();
        Op2 op2 = new Op2();

        while (true) {

            if (op1.equals((Op1) input3.readObject())) {
                System.out.println("Operação 1");
                op1 = (Op1) input3.readObject();
                int i = enviarNode4(op1);
                cliente3.getOutputStream().write(i);

            } else if (op2.equals((Op2) input3.readObject())) {
                System.out.println("Operação 2");
                op2 = (Op2) input3.readObject();
                int i = enviarNode3(op2);
                cliente3.getOutputStream().write(i);
            }
        }

    }

    public static int enviarNode3(Op2 op2) throws IOException {
        //abrindo comunicação com node3
        System.out.println("Op2=diff(x,y)\nEncaminhando a node3.");
        node3 = new Socket("localhost", 1088);
        System.out.println("Aberta conexão com node3");
        //enviando o objeto
        output3 = new ObjectOutputStream(node3.getOutputStream());
        output3.writeObject(op2);
        output3.flush();
        System.out.println("Recebedo...");
        ObjectInputStream input3 = new ObjectInputStream(node3.getInputStream());
        int i = input3.readInt();
        System.out.println("Resultado op1:diff(x,y), é igual a:" + input3);

        return i;

    }

    public static int enviarNode4(Op1 op1) throws IOException {
        System.out.println("Op1=sum(x,y)\nEncaminhando a node4.");
        node4 = new Socket("localhost", 1234);
        System.out.println("Aberta conexão com node4");
        output4 = new ObjectOutputStream(node4.getOutputStream());
        output4.writeObject(op1);
        output4.flush();

        System.out.println("Recebedo de node4...");
        ObjectInputStream input4 = new ObjectInputStream(node4.getInputStream());
        int i = input4.readInt();
        System.out.println("Resultado op1:diff(x,y) retornando a node1");

        return i;
    }

}
