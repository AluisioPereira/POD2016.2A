package br.edu.ifpb.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ajp
 */
public interface Topic extends Remote {

    void register(String uuid, Subscriber sub) throws RemoteException;
}
