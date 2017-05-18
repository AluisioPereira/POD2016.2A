package br.edu.ifpb.pod;

/**
 *
 * @author ajp
 */
public class AtendenteA2 {

    private int conte = 0;
    private final FilaQ2 fila2;
    private Pessoa atual = null;

    public AtendenteA2(FilaQ2 queue) {
        this.fila2 = queue;
    }

    public void iniciarAtendimento() {
        atual = this.fila2.retirar();
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
