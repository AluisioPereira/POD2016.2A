/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author ajp
 */
public class Principal3 {

    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, SQLException {

        int escolha = 1;

        while (escolha == 1) {

            System.out.println("Contruindo pessoa: ");
            System.out.print("Informe o nome: ");
            Scanner s = new Scanner(System.in);
            String name = s.next();
            Pessoa pessoa = new Pessoa(name);
            boolean cadastrouPost = insertPostgres(pessoa);
            if (cadastrouPost) {
                System.out.println("Atualização no banco de dados feita com sucesso");
            } else {
                System.out.println("Não foi possivel ser feito a atualização no banco de dados");
            }

            System.out.println("------------ \n Menu \n 1 para execultar novamente; \n 0 para encerra. ");

            System.out.print("Digite: ");
            escolha = s.nextInt();
            System.out.println("__________________");

        }

    }

    public static synchronized boolean insertPostgres(Pessoa pessoa) throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        PessoaBoPostgres pessoaBoPostgres = new PessoaBoPostgres();
        System.out.println("Adicionando pessoa no postgres.");
        boolean cadastrouPost = pessoaBoPostgres.adicionarPessoa(pessoa);
        return cadastrouPost;
    }

}
