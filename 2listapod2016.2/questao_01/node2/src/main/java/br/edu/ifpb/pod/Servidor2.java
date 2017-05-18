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
public class Servidor2 {
    
    public Servidor2() {
    }
        public static void main(String[] args) {
         try {
            
            InterfaceMysql mysql = new PessoaImplMysql();
            
            Registry registry = LocateRegistry.createRegistry(10888);
            System.out.println("Registrando...");
            registry.bind("ServidorMysql", mysql);
            System.out.println("Servidor2 - pronto para ouvir...");
        } catch (Exception e) {
            System.err.println("Servidor2 - não foi possível estabelecer conexao.");
            e.printStackTrace();
        }
    }
   
}
