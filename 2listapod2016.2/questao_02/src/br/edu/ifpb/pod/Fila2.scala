package br.edu.ifpb.pod

class Fila2 extends Thread with Passager {

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