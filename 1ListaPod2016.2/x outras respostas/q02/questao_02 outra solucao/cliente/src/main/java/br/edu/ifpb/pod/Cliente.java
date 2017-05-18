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

/**
 *
 * @author aluisiopereira
 */
public class Cliente {

    public static void main(String[] args) throws IOException {

        Random r = new Random();
        int x = r.nextInt(100);
        int y = r.nextInt(100);

        Op1 op1 = null;
        Op2 op2 = null;

        Random i = new Random();
        int opEnviar = i.nextInt(1);
        int nodeEnviar = i.nextInt(2);

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

        switch (opEnviar) {
            case 0: {
                op1 = new Op1(x, y);
                break;
            }
            case 1: {
                op2 = new Op2(x, y);
                break;
            }
        }

        switch (nodeEnviar) {
            case 0: {
                if (op1 != null) {
                    output1.writeObject(op1);
                    output1.flush();

                    output1.close();

                    break;

                } else {
                    output1.writeObject(op2);
                    output1.flush();

                    output1.close();

                    output2.close();
                    break;
                }
            }
            case 1: {
                if (op1 != null) {
                    output2.writeObject(op1);
                    output2.flush();
                    output2.close();
                    break;
                } else {
                    output2.writeObject(op2);
                    output2.flush();
                    int r1 = node2.getInputStream().read();
                    if (r1 == 0) {
                        nodeEnviar = 2;
                    }
                    output2.close();
                }
            }
            case 2: {

                if (op2 != null) {
                    output3.writeObject(op2);
                    output3.flush();
                    output3.close();
                    break;
                } else {
                    output3.writeObject(op1);
                    output3.flush();

                    output1.close();

                    output2.close();

                    output3.close();

                    break;
                }

            }
        }

        node1.close();
        node2.close();
        node3.close();
    }

}
