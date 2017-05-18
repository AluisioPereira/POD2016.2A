package br.edu.ifpb.pod;

import java.util.Random;

/**
 *
 * @author ajp
 */
public class GerenteDeEntradas {

    private int conte = 0;
    private int falou = 0;
    private final FilaQ1 filaq1;
    private final FilaQ2 filaq2;

    private Pessoa criarPessoa() {
        return new Pessoa(conte++);
    }

    public GerenteDeEntradas(FilaQ1 fila1, FilaQ2 fila2) {
        this.filaq1 = fila1;
        this.filaq2 = fila2;
    }

    public int execultarFila1() {
        //randomizando o número de pessoas a serem criadas (entrada)
        Random r = new Random();
        double d = r.nextDouble();
        double qtd1 = Math.pow(0.833, (-d));
        int qtd = (int) qtd1;
        //criando e encaminhando para a fila
        for (int i = 0; i < qtd; i++) {
            Pessoa p = criarPessoa();
            falou += filaq1.inserir(p) ? 0 : 1;
        }
        //
        return qtd;
    }

    public int execultarFila2() {
        //randomizando o número de pessoas a serem criadas (entrada)
        Random r = new Random();
        double d = r.nextDouble();
        double qtd2 = 3 * Math.pow(d, 2) + 5;
        int qtd = (int) qtd2;
        //criando e encaminhando para a fila
        for (int i = 0; i < qtd; i++) {
            Pessoa p = criarPessoa();
            falou += filaq2.inserir(p) ? 0 : 1;
        }
        //
        return qtd;
    }

    public int conte() {
        return conte;
    }

    public int falou() {
        return falou;
    }

}
