/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ajp
 */
public class Principal2 {

    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, SQLException, InterruptedException {

        int escolha = 1;

        while (escolha == 1) {

            System.out.println("Verificando o banco de dados... ");
            System.out.println("Informe o nome: ");
            boolean checkPost = checkPostgres();
            if (checkPost) {
                System.out.println("Verificação feita com sucesso");
            } else {
                System.out.println("Não foi possivel ser feito a verificação");
            }

            System.out.println("------------ \n Menu de notificação a node1 \n 0 - não notificar mais. \n 1 - para notificar novamente; \n 2 - para notificar automaticamente quantas vezes você definir e no tempo em segundos que você expecificar. ");

            System.out.print("Digite: ");
            Scanner s = new Scanner(System.in);
            escolha = s.nextInt();
            switch (escolha) {
                case 0: {
                    break;
                }
                case 1: {
                    boolean checkPost1 = checkPostgres();
                    if (checkPost1) {
                        System.out.println("Verificação feita com sucesso");
                    } else {
                        System.out.println("Não foi possivel ser feito a verificação");
                    }
                    break;
                }
                case 2: {
                    System.out.print("Informe quantas vezes node1 será notificado: \nQuantidade: ");
                    int opcao2 = s.nextInt();
                    System.out.print("De quanto em quantos segundos:  \nTempo em segundos: ");
                    int opcao3 = s.nextInt();
                    System.out.print("Node1 será notificado " + opcao2 + " veze(s) a cada " + opcao3 + " segundo(s).");
                    boolean retornocheckPostgres = checkPostgresTempo(opcao2, opcao3);
                    if (retornocheckPostgres) {
                        System.out.println("Quantidade de notificações encerradas.");
                        break;
                    } else {
                        break;
                    }

                }
            }
            System.out.println("\n------------ \n Menu \n 0 - para encerra. \n 1 - para notificar node1 novamente; ");

            System.out.print("Digite: ");
            escolha = s.nextInt();
            System.out.println("__________________");

        }

    }

    public static synchronized boolean checkPostgres() throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        PessoaBoPostgres pessoaBoPostgres = new PessoaBoPostgres();
        System.out.println("Buscar todas as pessoa no banco de dados");
        ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
        System.out.println("Listando as pessas");
        listaPessoas = pessoaBoPostgres.listarTodos();

        //abrindo comunicação com node1
        Socket node2 = new Socket("localhost", 1099);
        //enviando o objeto
        ObjectOutputStream output2 = new ObjectOutputStream(node2.getOutputStream());
        output2.writeObject(listaPessoas);
        output2.flush();
        System.out.println("Enviando a pessa para node1.");
        System.out.println("Esperando a entrega...");

        int i = node2.getInputStream().read();
        if (i == 1) {
            System.out.println("Entrega realizada com sucesso.");
            output2.close();
            node2.close();
            return true;
        } else if (i == 0) {
            System.out.println("Entrega não realizada.");
            return false;
        }
        output2.close();
        node2.close();
        return false;
    }

    public static synchronized boolean checkPostgresTempo(int qtd, int tempo) throws IOException, URISyntaxException, ClassNotFoundException, SQLException, InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < qtd; i++) {
                    try {
                        PessoaBoPostgres pessoaBoPostgres = new PessoaBoPostgres();

                        System.out.println("Buscar todas as pessoa no banco de dados");
                        ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
                        listaPessoas = pessoaBoPostgres.listarTodos();
                        //abrindo comunicação com node1
                        Socket node2 = new Socket("localhost", 1099);
                        //enviando o objeto
                        ObjectOutputStream output2 = new ObjectOutputStream(node2.getOutputStream());

                        output2.writeObject(listaPessoas);

                        output2.flush();

                        System.out.println("Enviando as pessoas para node1.");
                        System.out.println("Esperando a entrega de n.º: " + (i + 1));

                        int retorno1 = node2.getInputStream().read();
                        if (retorno1 == 1) {
                            System.out.println((i + 1) + " Entrega realizada com sucesso.");
                            System.out.println("................................");
                        } else if (retorno1 == 0) {
                            System.out.println((i + 1) + " Entrega não realizada.");
                        }

                        output2.close();
                        node2.close();

                    } catch (IOException ex) {
                        Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        ;
        };
        t.start();
        t.sleep(tempo * 1000);
        t.interrupt();
        return true;
    }

}
