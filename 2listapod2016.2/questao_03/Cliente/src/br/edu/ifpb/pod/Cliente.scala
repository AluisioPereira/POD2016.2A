package br.edu.ifpb.pod

import java.io.ObjectOutputStream
import java.net.Socket
import java.util.{Random, Scanner}

/**
  * Created by ajp on 13/05/2017.
  */
object Cliente {


  def main(args: Array[String]): Unit = {
    //enviando o objeto
    var node1 = new Socket("localhost", 1099);
    var output1 = new ObjectOutputStream(node1.getOutputStream());

    //abrindo comunicação com node2
    var node2 = new Socket("localhost", 1088);
    //enviando o objeto
    var output2 = new ObjectOutputStream(node2.getOutputStream());

    //abrindo comunicação com node3
    var node3 = new Socket("localhost", 1234);
    //enviando o objeto
    var output3 = new ObjectOutputStream(node3.getOutputStream());

    var escolha: Int = 1;
    var r = new Random;



    while (escolha == 1) {
      var x: Int = 0;
      var y: Int = 0;
      var opEnviar: Int = 0;
      var nodeEnviar: Int = 0;

      var op1: Op1 = null;
      var op2: Op2 = null;

      x = r.nextInt(100);
      y = r.nextInt(100);

      opEnviar = r.nextInt(2);
      nodeEnviar = r.nextInt(3);

      println("Operação a ser enviada: Op" + (opEnviar + 1));
      opEnviar match {
        case 0 => {
          op1 = new Op1(x, y);
          println("Op1=sum(x,y) \nContruindo temos: Op1=sum(" + x + ", " + y + ")");
        }
        case 1 => {
          op2 = new Op2(x, y);
          println("Op2=diff(x,y) \nContruindo temos: Op2=diff(" + x + ", " + y + ")");
        }
      }

      println("Encaminhar ao Node" + (nodeEnviar + 1));
      println("\nEnviando...");
      nodeEnviar match {
        case 0 => {
          println("Enviado ao node1");
          if (op1 != null) {
            output1.writeObject(op1);
            output1.flush();
            println("Resolvido em node1");
          } else {
            println("Node1 não sabe servolver! ");
            println("Enviado a outro node diferente ");
            output3.writeObject(op2);
            output3.flush();
            println("Resolvido em node3");
          }
        }
      }
      nodeEnviar match {
        case 0 => {
          println("Enviado ao node1");
          if (op1 != null) {
            output1.writeObject(op1);
            output1.flush();
            println("Resolvido em node1");
          } else {
            println("Node1 não sabe servolver! ");
            println("Enviado a outro node diferente ");
            output3.writeObject(op2);
            output3.flush();
            println("Resolvido em node3");
          }
        }
        case 1 => {
          println("Enviando ao node2");
          if (op1 != null) {

            output2.writeObject(op1);
            output2.flush();
            println("Resolvido em node2");

          } else {
            println("Node2 não sabe servolver! ");
            println("Escolha outro node diferente");
            output3.writeObject(op2);
            output3.flush();
          }
        }
        case 2 => {
          println("Enviando a node3");
          if (op2 != null) {
            output3.writeObject(op2);
            output3.flush();
            println("Resovido em node3");
          } else {
            println("Node3 não sabe resolver");
            println("Selecionando node1 ou node2 para resolver");
            if (opEnviar == 0) {
              println("Enviando para node1");
              output1.writeObject(op1);
              output1.flush();
              println("Resolvido em node1");
            } else {
              println("Enviando para node2");
              output2.writeObject(op1);
              output2.flush();
              println("Resolvido em node2");
            }

          }

        }
      }

      var s = new Scanner(System.in);
      println("------------ \n Menu \n 1 para execultar novamente; \n 0 para encerra. ");
      print("Digite: ");
      escolha = s.nextInt();
      println("__________________");

    }

    output1.close();
    output2.close();
    output3.close();

    node1.close();
    node2.close();
    node3.close();


  }

}
