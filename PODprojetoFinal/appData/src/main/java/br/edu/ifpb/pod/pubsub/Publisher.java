package br.edu.ifpb.pod.pubsub;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ajp
 */
public interface Publisher extends Remote {

    void publish(Message message) throws RemoteException;
}
