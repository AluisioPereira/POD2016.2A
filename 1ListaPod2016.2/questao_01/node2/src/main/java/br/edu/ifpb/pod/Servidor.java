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
 * @author ajp
 */
public class Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // abrindo a porta de conexão.
        ServerSocket servidor1 = new ServerSocket(1099);
        //se conectando ao cliente (node1)
        Socket node1 = servidor1.accept();

        //lendo objeto recebido
        ObjectInputStream input1 = new ObjectInputStream(node1.getInputStream());

        //convertendo em objeto numero
        Numeros ns1 = (Numeros) input1.readObject();

        if (ns1.getNum1() == ns1.getNum2()) {
            node1.getOutputStream().write(0);
            System.out.println("Retornando 0 (zero) a node1.");
        } else {
            //abrindo comunicação com node3
            Socket node3 = new Socket("localhost", 1234);
            //enviando o objeto a node3  
            ObjectOutputStream output3 = new ObjectOutputStream(node3.getOutputStream());
            System.out.println("Encaminhado a node3.");
            output3.writeObject(ns1);
            output3.flush();

            //fechando comunicação node3.
            output3.close();
            node3.close();

        }

        //fechando as conexões node1
        input1.close();
        servidor1.close();
        node1.close();

    }

}
