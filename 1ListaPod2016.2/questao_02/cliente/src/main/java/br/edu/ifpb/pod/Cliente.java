/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author aluisiopereira
 */
public class Cliente {

    public static void main(String[] args) throws IOException {

        //abrindo comunicação com node1
        Socket node1 = new Socket("localhost", 1099);
        //enviando o objeto
        ObjectOutputStream output1 = new ObjectOutputStream(node1.getOutputStream());

        //abrindo comunicação com node2
        Socket node2 = new Socket("localhost", 1088);
        //enviando o objeto
        ObjectOutputStream output2 = new ObjectOutputStream(node2.getOutputStream());

        //abrindo comunicação com node1
        Socket node3 = new Socket("localhost", 1234);
        //enviando o objeto
        ObjectOutputStream output3 = new ObjectOutputStream(node3.getOutputStream());

        int escolha = 1;
        Random r = new Random();

        while (escolha == 1) {

            int x = 0;
            int y = 0;

            int opEnviar = 0;
            int nodeEnviar = 0;

            Op1 op1 = null;
            Op2 op2 = null;

            x = r.nextInt(100);
            y = r.nextInt(100);

            opEnviar = r.nextInt(2);
            nodeEnviar = r.nextInt(3);

            System.out.println("Operação a ser enviada: Op" + (opEnviar + 1));
            switch (opEnviar) {

                case 0: {
                    op1 = new Op1(x, y);
                    System.out.println("Op1=sum(x,y) \nContruindo temos: Op1=sum(" + x + ", " + y + ")");
                    break;
                }
                case 1: {
                    op2 = new Op2(x, y);
                    System.out.println("Op2=diff(x,y) \nContruindo temos: Op2=diff(" + x + ", " + y + ")");
                    break;
                }
            }


            System.out.println("Encaminhar ao Node" + (nodeEnviar + 1));
            System.out.println("\nEnviando...");
            switch (nodeEnviar) {
                case 0: {
                    System.out.println("Enviado ao node1");
                    if (op1 != null) {
                        output1.writeObject(op1);
                        output1.flush();
                        System.out.println("Resolvido em node1");
                        break;
                    } else {
                        System.out.println("Node1 não sabe servolver! ");
                        System.out.println("Enviado a outro node diferente ");
                        output3.writeObject(op2);
                        output3.flush();

                        System.out.println("Resolvido em node3");
                        break;
                    }
                }
                case 1: {
                    System.out.println("Enviando ao node2");
                    if (op1 != null) {

                        output2.writeObject(op1);
                        output2.flush();
                        System.out.println("Resolvido em node2");
                        break;
                    } else {
                        System.out.println("Node2 não sabe servolver! ");
                        System.out.println("Escolha outro node diferente");
                        output3.writeObject(op2);
                        output3.flush();

                        break;
                    }
                }
                case 2: {
                    System.out.println("Enviando a node3");
                    if (op2 != null) {
                        output3.writeObject(op2);
                        output3.flush();
                        System.out.println("Resovido em node3");
                        break;
                    } else {
                        System.out.println("Node3 não sabe resolver");
                        System.out.println("Selecionando node1 ou node2 para resolver");
                        if (opEnviar == 0) {
                            System.out.println("Enviando para node1");
                            output1.writeObject(op1);
                            output1.flush();
                            System.out.println("Resolvido em node1");
                        } else {
                            System.out.println("Enviando para node2");
                            output2.writeObject(op1);
                            output2.flush();
                            System.out.println("Resolvido em node2");
                        }
                        break;
                    }

                }
            }

            Scanner s = new Scanner(System.in);
            System.out.println("------------ \n Menu \n 1 para execultar novamente; \n 0 para encerra. ");
            System.out.print("Digite: ");
            escolha = s.nextInt();
            System.out.println("__________________");

        }

        output1.close();
        output2.close();
        output3.close();

        node1.close();
        node2.close();
        node3.close();

    }

}
