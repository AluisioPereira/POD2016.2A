package br.edu.ifpb.pod;

import br.edu.ifpb.pod.pubsub.Message;
import br.edu.ifpb.pod.pubsub.Subscriber;
import java.rmi.RemoteException;

/**
 *
 * @author ajp
 */
public interface ChatClient extends Subscriber {

    public void sendMessage(Message message) throws RemoteException;
}
