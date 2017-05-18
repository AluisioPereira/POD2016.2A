package br.edu.ifpb.pod

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
  * Created by ajp on 13/05/2017.
  */
object Servidor1 {
  var servidor1: ServerSocket = null;
  var cliente1: Socket = null;
  var input1: ObjectInputStream = null;

  def main(args: Array[String]): Unit = {

    try {

      // abrindo a porta de conexão.
      servidor1 = new ServerSocket(1099);
      //se conectando ao cliente (node1)

      println("Node1 esperando cliente...");

      cliente1 = servidor1.accept();

      println("conexao estabelecedia");
      //lendo objeto recebido
      input1 = new ObjectInputStream(cliente1.getInputStream());
      while (true) {
        //        var op1: Op1 = input1.readObject()[Op1];
        var op1: Op1 = input1.readObject().asInstanceOf[Op1];
        if (op1 != null) {
          println("Sabendo que x = " + op1.x + " e y = " + op1.y + "\n A soma entre ele é igual a: " + (op1.x + op1.y));
        } else {
          println("Não resolveu encaminahdo para node3");
        }
        println("Node1 operação resolvida. \n ------------");
      }
    } catch {
      case ioe: IOException => ioe.printStackTrace();
      case e: Exception => e.printStackTrace();
      case c: ClassNotFoundException => c.printStackTrace();
    }

  }
}