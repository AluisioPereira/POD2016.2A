package br.edu.ifpb.pod;



import br.edu.ifpb.pod.pubsub.Message;
import br.edu.ifpb.pod.pubsub.Polling;
import br.edu.ifpb.pod.pubsub.Publisher;
import br.edu.ifpb.pod.pubsub.Subscriber;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


/**
 *
 * @author ajp
 */
@SuppressWarnings("serial")
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, Subscriber {

    private String subscriberUUID;
    private Publisher publisherStub;
    private Polling pollingStub;

    public ChatClientImpl(String uuid, Publisher publisher, Polling polling) throws RemoteException {
        subscriberUUID = uuid;
        publisherStub = publisher;
        pollingStub = polling;
    }

    ChatClientImpl(Publisher publisher) throws RemoteException {
        publisherStub = publisher;
    }

    //@Override
    @Override
    public void sendMessage(Message message) throws RemoteException {
        publisherStub.publish(message);
    }

    //@Override
    @Override
    public void update() throws RemoteException {
        List<Message> message = pollingStub.poll(subscriberUUID);
        for (Message msg : message) {
            System.out.println(msg.getFrom()+ ": " + msg.getText());
        }
    }

}
