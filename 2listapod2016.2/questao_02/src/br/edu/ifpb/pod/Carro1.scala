package br.edu.ifpb.pod

import java.util.ArrayList
import java.util.function.Consumer

class Carro1(var capacidade: Int, var tempo: Boolean, var lotacao: Int, var qtd: Int) extends Thread with Car {

  var fila1: ArrayList[PassagerImpl] = new ArrayList();
  val f = new Fila1(this);

  def tempoEspera(): Boolean = {
    return this.tempo;
  }

  def estaCheio(): Boolean = synchronized {
    return qtd == lotacao;
  }

  def add(passagerImpl: PassagerImpl): Unit = {
    fila1.add(qtd, passagerImpl);
    contUm();
  }

  def remover(passagerImpl: PassagerImpl): Unit = {
    fila1.remove(qtd, passagerImpl);
  }

  def contUm(): Unit = synchronized {
    synchronized(this) {
      if (!estaCheio()) {
        qtd ++;
      }
      if (estaCheio()) {
        notifyAll();
      }
    }
  }

  def estaVazio(): Boolean = synchronized {
    return qtd == 0;
  }

  def retiraUm(): Unit = synchronized {
    synchronized(this) {
      if (!estaVazio()) {
        qtd --;
      }
      if (estaVazio()) {
        notifyAll();
      }
    }
  }


  override def waifFill(): Unit = {
    // aguardar encher
    println("Aguardando carro1 encher");
    while (!estaCheio()) {
      try {
        wait();
      } catch (InterruptedException e) {
      }
    }
    if (estaCheio()) {
      println("O carrinho1 está cheio" + qtd);
    }

  }

  override def takeAWalk(): Unit = {
    // dar uma volta
    println("Dando uma volta...")
    synchronized {
      try {
        wait(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
    println("Finalizado volta...")

  }

  override def waitGetOUtAll(): Unit = {
    // aguardar sair todos
    synchronized{
      qtd = 0;
    }
    var i=0;

  }

}