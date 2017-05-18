package br.edu.ifpb.pod

import java.io.{IOException, ObjectInputStream}
import java.net.{ServerSocket, Socket};


/**
  * Created by ajp on 13/05/2017.
  */
object Servidor3 {
  var servidor3: ServerSocket = null;
  var cliente3: Socket = null;
  var input3: ObjectInputStream = null;

  def main(args: Array[String]): Unit = {

    try {

      // abrindo a porta de conexão.
      servidor3 = new ServerSocket(1234);
      //se conectando ao cliente (node1)

      println("Node3 esperando cliente...");

      cliente3 = servidor3.accept();

      println("conexao estabelecedia");
      //lendo objeto recebido
      input3 = new ObjectInputStream(cliente3.getInputStream());
      while (true) {
        var op2: Op2 = input3.readObject().asInstanceOf[Op2];

        if (op2 != null) {
          println("Sabendo que x = " + op2.x + " e y = " + op2.y + "\n A diferença entre ele é igual a: " + (op2.x - op2.y));
        } else {
          println("Node3 não resolveu! \n encaminhando...\n para node1 \n ou \n para node2");
        }
        println("Node3 operação resolvida. \n ------------");
      }
    } catch {
      case ioe: IOException => ioe.printStackTrace();
      case e: Exception => e.printStackTrace();
      case ex: ClassNotFoundException => ex.printStackTrace();
    }

  }
}