package br.edu.ifpb.pod.pubsub;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ajp
 */
public interface Subscriber extends Remote {

    void update() throws RemoteException;
}
