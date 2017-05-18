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
        String sql = "INSERT INTO pessoa (nome) VALUES (?)";
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

    /**
     * Método responsável pelo relizar o DELETE de dado referente ao objeto
     * passado por paramento no banco de dados Seleciona o ojeto a ser excluido
     * pelo idetificador (nome) ou (id) e retorna um boolean true(verdade) caso operação
     * bem sucessida false(falso) caso não foi encontrodo a pessoa.
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
