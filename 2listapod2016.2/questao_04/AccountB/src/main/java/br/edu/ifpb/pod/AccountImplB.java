package br.edu.ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ajp
 */
public class AccountImplB extends UnicastRemoteObject implements Account, TxAccount {

    private float transBalance = 0f;
    private float currentBalance = 10000f;
    private boolean inTransactio = false;

    public AccountImplB() throws RemoteException {
        super();
    }

    @Override
    public void prepare() throws RemoteException {
        transBalance = currentBalance;
        inTransactio = true;
    }

    @Override
    public void commit() throws RemoteException {
        if (inTransactio) {
            currentBalance = transBalance;
            inTransactio = false;
            transBalance = 0f;
        }
    }

    @Override
    public void rollback() throws RemoteException {
        if (inTransactio) {
            transBalance = 0f;
            inTransactio = false;
        }
    }

    @Override
    public void debit(float value) throws RemoteException {
        if (inTransactio) {
            transBalance -= value;
        } else {
            throw new RemoteException("Transações não preparada.");
        }
    }

    @Override
    public void credit(float value) throws RemoteException {
        if (inTransactio) {
            transBalance += value;
        } else {
            throw new RemoteException("Transações não realizada.");
        }
    }

    @Override
    public float print() throws RemoteException {
        System.out.println("Saldo corrente: " + currentBalance);
        return currentBalance;
    }

}
