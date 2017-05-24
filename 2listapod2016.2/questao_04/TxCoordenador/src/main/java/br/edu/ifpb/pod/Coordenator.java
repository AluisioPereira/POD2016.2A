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
public class Coordenator {

    private static final int porta = 10998;
    private static final String ipA = "192.168.1.101";
    private static final String ipB = "192.168.1.102";
    private static final String ipC = "192.168.1.103";

    public static TxAccount recuperarTxAccountA() throws AccessException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(ipA, porta);
        return (TxAccount) registry.lookup("TxServico");
    }

    public static TxAccount recuperarTxAccountB() throws AccessException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(ipB, porta);
        return (TxAccount) registry.lookup("TxServico");
    }

    public static TxAccount recuperarTxAccountC() throws AccessException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(ipC, porta);
        return (TxAccount) registry.lookup("TxServico");
    }

    public static void iniciarTxServico(TxCoordenatorAll txCoordAll) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(10003);
        registry.bind("TxCoordenator", txCoordAll);
    }

    public static void main(String[] args) {

        try {

            System.setProperty("java.rmi.server.hostname", "192.168.1.104");
            TxAccount txA = recuperarTxAccountA();
            TxAccount txB = recuperarTxAccountB();
            TxAccount txC = recuperarTxAccountC();
            TxCoordenatorAll coordenatorAll = new TxCoordenatorAllImpl(txA, txB, txC);
            iniciarTxServico(coordenatorAll);
        } catch (Exception e) {
            System.err.println("TxCoordenador - não foi possível estabelecer comunicação.");
            e.printStackTrace();
        }

    }
}
