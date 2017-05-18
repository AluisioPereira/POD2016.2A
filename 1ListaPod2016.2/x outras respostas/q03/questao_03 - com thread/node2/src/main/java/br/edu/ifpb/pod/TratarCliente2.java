/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluisiopereira
 */
public class TratarCliente2 implements Runnable {

    private ArrayList<Integer> cliente;
    private Servidor2 servidor;

    public TratarCliente2(ArrayList<Integer> cliente, Servidor2 servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public TratarCliente2() {

    }

    @Override
    public void run() {
        try {
            if (cliente.get(0) == 2) {
                servidor.enviarNode3(cliente);
            }
            if (cliente.get(0) == 1) {
                servidor.enviarNode4(cliente);
            }
        } catch (IOException ex) {
            Logger.getLogger(TratarCliente2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Finalizando operação em node2");

    }

}
