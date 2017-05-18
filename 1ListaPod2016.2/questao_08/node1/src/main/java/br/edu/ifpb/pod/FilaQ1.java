package br.edu.ifpb.pod;

/**
 *
 * @author ajp
 */
public class FilaQ1 {

    private int conte = 0;
    private Pessoa[] pessoas = new Pessoa[100];

    private void organizar() {
        for (int i = 1; i < pessoas.length; i++) {
            Pessoa p = pessoas[i];
            pessoas[i - 1] = p;
        }
        pessoas[pessoas.length - 1] = null;
    }

    public boolean inserir(Pessoa p) {
        synchronized (pessoas) {
            if (estaVazia()) {
                pessoas[conte] = p;
                conte++;
                return true;
            } else if (!estaCheia()) {
                pessoas[conte - 1] = p;
                conte++;
                return true;
            } else {
                return false;
            }
        }
    }

    public Pessoa retirar() {
        synchronized (pessoas) {
            if (!estaVazia()) {
                Pessoa p = pessoas[0];
                pessoas[0] = null;
                organizar();
                conte--;
                return p;
            } else {
                throw new RuntimeException("A fila está vazia.");
            }
        }
    }

    public int tamanho() {
        return conte;
    }

    public boolean estaCheia() {
        return conte == 100;
    }

    public boolean estaVazia() {
        return conte == 0;
    }

}
