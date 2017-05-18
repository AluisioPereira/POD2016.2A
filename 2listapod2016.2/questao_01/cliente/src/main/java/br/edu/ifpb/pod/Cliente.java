/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ajp
 */
public class Cliente {

    private static Registry registry = null;
    private static InterfacePostgres postgres = null;
    private static InterfaceMysql mysql = null;

    public static synchronized boolean addPostgres(Pessoa p) {
        try {

            registry = LocateRegistry.getRegistry("localhost", 10999);

            postgres = (InterfacePostgres) registry.lookup("ServidorPostgres");
            System.out.println("Enviando pessoa para ser adicionado no banco de dados postgres.");

            boolean pb = postgres.add(p);
            if (pb) {
                System.out.println("Adicionado ao postgres.");
            } else {
                System.out.println("Não adicionou ao postgres.");
            }

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean addMysql(Pessoa p) {
        try {
            registry = LocateRegistry.getRegistry("localhost", 10888);
            mysql = (InterfaceMysql) registry.lookup("ServidorMysql");
            System.out.println("Enviando pessoa para ser adicionado no banco de dado mysql");
            boolean mb = mysql.add(p);
            if (mb) {
                System.out.println("Adicionado ao mysqul.");
            } else {
                System.out.println("Não adicionou ao mysqul.");
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private static synchronized Pessoa bucarPostgres(String nome) throws RemoteException, NotBoundException {
        Pessoa p = null;
        try {

            registry = LocateRegistry.getRegistry("localhost", 10999);

            postgres = (InterfacePostgres) registry.lookup("ServidorPostgres");

            System.out.println("Buscando nome no postgres.");
            Pessoa p1 = postgres.buscarNome(nome);
            if (p1 != null) {
                p = p1;
            }
            return p;
        } catch (Exception ex) {
            ex.printStackTrace();
            return p;
        }
    }

    private static synchronized Pessoa bucarMysql(String nome) throws RemoteException, NotBoundException {
        Pessoa p = null;
        try {
            registry = LocateRegistry.getRegistry("localhost", 10888);
            mysql = (InterfaceMysql) registry.lookup("ServidorMysql");

            System.out.println("Buscando sobrenome no mysqul.");
            Pessoa p2 = mysql.buscarNome(nome);
            if (p2 != null) {
                p = p2;
            }
            return p;
        } catch (Exception ex) {
            ex.printStackTrace();
            return p;
        }
    }

    public static synchronized boolean removerPostgres(Pessoa p) {
        try {
            registry = LocateRegistry.getRegistry("localhost", 10999);
            postgres = (InterfacePostgres) registry.lookup("ServidorPostgres");
            System.out.println("Remover pessoa...");

            boolean pb = postgres.remove(p);
            if (pb) {
                System.out.println("Removida do postgres.");
                return true;
            } else {
                System.out.println("Não Removida do postgres.");
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean removerMysql(Pessoa p) {
        try {
            registry = LocateRegistry.getRegistry("localhost", 10888);
            mysql = (InterfaceMysql) registry.lookup("ServidorMysql");
            System.out.println("Remover pessoa...");

            boolean mb = mysql.remove(p);
            if (mb) {
                System.out.println("Removida do mysqul.");
                return true;
            } else {
                System.out.println("Não Removida do mysqul.");
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {

        int escolha = 1;

        while (escolha == 1) {
            Scanner s = new Scanner(System.in);
            System.out.println("Informe o gostaria de fazer:\n0-Nada; \n1-Adicionar uma pessoa aos bancos (postgres, mysql); \n2-Consultar uma pessoa; \n3-Remover uma pessoa.");
            System.out.print("Digite: ");
            escolha = s.nextInt();

            switch (escolha) {
                case 0: {
                    break;
                }
                case 1: {
                    Random r = new Random();
                    int id = r.nextInt(1000);
                    System.out.println("Contruindo pessoa: ");
                    System.out.print("Informe o nome: ");
                    String nome = s.next();
                    System.out.print("Informe o sobrenome: ");
                    String sobrenome = s.next();
                    Pessoa pessoa = new Pessoa(id, nome, sobrenome);
                    boolean retorno1 = addPostgres(pessoa);
                    //
                    //mysql
                    boolean retorno2 = addMysql(pessoa);
                    //&& retorno2
                    if (retorno1 && retorno2) {
                        System.out.println("Add aos bancos de dados realizado com sucesso.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Buscar por nome: ");
                    System.out.print("Informe o nome:");
                    String nome = s.next();
                    try {

                        //buscar no postgres
                        Pessoa pPost = bucarPostgres(nome);

                        // buscar no mysql 
                        Pessoa pMy = bucarMysql(nome);

                        //montando pessoa buscada
                        Pessoa p = new Pessoa();

                        if (pPost != null && pMy != null) {

                            if (pPost.equals(pMy)) {
                                System.out.println("Verificando consistência das informações, quanto a igualdade das pessoas buscadas.");
                                System.out.println("do postgres:" + pPost.toString());
                                System.out.println("do mysql:" + pMy.toString());

                                //montando a pessoa (p) com id e nome da busca do postgres e o sobrenome da busca do mysql.
                                p.setId(pPost.getId());
                                p.setNome(pPost.getNome());
                                p.setSobrenome(pMy.getSobrenome());

                            } else {
                                System.out.println("As duas pessoas (postgres e mysql) não são iguais.");
                            }
                        }

                        System.out.println("Situação da busca nos bancos de dados (postgres e mysql) \n ----------------");

                        if (pPost == null) {
                            System.out.println("Não foi encontrado ninguém no (POSTGRES) com estas informações (nome: " + nome + ")");
                        } else {
                            System.out.println("Pessoa do postgres: id: " + pPost.getId() + pPost.toString());
                        }
                        if (pMy == null) {
                            System.out.println("Não foi encontrado ninguém no (MYSQL) com estas informações (nome: " + nome + ")");
                        } else {
                            System.out.println("Pessoa do postgres: id: " + pMy.getId() + pMy.toString());
                        }

                        if (p == null) {
                            System.out.println("não foi contruida nenhuma pessoa pessoa.");
                        } else {
                            System.out.println("Construindo a pessoa de forma consistente com id e nome do postgres e sobrenome do mysql: \n id: " + p.getId() + " | " + p.toString());
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                    break;
                }
                case 3: {
                    System.out.println("Remover pessoa pelo nome.");
                    System.out.print("Informe o nome: ");
                    String nome = s.next();
                    try {
                        Pessoa buscaPessoa1 = bucarPostgres(nome);

                        //mysql
                        Pessoa buscaPessoa2 = bucarMysql(nome);
                        //&& buscaPessoa2 != null
                        if (buscaPessoa1 != null && buscaPessoa2 != null) {
                            boolean removido1 = removerPostgres(buscaPessoa1);

                            //mysql
                            boolean removido2 = removerMysql(buscaPessoa2);
                            //&& removido2
                            if (removido1 && removido2) {
                                System.out.println("Pessoa removida do postgres: \n" + buscaPessoa1.toString());

                                //mysql                                
                                System.out.println("Pessoa removida do Mysql: \n" + buscaPessoa2.toString());

                            } else {
                                System.out.println("Não foi removido ninguem.");
                            }
                        } else {
                            System.out.println("Pessoa não foi encontrado.");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            System.out.println("------------ \n Menu \n 0-Encerra; \n 1-Execultar novamente.  ");

            System.out.print("Digite: ");
            escolha = s.nextInt();
            System.out.println("__________________");

        }

    }

}
