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
import java.util.ArrayList;

/**
 *
 * @author aluisiopereira
 */
public class Servidor4 {

    private int porta;
    private ArrayList<Integer> clientes;

    public Servidor4(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<Integer>();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Servidor4(1234).executar();
    }

    private void executar() throws IOException, ClassNotFoundException {
        ServerSocket servidor = new ServerSocket(this.porta);
        while (true) {

            System.out.println("Aguardando cliente...");
            // aceita um cliente
            Socket cliente = servidor.accept();

            // adiciona saida do cliente à lista
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

            ArrayList<Integer> ai = (ArrayList<Integer>) ois.readObject();
            int op = ai.get(0);
            int re = ai.get(1);

            System.out.println("operação: " + op + "resultado: " + re);

            this.clientes.add(op);
            this.clientes.add(re);

            // cria tratador de cliente numa nova thread
            TratarCliente4 tc = new TratarCliente4(clientes, this);
            new Thread(tc).start();
        }

    }

}
