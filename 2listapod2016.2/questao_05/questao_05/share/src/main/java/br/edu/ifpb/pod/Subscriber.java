package br.edu.ifpb.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ajp
 */
public interface Subscriber extends Remote {

    void update(Message msg) throws RemoteException;
}
