/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ajp
 */
public class AccountMagager {

    private static final int porta = 10999;
    private static final String ipA = "192.168.1.101";
    private static final String ipB = "192.168.1.102";
    private static final String ipC = "192.168.1.103";

    private static Account registraA() throws AccessException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(ipA, porta);
        return (Account) registry.lookup("Account");
    }

    private static Account registraB() throws AccessException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(ipB, porta);
        return (Account) registry.lookup("Account");
    }

    private static Account registraC() throws AccessException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(ipC, porta);
        return (Account) registry.lookup("Account");
    }

    private static TxCoordenatorAll registraTxCoordAll() throws AccessException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("192.168.1.104", 10991);
        return (TxCoordenatorAll) registry.lookup("TxCoordenator");
    }

    public static void iniciaOperacao(Operacao o) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(10992);
        registry.bind("Operacao", o);
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        try {

            TxCoordenatorAll txAll = registraTxCoordAll();
            Account a = registraA();
            Account b = registraB();
            Account c = registraC();
            Operacao transfer = new OperacaoImpl(txAll, a, b, c);
            System.setProperty("java.rmi.server.hostname", "192.168.1.104");
            iniciaOperacao(transfer);
            System.out.println("AccountManager - pronto para ouvir...");

        } catch (Exception e) {
            System.err.println("AccountManager - não foi possível estabelecer comunicação.");
            e.printStackTrace();
        }

    }
}
