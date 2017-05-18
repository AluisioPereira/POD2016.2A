package br.edu.ifpb.pod;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ajp
 */
public class App {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        final TopicManager manager = new TopicManagerImpl();
        Registry registry = LocateRegistry.createRegistry(10999);
        registry.bind("__ChatServer__", manager);
        

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    manager.notifySubscribers();
                } catch (RemoteException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 1000, 10000);
    }
}
