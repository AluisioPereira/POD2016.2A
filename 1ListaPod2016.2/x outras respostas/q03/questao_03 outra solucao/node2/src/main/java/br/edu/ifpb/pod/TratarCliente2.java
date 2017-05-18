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
public class TratarCliente2 implements Runnable {

    private ObjectInputStream cliente;
    private Servidor2 servidor;

    public TratarCliente2(ObjectInputStream cliente, Servidor2 servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public TratarCliente2() {

    }

    @Override
    public void run() {
        try {
            ArrayList<Integer> al = (ArrayList) cliente.readObject();
            if (al.get(0) == 2) {
                servidor.enviarNode3(al);
            }
            if (al.get(1) == 1) {
                servidor.enviarNode4(al);
            }
        } catch (IOException ex) {
            Logger.getLogger(TratarCliente2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TratarCliente2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
