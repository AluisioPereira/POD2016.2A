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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ajp
 */
public class Servidor2 {

    private static ServerSocket servidor1;
    private static Socket node2;
    private static ObjectInputStream input2;

    private static Socket node3;
    private static ObjectOutputStream output3;

    private static Envio envia2;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        while (true) {
            System.out.println("Aguardando node1...");
            // abrindo a porta de conexão.
            servidor1 = new ServerSocket(1099);
            //se conectando ao cliente (node1)
            node2 = servidor1.accept();

            //lendo objeto recebido
            input2 = new ObjectInputStream(node2.getInputStream());

            //convertendo em objeto numero
            envia2 = (Envio) input2.readObject();

            if (envia2.getNum1() == envia2.getNum2()) {
                node2.getOutputStream().write(0);
                System.out.println("Retornando 0 (zero) a node1.");
            } else {

                Thread t02 = new Thread() {
                    boolean enviadoNode3;
                    int retorno3;

                    public void run() {
                        try {
                            enviadoNode3 = enviarNode3(envia2);
                        } catch (IOException ex) {
                            Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (enviadoNode3) {
                            try {
                                //retorno do node3
                                retorno3 = node3.getInputStream().read();
                            } catch (IOException ex) {
                                Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("Retornando resposta a node1 " + retorno3);
                            try {
                                node2.getOutputStream().write(retorno3);
                            } catch (IOException ex) {
                                Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                //fechando comunicação node3.
                                output3.close();
                            } catch (IOException ex) {
                                Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                node3.close();
                            } catch (IOException ex) {
                                Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                ;
                };
                            t02.start();
                System.out.println("Retornando..");

            }
            input2.close();
            servidor1.close();
        }

    }

    public static boolean enviarNode3(Envio envia2) throws IOException {
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    //abrindo comunicação com node3
                    node3 = new Socket("localhost", 1234);
                } catch (IOException ex) {
                    Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    //enviando o objeto a node3
                    output3 = new ObjectOutputStream(node3.getOutputStream());
                } catch (IOException ex) {
                    Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Encaminhado a node3.");
                try {
                    output3.writeObject(envia2);
                } catch (IOException ex) {
                    Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    output3.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        ;
        };
        t2.start();
        System.out.println("Retornando a nod1..");
        return true;

    }
}
