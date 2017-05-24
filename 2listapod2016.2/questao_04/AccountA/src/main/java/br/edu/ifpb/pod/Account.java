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
public interface Account extends Remote {

    void debit(float value) throws RemoteException;

    void credit(float value) throws RemoteException;

    float print() throws RemoteException;

}
