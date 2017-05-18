package br.edu.ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ajp
 */
public class TxCoordenatorAllImpl extends UnicastRemoteObject implements TxCoordenatorAll {

    private static Registry registry = null;
    private static TxAccount transfer = null;
    private static TxCoordenatorAll coordenatorAll = null;

    public TxCoordenatorAllImpl() throws RemoteException {
        super();
    }

    @Override
    public void prepareAll() throws RemoteException {
        try {

            registry = LocateRegistry.getRegistry("localhost", 10991);

            coordenatorAll = (TxCoordenatorAll) registry.lookup("TxCoordenator");
            System.out.println("TxCoordenator - Operação (prepareAll) a ser realizado.");
            coordenatorAll.prepareAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void commitAll() throws RemoteException {
        try {
            registry = LocateRegistry.getRegistry("localhost", 10991);

            coordenatorAll = (TxCoordenatorAll) registry.lookup("TxCoordenator");
            System.out.println("TxCoordenator - Operação (commitAll) a ser realizado.");
            coordenatorAll.commitAll();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void rollbackAll() throws RemoteException {
        try {

            registry = LocateRegistry.getRegistry("localhost", 10991);

            coordenatorAll = (TxCoordenatorAll) registry.lookup("TxCoordenator");
            System.out.println("TxCoordenator - Operação (rollbackAll) a ser realizado.");
            coordenatorAll.rollbackAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
