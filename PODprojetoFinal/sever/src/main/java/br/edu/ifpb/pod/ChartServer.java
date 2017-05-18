package br.edu.ifpb.pod;

import br.edu.ifpb.pod.pubsub.Message;
import br.edu.ifpb.pod.pubsub.Subscriber;
import br.edu.ifpb.pod.pubsub.GrupoManagerImpl;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.edu.ifpb.pod.pubsub.GrupoManager;

/**
 *
 * @author ajp
 */
public class ChartServer implements GrupoManager {

    @Override
    public void notifySubscribers(Message m) {
        System.out.println("Notificação!");

    }

    @Override
    public void publish(Message message) throws RemoteException {
        System.out.println("Messagem: " + message);
    }

    @Override
    public void register(String uuid, Subscriber sub) throws RemoteException, AccessException {
        final GrupoManager manager = new GrupoManagerImpl();
        Registry registry = LocateRegistry.createRegistry(10999);
        try {
            registry.bind("__ChatServer__", manager);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ChartServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Message> poll(String uuid) throws RemoteException {
        throw new UnsupportedOperationException("lista de menssagem!");
    }

}
