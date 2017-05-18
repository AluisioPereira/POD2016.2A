package br.edu.ifpb.pod;

/**
 *
 * @author ajp
 */
public class Principal {

    public static void main(String[] args) throws InterruptedException {
        //
        FilaQ1 fila1 = new FilaQ1();
        AtendenteA0 atendente0 = new AtendenteA0(fila1);
        AtendenteA1 atendente1 = new AtendenteA1(fila1);

        FilaQ2 fila2 = new FilaQ2();
        AtendenteA2 atendente2 = new AtendenteA2(fila2);

        GerenteDeEntradas gde = new GerenteDeEntradas(fila1, fila2);

        Gerador gerador = new Gerador(gde, atendente0, atendente1, atendente2, fila1, fila2);
        gerador.execultarGerador();
    }

}
