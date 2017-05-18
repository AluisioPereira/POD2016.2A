/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

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
public class DaoPessoaPostgres {

    private Connection conexao;

    public DaoPessoaPostgres() throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        this.conexao = new ConexaoFabrica().getConectionPostgres();
    }

    /**
     * Método responsável pelo relizar uma busca de todas as pessoas presente no
     * banco retornando assim uma lista de pessoas caso não exista pessoas no
     * banco o mesmo retorna a lista vazia.
     */
    public ArrayList<Pessoa> listarTodos() {
        String sql = "SELECT * FROM pessoa";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
            while (rs.next()) {
                Pessoa p = montarPessoa(rs);
                pessoas.add(p);
            }
            ps.close();
            return pessoas;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Pessoa montarPessoa(ResultSet rs) {
        Pessoa pessoa;
        try {
            pessoa = new Pessoa();
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            pessoa = null;
        }
        return pessoa;
    }

}
