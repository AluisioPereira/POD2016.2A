package br.edu.ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ajp
 */
public class PessoaImplPostgres extends UnicastRemoteObject implements InterfacePostgres {
    
    private DaoPessoaPostgres daoPessoa;
    
    public PessoaImplPostgres() throws RemoteException{
        super();
    }

    @Override
    public boolean add(Pessoa p) throws RemoteException {
        try {
            this.daoPessoa = new DaoPessoaPostgres();
            return daoPessoa.addPessoa(p);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Pessoa buscarNome(String nome) throws RemoteException {
        try {
            this.daoPessoa = new DaoPessoaPostgres();
            return daoPessoa.buscarNome(nome);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean remove(Pessoa p) throws RemoteException {
        try {
            this.daoPessoa = new DaoPessoaPostgres();
            return daoPessoa.removerPessoa(p);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
