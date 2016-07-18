package br.com.rodrigo.calcularIdade;

import br.com.rodrigo.calcularIdade.model.Pessoa;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgrodrigo on 17/07/16.
 */
public class EnviaParaCalculoDataWriter implements ItemWriter<Pessoa> {

    private ExecutionContext executionContext;

    public void write(List<? extends Pessoa> list) throws Exception {
        List<String> pessoas = (List<String>) executionContext.get("pessoas");
        if(pessoas == null) {
            executionContext.put("pessoas", list);
        }else{
            pessoas.addAll(pessoas);
        }
    }

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }

    public void setExecutionContext(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }
}
