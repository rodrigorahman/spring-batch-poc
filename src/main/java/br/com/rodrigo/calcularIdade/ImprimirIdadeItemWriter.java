package br.com.rodrigo.calcularIdade;

import br.com.rodrigo.calcularIdade.model.PessoaIdade;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by dgrodrigo on 17/07/16.
 */
public class ImprimirIdadeItemWriter implements ItemWriter<PessoaIdade> {
    public void write(List<? extends PessoaIdade> list) throws Exception {

        for (PessoaIdade pessoaIdade : list) {
            System.out.println("Idade do " + pessoaIdade.getNome() + " é " + pessoaIdade.getIdade());
        }

    }
}
