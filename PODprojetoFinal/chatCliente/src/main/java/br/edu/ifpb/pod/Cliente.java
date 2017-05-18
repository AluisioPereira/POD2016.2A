/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import br.edu.ifpb.pod.pubsub.Usuario;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ajp
 */
public class Cliente {

    private static Registry registry = null;
    private static InterfaceGDrive gdrive = null;
    private static ArrayList<String> grupos = new ArrayList<String>();

    public static synchronized boolean addGdrive(Usuario p) {
        try {

            registry = LocateRegistry.getRegistry("localhost", 10999);

            gdrive = (InterfaceGDrive) registry.lookup("__ChatServer__");
            System.out.println("Enviando pessoa para ser adicionado no banco de dados Gdrive.");

            boolean pb = gdrive.add(p);
            if (pb) {
                System.out.println("Adicionado ao Gdrive.");
            } else {
                System.out.println("Não adicionou ao Gdrive.");
            }

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private static synchronized Usuario buscarGDrive(String nome) throws RemoteException, NotBoundException {
        Usuario p = null;
        try {

            registry = LocateRegistry.getRegistry("localhost", 10999);

            gdrive = (InterfaceGDrive) registry.lookup("__ChatServer__");

            System.out.println("Buscando nome no Gdrive.");
            Usuario p1 = gdrive.buscarNome(nome);
            if (p1 != null) {
                p = p1;
            }
            return p;
        } catch (Exception ex) {
            ex.printStackTrace();
            return p;
        }
    }

    public static synchronized boolean removerGDrive(Usuario p) {
        try {
            registry = LocateRegistry.getRegistry("localhost", 10999);
            gdrive = (InterfaceGDrive) registry.lookup("__ChatServer__");
            System.out.println("Remover pessoa...");

            boolean pb = gdrive.remove(p);
            if (pb) {
                System.out.println("Removida do Gdrive");
                return true;
            } else {
                System.out.println("Não Removida do Gdrive");
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {

        int escolha = 1;

        while (escolha == 1) {
            Scanner s = new Scanner(System.in);
            System.out.println("Informe o gostaria de fazer:\n0-Nada; \n1-Se registrar (nome, e-mail); \n2-Login (Acessar).");
            System.out.print("Digite: ");
            escolha = s.nextInt();

            switch (escolha) {
                case 0: {
                    break;
                }
                case 1: {
                    Random r = new Random();
                    int id = r.nextInt(1000);
                    System.out.println("Registro: ");
                    System.out.print("Informe o nome: ");
                    String nome = s.next();
                    System.out.print("Informe o e-mail: ");
                    String email = s.next();
                    Usuario pessoa = new Usuario(id, nome, email);
                    //boolean retorno1 = addGDrive(pessoa);
                    boolean retorno1 = addGdrive(pessoa);
                    //&& retorno2
                    if (retorno1) {
                        System.out.println("Add aos drive de dados realizado com sucesso.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Realize login ");
                    System.out.print("Informe o nome do usuário:");
                    String nome = s.next();
                    try {

                        //buscar no usuario
                        Usuario pGdrive = buscarGDrive(nome);

                        //montando pessoa buscada
                        Usuario p = new Usuario();

                        if (pGdrive != null) {
                            System.out.println("Acesso confirmado.");
                            p.setId(pGdrive.getId());
                            p.setNome(pGdrive.getNome());
                            p.setEmail(pGdrive.getEmail());

                            int escolha1 = 1;

                            while (escolha1 == 1) {
                                Scanner s1 = new Scanner(System.in);
                                System.out.println("Informe o gostaria de fazer:\n0-logout; \n1-Se escrever em grupo;");
                                System.out.print("Digite: ");
                                escolha = s1.nextInt();
                                switch (escolha1) {
                                    case 0: {
                                        break;
                                    }
                                    case 1: {
                                        Random r = new Random();
                                        int id = r.nextInt(1000);
                                        String g1 = "Grupo1";
                                        String g2 = "Grupo2";
                                        String g3 = "Grupo3";
                                        grupos.add(g1);
                                        grupos.add(g2);
                                        grupos.add(g3);
                                        System.out.println("1 - Grupo1, 2 - Grupo2, 3 - Grupo3 ");
                                        System.out.print("Informe o grupo: ");
                                        int grupo = s1.nextInt();

                                        if (grupo == 1) {
                                        } else if (grupo == 2) {

                                        } else if (grupo == 3) {

                                        }

                                        break;
                                    }

                                }
                            }

                        } else {
                            System.out.println("Acesso negado.");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                    break;
                }

            }

            System.out.println("------------ \n Menu \n 0-Encerra; \n 1-Execultar novamente.  ");

            System.out.print("Digite: ");
            escolha = s.nextInt();
            System.out.println("__________________");

        }

    }

}
