package br.edu.ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ajp
 */
public class PessoaImplMysql extends UnicastRemoteObject implements InterfaceMysql {

    private DaoPessoaMysql daoPessoa;

    public PessoaImplMysql() throws RemoteException {
        super();
    }

    @Override
    public boolean add(Pessoa p) throws RemoteException {
        try {
            this.daoPessoa = new DaoPessoaMysql();
            return daoPessoa.addPessoa(p);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Pessoa buscarNome(String nome) throws RemoteException {
        try {
            this.daoPessoa = new DaoPessoaMysql();
            return daoPessoa.buscarNome(nome);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean remove(Pessoa p) throws RemoteException {
        try {
            this.daoPessoa = new DaoPessoaMysql();
            return daoPessoa.removerPessoa(p);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
