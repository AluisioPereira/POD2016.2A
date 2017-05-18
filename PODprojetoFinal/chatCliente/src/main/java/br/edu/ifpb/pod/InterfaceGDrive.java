/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import br.edu.ifpb.pod.pubsub.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ajp
 */
public interface InterfaceGDrive extends Remote {

    boolean add(Usuario p) throws RemoteException;

    Usuario buscarNome(String nome) throws RemoteException;

    boolean remove(Usuario p) throws RemoteException;

}
