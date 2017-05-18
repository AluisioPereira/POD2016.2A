package br.edu.ifpb.pod;

/**
 *
 * @author ajp
 */
public class AtendenteA1 {

    private int conte = 0;
    private final FilaQ1 fila1;
    private Pessoa atual = null;

    public AtendenteA1(FilaQ1 queue) {
        this.fila1 = queue;
    }

    public void iniciarAtendimento() {
        atual = this.fila1.retirar();
        System.out.println("Iniciando o atendimento ao cliente " + atual.getNome());
    }

    public void pararAtendimento() {
        System.out.println("Encerrando o atendimento ao cliente " + atual.getNome());
        atual = null;
        conte++;
    }

    public int conte() {
        return conte;
    }

}
