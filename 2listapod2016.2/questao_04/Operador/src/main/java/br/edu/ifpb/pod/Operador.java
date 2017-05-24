/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ajp
 */
public class Operador {

    private static Registry registry = null;
    private static Operacao transfer = null;
    private static TxCoordenatorAll coordenatorAll = null;

    public static synchronized boolean transfer() {
        try {

            registry = LocateRegistry.getRegistry("localhost", 10999);

            transfer = (Operacao) registry.lookup("Operador");
            System.out.println("Operador - Operação a ser realizado.");
            transfer.transfer();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean prepareAll() {
        try {

            registry = LocateRegistry.getRegistry("localhost", 10991);

            coordenatorAll = (TxCoordenatorAll) registry.lookup("TxCoordenator");
            System.out.println("TxCoordenator - Operação (prepareAll) a ser realizado.");
            coordenatorAll.prepareAll();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean commitAll() {
        try {

            registry = LocateRegistry.getRegistry("localhost", 10991);

            coordenatorAll = (TxCoordenatorAll) registry.lookup("TxCoordenator");
            System.out.println("TxCoordenator - Operação (commitAll) a ser realizado.");
            coordenatorAll.commitAll();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean rollbackAll() {
        try {

            registry = LocateRegistry.getRegistry("localhost", 10991);

            coordenatorAll = (TxCoordenatorAll) registry.lookup("TxCoordenator");
            System.out.println("TxCoordenator - Operação (rollbackAll) a ser realizado.");
            coordenatorAll.rollbackAll();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
