package br.edu.ifpb.pod;

import br.edu.ifpb.pod.pubsub.Grupo;
import br.edu.ifpb.pod.pubsub.GrupoManager;
import br.edu.ifpb.pod.pubsub.GrupoManagerImpl;
import br.edu.ifpb.pod.pubsub.Message;
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
        final GrupoManager manager = new GrupoManagerImpl();
        Registry registry = LocateRegistry.createRegistry(10999);
        registry.bind("__ChatServer__", manager);
        
        Grupo g = new Grupo(1, "Grupo1");
        Message m = new Message();
        m.setIdGrupo(g.getDescricao());
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                manager.notifySubscribers(m);
            }
        }, 1000, 10000);
    }
}
