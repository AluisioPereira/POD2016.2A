package br.edu.ifpb.pod;

/**
 *
 * @author ajp
 */
public class Gerador {

    private GerenteDeEntradas gerente;
    private AtendenteA0 att0;
    private AtendenteA1 att1;
    private AtendenteA2 att2;

    private FilaQ1 fila1;
    private FilaQ2 fila2;

    private Thread t0 = null;
    private Thread t1 = null;
    private Thread t2 = null;

    private boolean fim = false;
    private long x;

    public Gerador(GerenteDeEntradas gerente, AtendenteA0 att0, AtendenteA1 att1, AtendenteA2 att2, FilaQ1 fila1, FilaQ2 fila2) {
        this.gerente = gerente;
        this.att0 = att0;
        this.att1 = att1;
        this.att2 = att2;
        this.fila1 = fila1;
        this.fila2 = fila2;
    }

    private void imprimirResultadoFila1() {
        System.out.println("-------------------------------------------------------");
        System.out.println("Quantidade de pessoas entraram na fila1: " + gerente.conte());
        System.out.println("Quantidade de pessoas que foram embora da fila1: " + gerente.falou());
        System.out.println("Quantidade de pessoas atendidas na fila1: " + (att0.conte() + att0.conte()));
        System.out.println("Quantidade de pessoas ficaram na fila1: " + fila1.tamanho());
        System.out.println("-------------------------------------------------------");
    }

    private void imprimirResultadoFila2() {
        System.out.println("-------------------------------------------------------");
        System.out.println("Quantidade de pessoas entraram na fila2: " + gerente.conte());
        System.out.println("Quantidade de pessoas que foram embora da fila2: " + gerente.falou());
        System.out.println("Quantidade de pessoas atendidas na fila2: " + att2.conte());
        System.out.println("Quantidade de pessoas ficaram na fila2: " + fila2.tamanho());
        System.out.println("-------------------------------------------------------");
    }

    private void temporizador01() {
        Runnable r0 = new Runnable() {
            @Override
            public void run() {
                //
                long ti = System.currentTimeMillis();
                //
                int timef = 0;
                boolean printed = false;
                //
                long time0 = System.currentTimeMillis();
                long time1 = System.currentTimeMillis();
                while (true) {
                    time1 = System.currentTimeMillis();
                    if (time1 - time0 >= (1000)) {
                        timef++;
                        time0 = time1;
                        synchronized (t0) {
                            t0.notifyAll();
                        }
                        System.out.println("[T0] Time: " + timef + "s");
                    }
                    if (timef == 59 && printed == false) {
                        fim = true;
                        printed = true;
                        System.out.println("[T0] The end!");
                    }
                    if (timef == 60) {
                        imprimirResultadoFila1();
                        break;
                    }
                }
                //
                long tf = System.currentTimeMillis();
                System.out.println("Tempo total de simulação (ms): " + (tf - ti));
            }
        };
        //
        t0 = new Thread(r0);
        t0.start();
    }

    private void temporizador2() {
        Runnable r0 = new Runnable() {
            @Override
            public void run() {
                //
                long ti = System.currentTimeMillis();
                //
                int timef = 0;
                boolean printed = false;
                //
                long time0 = System.currentTimeMillis();
                long time1 = System.currentTimeMillis();
                while (true) {
                    time1 = System.currentTimeMillis();
                    if (time1 - time0 >= (1000)) {
                        timef++;
                        time0 = time1;
                        synchronized (t2) {
                            t2.notifyAll();
                        }
                        System.out.println("[T0] Time: " + timef + "s");
                    }
                    if (timef == 59 && printed == false) {
                        fim = true;
                        printed = true;
                        System.out.println("[T0] The end!");
                    }
                    if (timef == 60) {
                        imprimirResultadoFila2();
                        break;
                    }
                }
                //
                long tf = System.currentTimeMillis();
                System.out.println("Tempo total de simulação (ms): " + (tf - ti));
            }
        };
        //
        t2 = new Thread(r0);
        t2.start();
    }

    private void gerenciadorDeEntradaFila1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                int time = 0;
                while (fim == false) {
                    //
                    synchronized (t0) {
                        try {
                            t0.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //
                    if (++time == 3) {
                        int qt = gerente.execultarFila1();
                        time = 0;
                        System.out.println("[T1] Ocorreu a entrada de " + qt + " pessoa(s).");
                    } else {
                        System.out.println("[T1] Aguardando a entrada de pessoas.");
                    }
                }
            }
        };
        //
        Thread t01 = new Thread(r1);
        t01.start();
    }

    private void gerenciadorDeEntradaFila2() {
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                int time = 0;
                while (fim == false) {
                    //
                    synchronized (t2) {
                        try {
                            t2.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //
                    if (++time == 3) {
                        int qtd2 = gerente.execultarFila2();
                        time = 0;
                        System.out.println("[T1] Ocorreu a entrada de " + qtd2 + " pessoa(s).");
                    } else {
                        System.out.println("[T1] Aguardando a entrada de pessoas.");
                    }
                }
            }
        };
        //
        Thread t02 = new Thread(r2);
        t02.start();
    }

    private void atendimentoAtt0() throws InterruptedException {
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                //
                while (fim == false) {
                    //
                    synchronized (t0) {
                        try {
                            t0.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //
                    try {
                        System.out.println("Verifacar se há atendimento.");
                        att0.iniciarAtendimento();
                    } catch (RuntimeException e) {
                        System.out.println("Ninguém para atendimento.");
                        continue;
                    }
                    //
                    int time = 0;
                    while (true) {
                        //
                        synchronized (t0) {
                            try {
                                t0.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //
                        if (++time == 2) {
                            System.out.print("[T2] ");
                            att0.pararAtendimento();
                            break;
                        }
                    }
                }
            }
        };
        //

        Thread t00 = new Thread(r2);
        t00.start();

    }

    private void atendimentoAtt1() throws InterruptedException {
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                //
                while (fim == false) {
                    //
                    synchronized (t0) {
                        try {
                            t0.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //
                    try {
                        System.out.println("Verifacar se há atendimento.");
                        att1.iniciarAtendimento();
                    } catch (RuntimeException e) {
                        System.out.println("Ninguém para atendimento.");
                        continue;
                    }
                    //
                    int time = 0;
                    while (true) {
                        //
                        synchronized (t0) {
                            try {
                                t0.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //
                        if (++time == 2) {
                            System.out.print("[T2] ");
                            att1.pararAtendimento();
                            break;
                        }
                    }
                }
            }
        };
        //

        Thread t01 = new Thread(r2);
        t01.start();

    }

    private void atendimentoAtt2() throws InterruptedException {
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                //
                while (fim == false) {
                    //
                    synchronized (t2) {
                        try {
                            t2.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //
                    try {
                        System.out.println("Verifacar se há atendimento.");
                        att2.iniciarAtendimento();
                    } catch (RuntimeException e) {
                        System.out.println("Ninguém para atendimento.");
                        continue;
                    }
                    //
                    int time = 0;
                    while (true) {
                        //
                        synchronized (t2) {
                            try {
                                t2.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //
                        if (++time == 2) {
                            System.out.print("[T2] ");
                            att2.pararAtendimento();
                            break;
                        }
                    }
                }
            }
        };
        //

        Thread t02 = new Thread(r2);
        t02.start();

    }

    public void execultarGerador() throws InterruptedException {
        temporizador01();
        temporizador2();
        gerenciadorDeEntradaFila1();
        gerenciadorDeEntradaFila2();
        atendimentoAtt0();
        atendimentoAtt1();
        atendimentoAtt2();

    }

}
