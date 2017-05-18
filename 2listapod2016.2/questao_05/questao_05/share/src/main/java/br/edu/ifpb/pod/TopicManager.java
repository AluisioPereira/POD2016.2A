package br.edu.ifpb.pod;

import java.rmi.RemoteException;

/**
 *
 * @author ajp
 */
interface TopicManager extends Publisher, Topic {

    void notifySubscribers() throws RemoteException;
}
