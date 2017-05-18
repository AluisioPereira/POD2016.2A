/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluisiopereira
 */
public class TratarCliente3 implements Runnable {

    private ObjectInputStream cliente;
    private Servidor3 servidor;

    public TratarCliente3(ObjectInputStream cliente, Servidor3 servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public TratarCliente3() {

    }

    @Override
    public void run() {
        try {
            ArrayList<Integer> al = (ArrayList) cliente.readObject();
            if (al.get(0) == 1) {
                servidor.enviarNode2(al);
            }
            if (al.get(1) == 2) {
                servidor.enviarNode4(al);
            }
        } catch (IOException ex) {
            Logger.getLogger(TratarCliente3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TratarCliente3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
