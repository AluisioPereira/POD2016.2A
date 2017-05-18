/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ajp
 */
public class Cliente1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException, SQLException, InterruptedException {

        int escolha = 1;

        while (escolha == 1) {

            System.out.println("------------ \n Menu de comunicação a node2 \n 0 - não enviar nada. \n 1 - enviar números (x e y) a nod2; ");

            System.out.print("Digite: ");
            Scanner s = new Scanner(System.in);
            int opEscolha = s.nextInt();

            switch (opEscolha) {
                case 0: {
                    break;
                }
                case 1: {

                    int opcao2 = 1;
                    int opcao3 = 1;
                    while (opcao2 >= 1 && opcao2 <= 20) {
                        System.out.print("Informe quantas requisições você gostaria de enviado a node2 (mín.: 1 e máx.: 20) \nQuantidade: ");
                        opcao2 = s.nextInt();
                        if (opcao2 >= 1 && opcao2 <= 20) {
                            opcao3 = 1;
                            System.out.println("Node2 será notificado " + opcao2 + " veze(s) em no máximo " + opcao3 + " segundo.");
                            boolean enviadoANode2 = EnviandoANod2(opcao2, opcao3);
                            if (enviadoANode2) {
                                System.out.println("Quantidade de envios encerradas.");
                                break;
                            }
                        } else {

                        }
                    }

                }
            }
            System.out.println("\n------------ \n Menu \n 0 - para encerra. \n 1 - para notificar node1 novamente; ");

            System.out.print("Digite: ");
            escolha = s.nextInt();
            System.out.println("__________________");

        }
    }

    public static synchronized boolean EnviandoANod2(int qtd, int tempo) throws IOException, URISyntaxException, ClassNotFoundException, SQLException, InterruptedException {

        //criando numeros aleatorios entre 0 e 100
        Random r = new Random();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < qtd; i++) {
                    try {
                        int num1 = r.nextInt(100);
                        int num2 = r.nextInt(100);
                        //atribuindo os aleatorios a um objetos chamando numeros 
                        Envio envia = new Envio();
                        envia.setNum1(num1);
                        envia.setNum2(num2);
                        envia.setQtd(qtd);

                        //abrindo comunicação com node2
                        Socket node1 = new Socket("localhost", 1099);
                        //enviando o objeto
                        ObjectOutputStream output1 = new ObjectOutputStream(node1.getOutputStream());
                        output1.writeObject(envia);
                        output1.flush();

                        //retorno do node2
                        int retorno1 = node1.getInputStream().read();
                        System.out.println("f(" + envia.getNum1() + "," + envia.getNum2() + ") = " + retorno1);
                        if (retorno1 != 0) {
                            System.out.println((i + 1) + " Envio realizada com sucesso.");
                            System.out.println("................................");
                        } else if (retorno1 == 0) {
                            System.out.println((i + 1) + " Envio não realizada.");
                            System.out.println("Retorno de node2 foi: " + retorno1 + " (zero)");
                        }

                        System.out.println("Enviando as requsições a node2.");
                        System.out.println("Esperando a entrega de n.º: " + (i + 1));

                        output1.close();
                        node1.close();
                        System.out.println("Encerrando atividades");

                    } catch (IOException ex) {
                        Logger.getLogger(Cliente1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        ;
        };
        t1.start();
        int l = 1000 / qtd;
        t1.sleep(l);
        t1.destroy();
        System.out.println("Retornando..");
        return true;
    }

}
