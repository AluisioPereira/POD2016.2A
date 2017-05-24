package br.edu.ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ajp
 */
public class OperacaoImpl extends UnicastRemoteObject implements Operacao {

    private TxCoordenatorAll txCoordAll;
    private Account accountA;
    private Account accountB;
    private Account accountC;

    public OperacaoImpl() throws RemoteException {
        super();
    }

    public OperacaoImpl(TxCoordenatorAll tx, Account a, Account b, Account c) throws RemoteException {
        txCoordAll = tx;
        accountA = a;
        accountB = b;
        accountC = c;
    }

    @Override
    public void transfer(String EnderAccount, float valor) throws RemoteException {
        float parte = valor / 4;
        try {
            txCoordAll.prepareAll();
            if ("A".equals(EnderAccount)) {
                accountA.debit(valor);
                accountB.credit(parte);
                accountC.credit(parte);
            } else if ("B".equals(EnderAccount)) {
                accountB.debit(valor);
                accountA.credit(parte);
                accountC.credit(parte);
            } else if ("C".equals(EnderAccount)) {
                accountC.debit(valor);
                accountA.credit(parte);
                accountB.credit(parte);
            } else {
                throw new RemoteException("Conta não foi encontrada.");
            }
            txCoordAll.commitAll();
        } catch (RemoteException e) {
            txCoordAll.rollbackAll();
            throw new RemoteException(e.getMessage());
        }

    }

}
