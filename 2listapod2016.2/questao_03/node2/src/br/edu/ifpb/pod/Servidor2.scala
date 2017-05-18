package br.edu.ifpb.pod

import java.io.{IOException, ObjectInputStream}
import java.net.{ServerSocket, Socket};


/**
  * Created by ajp on 13/05/2017.
  */
object Servidor2 {
  var servidor2: ServerSocket = null;
  var cliente2: Socket = null;
  var input2: ObjectInputStream = null;

  def main(args: Array[String]): Unit = {

    try {

      // abrindo a porta de conexão.
      servidor2 = new ServerSocket(1088);
      //se conectando ao cliente (node1)

      println("Node2 esperando cliente...");

      cliente2 = servidor2.accept();

      println("conexao estabelecedia");
      //lendo objeto recebido
      input2 = new ObjectInputStream(cliente2.getInputStream());
      while (true) {
        var op1: Op1 = input2.readObject().asInstanceOf[Op1];
        //var op1: Op1 = input2.readObject()[Op1];
        if (op1 != null) {
          println("Sabendo que x = " + op1.x + " e y = " + op1.y + "\n A soma entre ele é igual a: " + (op1.x + op1.y));
        } else {
          println("Não resolveu encaminahdo para node3");
        }
        println("Node2 operação resolvida. \n ------------");
      }
    } catch {
      case ioe: IOException => ioe.printStackTrace();
      case e: Exception => e.printStackTrace();
      case c: ClassNotFoundException => c.printStackTrace();
    }

  }
}