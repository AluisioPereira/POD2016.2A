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
public class TratarCliente4 implements Runnable {

    private ObjectInputStream cliente;
    private Servidor4 servidor;

    public TratarCliente4(ObjectInputStream cliente, Servidor4 servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public TratarCliente4() {

    }

    @Override
    public void run() {
        try {
            ArrayList<Integer> al = (ArrayList) cliente.readObject();
            if (al.get(0) == 1) {
                System.out.println("Op1 = sum(x,y) \n Op1 = " + al.get(2));
            }
            if (al.get(1) == 2) {
                System.out.println("Op2 = diff(x,y) \n Op2 = " + al.get(2));
            }
        } catch (IOException ex) {
            Logger.getLogger(TratarCliente4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TratarCliente4.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
