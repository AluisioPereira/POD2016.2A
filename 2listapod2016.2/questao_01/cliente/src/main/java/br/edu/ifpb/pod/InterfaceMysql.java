/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ajp
 */
public interface InterfaceMysql extends Remote {

    boolean add(Pessoa p) throws RemoteException;

    Pessoa buscarNome(String nome) throws RemoteException;

    boolean remove(Pessoa p) throws RemoteException;
}
