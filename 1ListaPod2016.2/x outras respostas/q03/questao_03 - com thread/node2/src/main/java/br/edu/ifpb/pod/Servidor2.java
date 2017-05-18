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
import java.util.ArrayList;

/**
 *
 * @author aluisiopereira
 */
public class Servidor2 {

    private int porta;
    private ArrayList<Integer> clientes;

    private static ObjectOutputStream output3 = null;
    private static Socket node3 = null;

    private static ObjectOutputStream output4 = null;
    private static Socket node4 = null;

    public Servidor2(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<Integer>();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Servidor2(1099).executar();
    }

    void enviarNode3(ArrayList<Integer> al) throws IOException {
        //abrindo comunicação com node3
        System.out.println("Op2=diff(x,y)\nEncaminhando a node3.");
        node3 = new Socket("localhost", 1088);
        System.out.println("Aberta conexão com node3");
        //enviando o objeto
        output3 = new ObjectOutputStream(node3.getOutputStream());
        output3.writeObject(al);
        output3.flush();
    }

    void enviarNode4(ArrayList<Integer> al) throws IOException {
        System.out.println("Op1=sum(x,y)\nEncaminhando a node4.");
        node4 = new Socket("localhost", 1234);
        System.out.println("Aberta conexão com node4");
        output4 = new ObjectOutputStream(node4.getOutputStream());
        output4.writeObject(al);
        output4.flush();
        node4.close();
        output4.close();
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
            TratarCliente2 tc = new TratarCliente2(clientes, this);
            new Thread(tc).start();
        }

    }

}
