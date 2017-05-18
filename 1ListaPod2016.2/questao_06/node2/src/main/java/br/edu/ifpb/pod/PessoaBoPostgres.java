
package br.edu.ifpb.pod;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ajp
 */
public class PessoaBoPostgres {

    private DaoPessoaPostgres daoPessoa;


    /**
     * Método responsavel por realizar a busta de todos as pessoas presente no banco
     *
     * @return
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ArrayList<Pessoa> listarTodos() throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        this.daoPessoa = new DaoPessoaPostgres();
        return daoPessoa.listarTodos();
    }



}
