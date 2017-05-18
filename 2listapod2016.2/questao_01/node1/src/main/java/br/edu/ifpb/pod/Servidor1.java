/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ajp
 */
public class Servidor1 {

    public Servidor1() {
    }

    public static void main(String args[]) {

        try {
                        
            InterfacePostgres postgres = new PessoaImplPostgres();
            
            Registry registry = LocateRegistry.createRegistry(10999);
            System.out.println("Registrando...");
            registry.bind("ServidorPostgres", postgres);
            System.out.println("Servidor1 - pronto para ouvir...");

        } catch (Exception e) {
            System.err.println("Servidor1 - não foi possível estabelecer comunicação.");
            e.printStackTrace();
        }
    }
}
