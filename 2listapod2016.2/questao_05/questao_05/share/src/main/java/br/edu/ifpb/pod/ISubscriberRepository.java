/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import br.edu.ifpb.pod.Subscriber;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ajp
 */
public interface ISubscriberRepository extends Remote {

    void store(String uuid, Subscriber sub) throws RemoteException;

    Subscriber find(String uuid) throws RemoteException;

    ArrayList<String> listUUIDs() throws RemoteException;
}
