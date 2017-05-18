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
public class Coordenator {

    private static Registry registry = null;
    private static TxAccount accountabc = null;

    public static synchronized boolean prepare() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 10999);
            accountabc = (TxAccount) registry.lookup("Account");
            System.out.println("prepare - Operação a ser realizado.");
            accountabc.prepare();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean commit() {
        try {

            registry = LocateRegistry.getRegistry("localhost", 10999);

            accountabc = (TxAccount) registry.lookup("Account");
            System.out.println("commit - Operação a ser realizado.");
            accountabc.commit();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean rollback() {
        try {

            registry = LocateRegistry.getRegistry("localhost", 10999);

            accountabc = (TxAccount) registry.lookup("Account");
            System.out.println("rollback - Operação a ser realizado.");
            accountabc.rollback();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {

        try {

            System.setProperty("java.rmi.server.hostname", "192.168.1.106");
            TxCoordenatorAll coordenatorAll = new TxCoordenatorAllImpl();

            Registry registry = LocateRegistry.createRegistry(10991);
            System.out.println("Registrando...");
            registry.bind("TxCoordenator", coordenatorAll);
            System.out.println("TxCoordenador - pronto para ouvir...");

        } catch (Exception e) {
            System.err.println("TxCoordenador - não foi possível estabelecer comunicação.");
            e.printStackTrace();
        }

    }
}
