package br.edu.ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author ajp
 */
public class TopicManagerImpl extends UnicastRemoteObject implements TopicManager, INotificationRepository, ISubscriberRepository {

    private DaoNotificationRepository daoNotification;
    private DaoSubscriberRepository daoSubscriber;

    public TopicManagerImpl() throws RemoteException {
        super();
    }

    public void notifySubscribers() {

    }

    @Override
    public void store(Notification n) throws RemoteException {
        try {
            this.daoNotification = new DaoNotificationRepository();
            daoNotification.store(n);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Notification> listaNotifications(String uuid) throws RemoteException {
        try {
            this.daoNotification = new DaoNotificationRepository();
            return daoNotification.listNotifications(uuid);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeNotification(String uuid) throws RemoteException {
        try {
            this.daoNotification = new DaoNotificationRepository();
            daoNotification.removerNotifications(uuid);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void store(String uuid, Subscriber sub) throws RemoteException {
        try {
            this.daoSubscriber = new DaoSubscriberRepository();
            daoSubscriber.store(uuid, sub);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Subscriber find(String uuid) throws RemoteException {
        try {
            this.daoSubscriber = new DaoSubscriberRepository();
            return daoSubscriber.find(uuid);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<String> listUUIDs() throws RemoteException {
        try {
            this.daoSubscriber = new DaoSubscriberRepository();
            return daoSubscriber.listUUIDs();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void publish(Message message) throws RemoteException {

    }

    @Override
    public void register(String uuid, Subscriber sub) throws RemoteException {
    }

}
