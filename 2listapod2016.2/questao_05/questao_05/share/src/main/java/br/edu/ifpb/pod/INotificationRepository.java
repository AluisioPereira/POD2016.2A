/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import br.edu.ifpb.pod.Notification;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ajp
 */
public interface INotificationRepository extends Remote {

    void store(Notification n) throws RemoteException;

    ArrayList<Notification> listaNotifications(String uuid) throws RemoteException;

    void removeNotification(String uuid) throws RemoteException;
}
