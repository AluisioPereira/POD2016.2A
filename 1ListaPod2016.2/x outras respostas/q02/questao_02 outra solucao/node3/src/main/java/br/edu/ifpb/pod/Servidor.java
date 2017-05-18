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
import java.util.Random;

/**
 *
 * @author aluisiopereira
 */
public class Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // abrindo a porta de conexão.
        ServerSocket servidor3 = new ServerSocket(1234);
        //se conectando ao cliente (node1)
        Socket cliente3 = servidor3.accept();
                                                                                                                                                                                                                                                                                                                                                                                                                                
        //lendo objeto recebido
        ObjectInputStream input3 = new ObjectInputStream(cliente3.getInputStream());

        Object ob = (Object) input3.readObject();

        Op2 op2 = (Op2) input3.readObject();

        if (!ob.equals(op2)) {

            Random i = new Random();
            int nodeEnviar = i.nextInt(1);

            switch (nodeEnviar) {
                case 0: {
                    Socket node1 = new Socket("localhost", 1099);
                    //enviando o objeto
                    ObjectOutputStream output1 = new ObjectOutputStream(node1.getOutputStream());
                    output1.writeObject(ob);
                    output1.flush();
                    System.out.println("Forward_op1() para node1");
                    node1.close();
                    output1.close();
                    break;
                }
                case 1: {
                    Socket node2 = new Socket("localhost", 1088);
                    ObjectOutputStream output2 = new ObjectOutputStream(node2.getOutputStream());
                    output2.writeObject(ob);
                    output2.flush();
                    System.out.println("Forward_op1() para node2");
                    node2.close();
                    output2.close();
                    break;
                }

            }

        }else{
            System.out.println("Sabendo que x = " + op2.getX() + " e y = " + op2.getY() + "\n A diferença entre eles é igual a: " + (op2.getX() - op2.getY()));
        }

        
        input3.close();
        cliente3.close();
        servidor3.close();
       
    }

}
