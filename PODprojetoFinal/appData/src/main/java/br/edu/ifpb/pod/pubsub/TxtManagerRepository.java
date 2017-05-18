/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.pubsub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author ajp
 */
public class TxtManagerRepository {

    private File file;

    public TxtManagerRepository(String uuid) throws ClassNotFoundException, IOException {
        file = new File(uuid + ".txt");
        // Se o arquivo nao existir, ele é gerado.
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void criar(GrupoIF g, Notification n) throws IOException {

        // Prepara para escrever no arquivo
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        PrintWriter pw = new PrintWriter(fw);
        pw.print(g + ";##");
        pw.print(n.msg.getFrom() + ";##");
        GregorianCalendar gc = new GregorianCalendar();
        pw.print(gc.getTime() + ";##");
        Timestamp ts = new Timestamp(gc.getTimeInMillis());
        pw.print(ts.getDate() + ";##");
        pw.print(ts.getHours() + ";##");
        pw.println(n.msg.getText() + ";##");
        pw.flush();
        pw.close();

    }

    public ArrayList<Notification> listNotifications(String uuid) throws IOException {
        // Le o arquivo
        FileReader ler = new FileReader("test.txt");
        BufferedReader reader = new BufferedReader(ler);
        String linha;
        ArrayList<Notification> nots = new ArrayList<>();
        Notification e = new Notification();
        while ((linha = reader.readLine()) != null) {
            System.out.println(linha);
            e.msg.setFrom(uuid);
            e.msg.setText(linha);
            nots.add(e);
        }
        return nots;
    }

    public ArrayList<Notification> listaNotifications(String uuid) throws FileNotFoundException, IOException {
        if (file.getName() == uuid) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha = "";
            List<String> result = new ArrayList<String>();

            while ((linha = bufferedReader.readLine()) != null) {
                System.out.println(linha);
                if (linha != null && !linha.isEmpty()) {
                    result.add(linha);
                }
            }
            fileReader.close();
            bufferedReader.close();
            ArrayList<Notification> rl = new ArrayList<>();
            for (String s : result) {
                String[] notications = s.split(";##");
                Notification n = new Notification();
                n.msg.setFrom(String.valueOf(notications[0]));
                n.msg.setText(String.valueOf(notications[6]));
                n.subs.add(String.valueOf(notications[1]));

                rl.add(n);
                System.out.println(n.toString());
            }
            return rl;
        }
        return null;

    }

    public void removeNotifications(String uuid) throws FileNotFoundException, IOException {
        if (file.getName() == uuid) {
            boolean success = file.delete();
        } else {
            System.out.println("Não foi possível encontrar arquivo com o nome especificado.");
        }

    }

}
