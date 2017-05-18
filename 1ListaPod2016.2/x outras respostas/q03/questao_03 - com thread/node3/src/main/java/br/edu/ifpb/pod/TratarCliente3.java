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
public class TratarCliente3 implements Runnable {

    private ArrayList<Integer> cliente;
    private Servidor3 servidor;

    public TratarCliente3(ArrayList<Integer> cliente, Servidor3 servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public TratarCliente3() {

    }

    @Override
    public void run() {
        System.out.println("Chegado algo...");
        try {            
            if (cliente.get(0) == 2) {
                servidor.enviarNode2(cliente);
            }
            if (cliente.get(0) == 1) {
                servidor.enviarNode4(cliente);
            }
        } catch (IOException ex) {
            Logger.getLogger(TratarCliente3.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Finalizando operação em node3");

    }

}
