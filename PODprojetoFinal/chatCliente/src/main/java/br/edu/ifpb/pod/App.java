package br.edu.ifpb.pod;

import br.edu.ifpb.pod.pubsub.Message;
import br.edu.ifpb.pod.pubsub.Publisher;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import br.edu.ifpb.pod.pubsub.Grupo;

/**
 *
 * @author ajp
 */
public class App {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        String uuid = "aluisio1102@gmail.com";
        Registry registry = LocateRegistry.getRegistry(10999);
        Grupo grupo = (Grupo) registry.lookup("__ChatServer__");
        Publisher publisher = (Publisher) registry.lookup("__ChatServer__");
        ChatClientImpl client = new ChatClientImpl(publisher);
        grupo.register(uuid, client);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();
            Message message = new Message();
            message.setFrom(uuid);
            message.setText(text);
            client.sendMessage(message);
        }
    }

}
