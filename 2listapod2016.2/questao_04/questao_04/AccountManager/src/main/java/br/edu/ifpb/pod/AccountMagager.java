/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ajp
 */
public class AccountMagager {

    private static Registry registry = null;
    private static Account account = null;

    public static synchronized boolean debit(float value) {
        try {
            registry = LocateRegistry.getRegistry("localhost", 10999);
            account = (Account) registry.lookup("Account");
            System.out.println("Debit - Operação a ser realizado.");
            account.debit(value);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean credit(float value) {
        try {
            registry = LocateRegistry.getRegistry("localhost", 10999);
            account = (Account) registry.lookup("Account");
            System.out.println("Credit - Operação a ser realizado.");
            account.credit(value);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static synchronized void print() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 10999);
            account = (Account) registry.lookup("Account");
            System.out.println("Print - Operação a ser realizado.");
            account.print();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        try {

            System.setProperty("java.rmi.server.hostname", "192.168.1.106");
            Operacao transfer = new OperacaoImpl();

            Registry registry = LocateRegistry.createRegistry(10999);
            System.out.println("Registrando...");
            registry.bind("Operador", transfer);
            System.out.println("AccountManager - pronto para ouvir...");

        } catch (Exception e) {
            System.err.println("AccountManager - não foi possível estabelecer comunicação.");
            e.printStackTrace();
        }

    }
}
