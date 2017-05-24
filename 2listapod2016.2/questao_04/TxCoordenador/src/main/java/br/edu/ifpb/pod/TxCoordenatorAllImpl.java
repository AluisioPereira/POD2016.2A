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

    private TxAccount txAccountA;
    private TxAccount txAccountB;
    private TxAccount txAccountC;

    public TxCoordenatorAllImpl() throws RemoteException {
        super();
    }

    public TxCoordenatorAllImpl(TxAccount txa, TxAccount txb, TxAccount txc) throws RemoteException {
        txAccountA = txa;
        txAccountB = txb;
        txAccountC = txc;
    }

    @Override
    public void prepareAll() throws RemoteException {
        txAccountA.prepare();
        txAccountB.prepare();
        txAccountC.prepare();
    }

    @Override
    public void commitAll() throws RemoteException {
        txAccountA.commit();
        txAccountB.commit();
        txAccountC.commit();
    }

    @Override
    public void rollbackAll() throws RemoteException {
        txAccountA.rollback();
        txAccountB.rollback();
        txAccountC.rollback();
    }

}
