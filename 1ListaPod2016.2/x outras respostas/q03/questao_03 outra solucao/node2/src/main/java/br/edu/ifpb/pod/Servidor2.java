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
import java.util.List;

/**
 *
 * @author aluisiopereira
 */
public class Servidor2 {

    private int porta;
    private List<ObjectOutputStream> clientes;

    public Servidor2(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<ObjectOutputStream>();
    }

    public static void main(String[] args) throws IOException {
        new Servidor2(1099).executar();
    }

    void enviarNode3(ArrayList<Integer> al) throws IOException {
        //abrindo comunicação com node3
        Socket node3 = new Socket("localhost", 1088);
        //enviando o objeto
        ObjectOutputStream output3 = new ObjectOutputStream(node3.getOutputStream());
        output3.writeObject(al);
        output3.flush();
    }

    void enviarNode4(ArrayList<Integer> al) throws IOException {
        Socket node4 = new Socket("localhost", 1234);
        ObjectOutputStream output4 = new ObjectOutputStream(node4.getOutputStream());
        output4.writeObject(al);
        output4.flush();
    }

    private void executar() throws IOException {
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Aguardando cliente...");
        while (true) {
            // aceita um cliente
            Socket cliente = servidor.accept();
            // adiciona saida do cliente à lista
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            this.clientes.add(oos);

            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

            // cria tratador de cliente numa nova thread
            TratarCliente2 tc = new TratarCliente2(ois, this);
            new Thread(tc).start();
        }

    }

}
