package br.edu.ifpb.pod;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ajp
 */
public class MainC {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname", "192.168.1.103");
        Account account = new AccountImplC();
        Registry registry = LocateRegistry.createRegistry(10999);
        registry.bind("Account", account);
        System.out.println("AccountC - Transação Liberada");
    }

}
