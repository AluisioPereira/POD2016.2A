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
public class Servidor3 {

    private int porta;
    private ArrayList<Integer> clientes;

    public Servidor3(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<Integer>();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while (true) {
            new Servidor3(1088).executar();
        }
    }

    void enviarNode2(ArrayList<Integer> al) throws IOException {
        //abrindo comunicação com node3   
        System.out.println("Op2=diff(x,y)\nEncaminhando a node2.");
        Socket node2 = new Socket("localhost", 1099);
        System.out.println("Aberta conexão com node2");
        //enviando o objeto
        ObjectOutputStream output2 = new ObjectOutputStream(node2.getOutputStream());
        output2.writeObject(al);
        System.out.println("Enviado a node2");
        output2.flush();
    }

    void enviarNode4(ArrayList<Integer> al) throws IOException {
        System.out.println("Op1=sum(x,y)\nEncaminhando a node4.");
        Socket node4 = new Socket("localhost", 1234);
        System.out.println("Aberta conexão com node4");
        ObjectOutputStream output4 = new ObjectOutputStream(node4.getOutputStream());
        output4.writeObject(al);
        output4.flush();
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
            TratarCliente3 tc = new TratarCliente3(clientes, this);
            new Thread(tc).start();
        }

    }

}
