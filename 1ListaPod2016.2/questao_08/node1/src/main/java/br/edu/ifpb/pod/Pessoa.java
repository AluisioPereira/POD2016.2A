package br.edu.ifpb.pod;

/**
 *
 * @author ajp
 */
public class Pessoa {

    private String nome;

    public Pessoa(int seq) {
        nome = "Pessoa " + seq;
    }

    public String getNome() {
        return nome;
    }

}
