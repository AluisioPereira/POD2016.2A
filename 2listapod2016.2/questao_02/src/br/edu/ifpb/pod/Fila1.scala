package br.edu.ifpb.pod

import java.util.ArrayList;

class Fila1(val carro: Carro1) extends Thread with Passager {

  val passageiros: ArrayList[PassagerImpl] = new ArrayList();

  def tamanho() {

  }

  override def getInTheCar(): Unit = {
    // entrar no carro
  }
  override def waitRideAway(): Unit = {
    // aguardar passeio até finalizar
  }
  override def getOutThecar(): Unit = {
    // sair do carro
  }
  override def rideInThePark(): Unit = {
    // passear pelo parque
  }
}