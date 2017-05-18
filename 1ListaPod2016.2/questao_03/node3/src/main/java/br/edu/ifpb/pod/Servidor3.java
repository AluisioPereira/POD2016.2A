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
public class Servidor3 {

    private static ServerSocket servidor3 = null;
    private static Socket cliente3 = null;
    private static ObjectInputStream input3 = null;

    private static ObjectOutputStream output4 = null;
    private static Socket node4 = null;

    private static ObjectOutputStream output2 = null;
    private static Socket node2 = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // abrindo a porta de conexão.
        servidor3 = new ServerSocket(1088);

        while (true) {
            //se conectando ao cliente (node1)
            System.out.println("Node3 esperando cliente...");
            cliente3 = servidor3.accept();

            //lendo objeto recebido
            input3 = new ObjectInputStream(cliente3.getInputStream());

            Operacao op = (Operacao) input3.readObject();

            if (op.getId() == 1) {
                System.out.println("Operação 1");
                int i = enviarNode2(op);
                cliente3.getOutputStream().write(new Double (i).byteValue());
            } else if (op.getId() == 2) {
                System.out.println("Operação 2");
                int i = enviarNode4(op);
                cliente3.getOutputStream().write(new Double (i).byteValue());
            }

        }

    }

    public static int enviarNode4(Operacao op2) throws IOException {
        //abrindo comunicação com node3
        System.out.println("Op2=diff(x,y)\nEncaminhando a node4.");
        node4 = new Socket("localhost", 1234);
        System.out.println("Aberta conexão com node4");
        //enviando o objeto
        output4 = new ObjectOutputStream(node4.getOutputStream());
        output4.writeObject(op2);
        output4.flush();
        System.out.println("Recebedo de node4...");

        int i = node4.getInputStream().read();

        System.out.println("Resultado op1:diff(x,y), retornando a node2");
        
        output4.close();
        node4.close();
        

        return i;

    }

    public static int enviarNode2(Operacao op1) throws IOException {
        System.out.println("Op1=sum(x,y)\nEncaminhando a node2.");
        node2 = new Socket("localhost", 1099);
        System.out.println("Aberta conexão com node2");
        output2 = new ObjectOutputStream(node2.getOutputStream());
        output2.writeObject(op1);
        output2.flush();

        System.out.println("Recebedo de node2...");

        int i = node2.getInputStream().read();

        System.out.println("Resultado op1:sum(x,y) retornando a node1");
        
        output2.close();
        node2.close();

        return i;
    }

}
