package br.com.rodrigo.calcularIdade;

import org.springframework.beans.factory.FactoryBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by dgrodrigo on 17/07/16.
 */
public class BuscarPessoasFactoryData implements FactoryBean<ResultSet> {

    private DataSource dataSource;

    public ResultSet getObject() throws Exception {
        Connection connection = dataSource.getConnection();
        Statement st = connection.createStatement();

        return st.executeQuery("select * from pessoas");
    }

    public Class<?> getObjectType() {
        return ResultSet.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
