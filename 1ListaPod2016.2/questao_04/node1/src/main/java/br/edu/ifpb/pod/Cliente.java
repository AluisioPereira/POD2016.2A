/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ajp
 */
public class Cliente {

    public static int enviar(String nome) throws IOException {
        //abrindo comunicação com node2
        Socket node2 = new Socket("localhost", 1099);
        //enviando o objeto
        ObjectOutputStream output2 = new ObjectOutputStream(node2.getOutputStream());

        Pessoa pessoa = new Pessoa(nome);
        output2.writeObject(pessoa);
        output2.flush();
        System.out.println("Enviando a pessa para node2.");
        System.out.println("Esperando a entrega...");

        int i = node2.getInputStream().read();

        if (i == 1) {
            System.out.println("Entrega realizada.");
            output2.close();
            node2.close();
            return i;
        } else if (i == 0) {
            System.out.println("Entrega não realizada tentar novamente?");
            int opEnviar = 1;
            System.out.println("Enviar novamente: \n 1 - sim \n 0 - não");
            Scanner opcao = new Scanner(System.in);
            opEnviar = opcao.nextInt();
            switch (opEnviar) {
                case 0: {
                    System.out.println("Entrega encerrada.");
                    System.out.println("Enviar outra pessoa?");
                    break;
                }
                case 1: {
                    System.out.println("Tentando novamente");
                    enviar(nome);
                    break;
                }
            }
        }
        output2.close();
        node2.close();
        return i;
    }

    public static void main(String[] args) throws IOException {

        int escolha = 1;

        while (escolha == 1) {

            System.out.println("Contruindo pessoa: ");
            System.out.println("Informe o nome: ");
            Scanner s = new Scanner(System.in);
            String name = s.next();
            int retorno = enviar(name);

            if (retorno == 1) {
                System.out.println("Entrega realizada.");
            }

            System.out.println("------------ \n Menu \n 1 para execultar novamente; \n 0 para encerra. ");

            System.out.print("Digite: ");
            escolha = s.nextInt();
            System.out.println("__________________");

        }

    }

}
