package br.edu.ifpb.pod.pubsub;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ajp
 */
public interface Polling extends Remote {

    List<Message> poll(String uuid) throws RemoteException;
}
