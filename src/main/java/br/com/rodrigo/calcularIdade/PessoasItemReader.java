package br.com.rodrigo.calcularIdade;

import br.com.rodrigo.calcularIdade.model.Pessoa;
import br.com.rodrigo.calcularIdade.model.PessoaIdade;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by dgrodrigo on 17/07/16.
 */
public class PessoasItemReader extends AbstractItemCountingItemStreamItemReader<Pessoa> {

    private DataSource dataSource;
    private ResultSet resultSet;
    private Connection connection;

    public PessoasItemReader() {
        setName(getClass().getCanonicalName());
    }

    protected Pessoa doRead() throws Exception {

        if(resultSet.next()){
            Pessoa pessoa = new Pessoa();
            pessoa.setId(resultSet.getInt("id"));
            pessoa.setNome(resultSet.getString("nome"));
            pessoa.setDataNascimento(resultSet.getDate("data_nascimento"));
            return pessoa;
        }

        return null;
    }

    protected void doOpen() throws Exception {
        connection = dataSource.getConnection();
        Statement stCount = connection.createStatement();
        Statement st = connection.createStatement();

        ResultSet resultSetCount = stCount.executeQuery("select count(*) total from pessoas");
        resultSet = st.executeQuery("select * from pessoas");
        resultSetCount.next();
        setMaxItemCount(resultSetCount.getInt("total"));
        resultSetCount.close();
    }

    protected void doClose() throws Exception {
        resultSet.close();
        connection.close();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
