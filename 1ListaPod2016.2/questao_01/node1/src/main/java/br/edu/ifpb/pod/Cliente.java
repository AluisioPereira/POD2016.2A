/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ajp
 */
public class Cliente {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //criando numeros aleatorios entre 0 e 100
        Random r = new Random();
        int num1 = r.nextInt(100);
        int num2 = r.nextInt(100);
        //atribuindo os aleatorios a um objetos chamando numeros 
        Numeros n = new Numeros();
        n.setNum1(num1);
        n.setNum2(num2);

        //abrindo comunicação com node2
        Socket node2 = new Socket("localhost", 1099);
        //enviando o objeto
        ObjectOutputStream output = new ObjectOutputStream(node2.getOutputStream());
        output.writeObject(n);
        output.flush();

        //retorno do node2
        int r1 = node2.getInputStream().read();
        if (r1==0){
        System.out.println("Retorno de node2: " + r1 + " (zero)");
        }else{
            System.out.println("Encaminhado a node2 que envio a node3");
        }

        /**
         * ou Scanner s = new Scanner(node2.getInputStream()); while
         * (s.hasNextLine()) { System.out.println("Retorno: " + s.nextLine() + "
         * (zero)"); }
         *
         * s.close();
         *
         */
        //fechando comunicações
        output.close();

        node2.close();
    }

}
