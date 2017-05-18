
package br.edu.ifpb.pod;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 *
 * @author ajp
 */
public class PessoaBoPostgres {

    private DaoPessoaPostgres daoPessoa;

    public boolean adicionarPessoa(Pessoa pessoa) throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        this.daoPessoa = new DaoPessoaPostgres();
        return daoPessoa.addPessoa(pessoa);
    }

    public boolean removerPessoa(Pessoa pessoa) throws IOException, URISyntaxException, ClassNotFoundException, SQLException {
        this.daoPessoa = new DaoPessoaPostgres();
        return daoPessoa.removerPessoa(pessoa);
    }

}
