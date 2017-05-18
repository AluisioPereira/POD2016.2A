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
public class Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // abrindo a porta de conexão.
        ServerSocket servidor2 = new ServerSocket(1088);
        //se conectando ao cliente (node1)
        Socket cliente2 = servidor2.accept();

        //lendo objeto recebido
        ObjectInputStream input2 = new ObjectInputStream(cliente2.getInputStream());

        Object ob = (Object) input2.readObject();

        Op1 op1 = (Op1) input2.readObject();
        
        if (!ob.equals(op1)){
            cliente2.getOutputStream().write(0);           
        }else{
             System.out.println("Sabendo que x = " + op1.getX() + " e y = " + op1.getY() + "\n A soma entre ele é igual a: " + (op1.getX() + op1.getY()));
        }       

        input2.close();
        cliente2.close();
        servidor2.close();

    }

}
