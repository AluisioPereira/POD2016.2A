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
public class Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // abrindo a porta de conexão.
        ServerSocket servidor1 = new ServerSocket(1099);
        //se conectando ao cliente (node1)
        Socket cliente1 = servidor1.accept();

        //lendo objeto recebido
        ObjectInputStream input1 = new ObjectInputStream(cliente1.getInputStream());

        Object ob = (Object) input1.readObject();

        Op1 op1 = (Op1) input1.readObject();

        if (!ob.equals(op1)) {
            //abrindo comunicação com node3
            Socket node3 = new Socket("localhost", 1234);
            //enviando o objeto
            ObjectOutputStream output3 = new ObjectOutputStream(node3.getOutputStream());
            output3.writeObject(ob);
            output3.flush();
            System.out.println("Forward_op2() para node3");

            output3.close();
            node3.close();
        } else {
            System.out.println("Sabendo que x = " + op1.getX() + " e y = " + op1.getY() + "\n A soma entre ele é igual a: " + (op1.getX() + op1.getY()));
        }

        input1.close();
        cliente1.close();
        servidor1.close();

    }

}
