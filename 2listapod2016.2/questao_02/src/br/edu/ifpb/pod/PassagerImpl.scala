package br.edu.ifpb.pod

class PassagerImpl(nome: String, fila: Int) extends Thread with Passager {


  override def getInTheCar(): Unit = {
    // entrar no carro
    synchronized{

    }
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