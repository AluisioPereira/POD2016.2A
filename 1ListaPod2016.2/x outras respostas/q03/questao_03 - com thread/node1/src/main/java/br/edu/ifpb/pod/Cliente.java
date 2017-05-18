/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author aluisiopereira
 */
public class Cliente {

    public static void main(String[] args) throws IOException {

        //abrindo comunicação com node2
        Socket node2 = new Socket("localhost", 1099);
        //enviando o objeto
        ObjectOutputStream output2 = new ObjectOutputStream(node2.getOutputStream());

        //abrindo comunicação com node3
        Socket node3 = new Socket("localhost", 1088);
        //enviando o objeto
        ObjectOutputStream output3 = new ObjectOutputStream(node3.getOutputStream());

        Random r = new Random();

        int escolha = 1;

        while (escolha == 1) {

            int x = r.nextInt(100);
            int y = r.nextInt(100);

            int op1 = x + y;
            int op2 = x - y;

            int opEnviar = r.nextInt(2);

            ArrayList<Integer> ob = new ArrayList<Integer>();

            switch (opEnviar) {
                case 0: {
                    ob.add(1);
                    ob.add(op1);
                    System.out.println("Op1=sum(x,y) \nContruindo temos: Op1=sum(" + x + ", " + y + ")");
                    output3.writeObject(ob);
                    output3.flush();
                    System.out.println("Enviando a node3.");
                    break;

                }
                case 1: {
                    ob.add(2);
                    ob.add(op2);
                    System.out.println("Op2=diff(x,y) \nContruindo temos: Op2=diff(" + x + ", " + y + ")");
                    output2.writeObject(ob);
                    output2.flush();
                    System.out.println("Enviando a node2.");
                    break;
                }
            }

            Scanner s = new Scanner(System.in);
            System.out.println("------------ \n Menu \n 1 para execultar novamente; \n 0 para encerra. ");
            System.out.print("Digite: ");
            escolha = s.nextInt();
            System.out.println("__________________");

        }

        output2.close();
        output3.close();

        node2.close();
        node3.close();

    }

}
