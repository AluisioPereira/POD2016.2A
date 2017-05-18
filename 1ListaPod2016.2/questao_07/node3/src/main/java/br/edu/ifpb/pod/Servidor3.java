/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ajp
 */
public class Servidor3 {

    private static ServerSocket servidor3 = null;
    private static Socket node3 = null;
    private static ObjectInputStream input3 = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException, SQLException, InterruptedException {
        while (true) {
            System.out.println("Aguardando node2...");
            // abrindo a porta de conexão.
            servidor3 = new ServerSocket(1234);
            //se conectando ao cliente (node2)
            node3 = servidor3.accept();

            //lendo objeto recebido
            input3 = new ObjectInputStream(node3.getInputStream());

            //convertendo em objeto numero
            Envio envio3 = (Envio) input3.readObject();

            boolean retornando = respostaANode2(envio3.getQtd(), envio3);
            //fechando conexões
            input3.close();
            servidor3.close();
            node3.close();
        }

    }

    public static synchronized boolean respostaANode2(int qtd, Envio envio3) throws IOException, URISyntaxException, ClassNotFoundException, SQLException, InterruptedException {

        Thread t = new Thread() {
            @Override
            public void run() {
                try {

                    double resposta = (int) (Math.pow(envio3.getNum1(), envio3.getNum2()) + Math.pow(envio3.getNum2(), envio3.getNum1()));

                    System.out.println("Sabendo que f(x, y) = x^y+y^x e sabendo que x = " + envio3.getNum1() + " y = " + envio3.getNum2() + " temos:");

                    System.out.println("f(" + envio3.getNum1() + "," + envio3.getNum2() + ") = " + resposta);
                    System.out.println("f(" + envio3.getNum1() + "," + envio3.getNum2() + ") = " + (Math.pow(envio3.getNum1(), envio3.getNum2()) + Math.pow(envio3.getNum2(), envio3.getNum1())));

                    node3.getOutputStream().write((int) resposta);
                    System.out.println("Resposta enviada a node2 com sucesso.");
                    System.out.println("................................");
                    input3.close();
                    node3.close();
                    servidor3.close();
                } catch (IOException ex) {
                    Logger.getLogger(Servidor3.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    int l = 50 / qtd;
                    Thread.sleep(l);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Servidor3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        ;
        };
        t.start();
        int l = 100 / qtd;
        System.out.println("Tempo de retorno " + l + " milisegundos");
        t.sleep(l);
        t.interrupt();
        return true;

    }

}
