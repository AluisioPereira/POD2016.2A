package br.edu.ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ajp
 */
public class OperacaoImpl extends UnicastRemoteObject implements Operacao {

    public OperacaoImpl() throws RemoteException {
        super();
    }

    @Override
    public void transfer() throws RemoteException {
    }

}
