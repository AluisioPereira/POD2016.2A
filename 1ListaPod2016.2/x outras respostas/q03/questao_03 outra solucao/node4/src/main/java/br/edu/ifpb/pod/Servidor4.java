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
public class Servidor4 {

    private int porta;
    private List<ObjectOutputStream> clientes;

    public Servidor4(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<ObjectOutputStream>();
    }

    public static void main(String[] args) throws IOException {
        new Servidor4(1234).executar();
    }
  

    private void executar() throws IOException {
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Entrega no loop.");
        while (true) {
            // aceita um cliente
            Socket cliente = servidor.accept();
            // adiciona saida do cliente à lista
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            this.clientes.add(oos);

            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

            // cria tratador de cliente numa nova thread
            TratarCliente4 tc = new TratarCliente4(ois, this);
            new Thread(tc).start();
        }

    }

}
