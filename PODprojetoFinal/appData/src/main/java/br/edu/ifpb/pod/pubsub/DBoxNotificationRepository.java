package br.edu.ifpb.pod.pubsub;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author ajp
 */
public class DBoxNotificationRepository {

    private static final String ACCESS_TOKEN = "F1OxNZVYehAAAAAAAAAAC3CmvVpYA_6LjrJHVXLaceZPPR-NA7pYm5jBSZOKoaaC";
    private DbxRequestConfig config;
    private DbxClientV2 client;

    public DBoxNotificationRepository() throws ClassNotFoundException, IOException {
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
}
