package br.edu.ifpb.pod.pubsub;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author ajp
 */
public class GDriveSubscriberRepository {

    //key = "AIzaSyCihzROaI-2iOwjDm11Q4wLDIDLi7lXUJY" //google drive
    // id cliente 729372619285-g1ql62agv3t5iiri891bk1mec5cg8jv4.apps.googleusercontent.com
    private static final String ACCESS_TOKEN = "F1OxNZVYehAAAAAAAAAAC3CmvVpYA_6LjrJHVXLaceZPPR-NA7pYm5jBSZOKoaaC";
    private DbxRequestConfig config;
    private DbxClientV2 client;

    public GDriveSubscriberRepository() throws ClassNotFoundException, IOException {
        config = new DbxRequestConfig("dropbox/podprojetofinal");
    }

    public void store(Notification n) throws IOException, DbxException {
        // Create Dropbox client
        config = new DbxRequestConfig("dropbox/podprojetofinal");
        client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }

        // Upload "test.txt" to Dropbox
        try (InputStream in = new FileInputStream("test.txt")) {
            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
                    .uploadAndFinish(in);
        }

    }

    public void addMensagem(Message m) throws IOException {
        // Prepara para escrever no arquivo
        FileWriter fw = new FileWriter("test.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.print("m" + ";##");
        pw.print(m.getId() + ";##");
        pw.print(m.getFrom() + ";##");
        pw.print(m.getData() + ";##");
        pw.print(m.getHora() + ";##");
        pw.print(m.getText() + ";##");
        pw.println(m.getIdGrupo() + ";##");
        pw.flush();
        pw.close();
    }

    public void addUsuario(Usuario u) throws IOException {
        // Prepara para escrever no arquivo
        FileWriter fw = new FileWriter("test.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.print("u" + ";##");
        pw.print(u.getId() + ";##");
        pw.print(u.getNome() + ";##");
        pw.print(u.getEmail() + ";##");
        pw.flush();
        pw.close();
    }

}
