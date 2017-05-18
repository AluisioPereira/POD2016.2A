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
     *
     * Método responsável por inserir no banco de dados as iformações relativa
     * ao objeto.
     */
    public boolean addPessoa(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (id, nome, sobrenome) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, pessoa.getId());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getSobrenome());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    /**
     * Método responsável pelo relizar o BUSCA de dado referente ao objeto
     * passado por paramento no banco de dados Seleciona o objeto a ser
     * consultado pelo idetificador (nome) e retorna um Pessoa construida caso a
     * operação bem sucessida e retornando null caso não foi encontrodo a
     * pessoa.
     */

    public Pessoa buscarNome(String nome) {
        Pessoa p = null;
        String sql = "SELECT * FROM pessoa WHERE nome=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                p = montarPessoa(rs);
            }
            ps.close();
            return p;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Método responsável pelo a montagem de dada pessoa recebida como resultset
     */
    private Pessoa montarPessoa(ResultSet rs) {
        Pessoa pessoa;
        try {
            pessoa = new Pessoa();
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setSobrenome(rs.getString("sobrenome"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            pessoa = null;
        }
        return pessoa;
    }

    /**
     * Método responsável pelo relizar o DELETE de dado referente ao objeto
     * passado por paramento no banco de dados Seleciona o ojeto a ser excluido
     * pelo idetificador (nome) ou (id) e retorna um boolean true(verdade) caso
     * operação bem sucessida false(falso) caso não foi encontrodo a pessoa.
     */
    public boolean removerPessoa(Pessoa pessoa) {
        String sql = "DELETE FROM pessoa WHERE nome = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
