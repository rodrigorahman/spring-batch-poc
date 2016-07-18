package br.com.rodrigo.calcularIdade;

import br.com.rodrigo.calcularIdade.model.Pessoa;
import org.springframework.batch.item.ItemProcessor;

import java.sql.ResultSet;

/**
 * Created by dgrodrigo on 17/07/16.
 */
public class ConverterPessoaProcessor implements ItemProcessor<Pessoa, Pessoa> {

    public Pessoa process(Pessoa pessoa) throws Exception {
        return pessoa;
    }
}
