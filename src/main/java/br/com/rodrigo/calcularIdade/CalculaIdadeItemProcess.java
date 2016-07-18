package br.com.rodrigo.calcularIdade;

import br.com.rodrigo.calcularIdade.model.Pessoa;
import br.com.rodrigo.calcularIdade.model.PessoaIdade;
import org.springframework.batch.item.ItemProcessor;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by dgrodrigo on 17/07/16.
 */
public class CalculaIdadeItemProcess implements ItemProcessor<Pessoa, PessoaIdade> {

    public PessoaIdade process(Pessoa pessoa) throws Exception {

        Date dataNascimento = pessoa.getDataNascimento();

        Calendar c = Calendar.getInstance();
        c.setTime(dataNascimento);

        Calendar dataAtual = Calendar.getInstance();

        int idade = dataAtual.get(Calendar.YEAR) - c.get(Calendar.YEAR);

        if(c.get(Calendar.MONTH) > dataAtual.get(Calendar.MONDAY)){
            idade++;
        }else if(c.get(Calendar.MONTH) == dataAtual.get(Calendar.MONDAY)){
            if(c.get(Calendar.DATE) <= dataAtual.get(Calendar.DATE)){
                idade++;
            }
        }

        PessoaIdade pessoaIdade = new PessoaIdade();
        pessoaIdade.setNome(pessoa.getNome());
        pessoaIdade.setIdade(idade);

        return pessoaIdade;
    }
}
