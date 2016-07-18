package br.com.rodrigo.calcularIdade;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.FactoryBean;

import java.util.List;

/**
 * Created by dgrodrigo on 17/07/16.
 */
public class GetDataInExecutorProcess implements FactoryBean<List<Object>> {
    private ExecutionContext executionContext;
    private String key;

    public List<Object> getObject() throws Exception {
        return (List<Object>) executionContext.get(key);
    }

    public Class<?> getObjectType() {
        return List.class;
    }

    public boolean isSingleton() {
        return false;
    }

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }

    public void setExecutionContext(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}