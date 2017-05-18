package br.edu.ifpb.pod;

import br.edu.ifpb.pod.Notification;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ajp
 */
public class DaoNotificationRepository {

    private Connection conexao;

    public DaoNotificationRepository() throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        this.conexao = new ConexaoFabrica().getConectionPostgres();
    }

    public void store(Notification n) {
        String sql = "INSERT INTO notification (fromm, text,list) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, n.msg.getFrom());
            ps.setString(2, n.msg.getText());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<Notification> listNotifications(String uuid) {
        String sql = "SELECT * FROM  notification WHERE fromm=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            ArrayList<Notification> ntfs = new ArrayList<>();
            while (rs.next()) {
                Notification ntf = montarNotification(rs);
                ntfs.add(ntf);
            }
            ps.close();
            return ntfs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Notification montarNotification(ResultSet rs) {
        Notification ntf;
        try {
            ntf = new Notification();
            ntf.msg.setFrom(rs.getString("fromm"));
            ntf.msg.setText(rs.getString("text"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            ntf = null;
        }
        return ntf;
    }

    public void removerNotifications(String uuid) {
        String sql = "DELETE FROM notification WHERE fromm = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, uuid);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
