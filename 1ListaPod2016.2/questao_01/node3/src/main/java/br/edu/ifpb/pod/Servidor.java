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
 * @author ajp
 */
public class Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // abrindo a porta de conexão.
        ServerSocket servidor2 = new ServerSocket(1234);
        //se conectando ao cliente (node2)
        Socket node2 = servidor2.accept();

        //lendo objeto recebido
        ObjectInputStream input2 = new ObjectInputStream(node2.getInputStream());

        //convertendo em objeto numero
        Numeros ns2 = (Numeros) input2.readObject();
        
        System.out.println("Sabendo que f(x, y) = x^y+y^x e x = "+ns2.getNum1()+" y = "+ns2.getNum2()+" temos:");

        System.out.println("Resultado de f(" + ns2.getNum1() + ", " + ns2.getNum2() + ") = " + (Math.pow(ns2.getNum1(), ns2.getNum2()) + Math.pow(ns2.getNum2(), ns2.getNum1())));

        //fechando conexões
        input2.close();
        servidor2.close();
        node2.close();
    }

}
