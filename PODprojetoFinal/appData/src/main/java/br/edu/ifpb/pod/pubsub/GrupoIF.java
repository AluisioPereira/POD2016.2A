package br.edu.ifpb.pod.pubsub;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ajp
 */
public interface GrupoIF extends Remote {

    void register(String uuid, Subscriber sub) throws RemoteException;
}
