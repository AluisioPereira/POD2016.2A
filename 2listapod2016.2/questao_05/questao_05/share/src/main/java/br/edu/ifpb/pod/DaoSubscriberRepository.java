package br.edu.ifpb.pod;

import br.edu.ifpb.pod.Notification;
import br.edu.ifpb.pod.Subscriber;
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
public class DaoSubscriberRepository {

    private Connection conexao;

    public DaoSubscriberRepository() throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        this.conexao = new ConexaoFabrica().getConectionPostgres();
    }

    public void store(String uuid, Subscriber s) {
        String sql = "INSERT INTO subscriber (uuid, subscribe) VALUES (?, ?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, uuid);
            ps.setString(2, s.toString());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public Subscriber find(String uuid) {
        Subscriber adesivo = null;
        String sql = "SELECT * FROM subscriber WHERE uuid = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                //     adesivo = montarSubscriber(rs);
            }
            ps.close();
            return adesivo;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> listUUIDs() {
        String sql = "SELECT * FROM  notification";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> ntfs = new ArrayList<>();
            while (rs.next()) {
                String ntf = new String(rs.getString("subscriber"));
                ntfs.add(ntf);
            }
            ps.close();
            return ntfs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

//    private Subscriber montarSubscriber(ResultSet rs) {
//        Subscriber ntf;
//        try {
//            ntf.update(msg);
//            ntf.msg.setFrom(rs.getString("from"));
//            ntf.msg.setText(rs.getString("text"));
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            ntf = null;
//        }
//        return ntf;
//    }
}
