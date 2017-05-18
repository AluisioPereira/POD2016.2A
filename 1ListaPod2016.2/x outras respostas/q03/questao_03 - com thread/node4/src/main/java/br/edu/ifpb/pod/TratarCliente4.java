/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.util.ArrayList;

/**
 *
 * @author aluisiopereira
 */
public class TratarCliente4 implements Runnable {

    private ArrayList<Integer> cliente;
    private Servidor4 servidor;

    public TratarCliente4(ArrayList<Integer> cliente, Servidor4 servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public TratarCliente4() {

    }

    @Override
    public void run() {
        System.out.println("Chegado algo...");
        if (cliente.get(0) == 1) {
            System.out.println("Op1=sum(x,y)\nÉ igual a é op1=" + cliente.get(1));
        }
        if (cliente.get(0) == 2) {
            System.out.println("Op2=diff(x,y)\nÉ igual a é op2=" + cliente.get(1));
        }
        System.out.println("Finalizando operação em node4");

    }

}
