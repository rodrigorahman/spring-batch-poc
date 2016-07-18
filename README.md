## POC com Spring-Batch ##

Nessa POC fazemos a busca dos dados de pessoas e calculamos a sua idade


O arquivo do banco de dados está em resource
```
database.sql
```

Criar um banco de dados com o nome:

 ***spring_batch_poc***

## Para Rodar o projeto

Execute o Main na classe:

```
br.com.rodrigo.App
```

***O arquivo de configuração é:***

```
job-hello-world.xml
```
A confiração do JOB:

```
<batch:job id="CalcularIdadePessoas">
        <batch:step id="step1_buscar_pessoas" next="step2_calcular_idades" >
            <batch:tasklet>
                <batch:chunk reader="buscarPessoasItemReader" writer="enviaParaCalculoItemWriter"
                             processor="converterPessoaItemProcessor" commit-interval="100">
                </batch:chunk>
            </batch:tasklet>
        </batch:step>
        <batch:step id="step2_calcular_idades">
            <batch:tasklet task-executor="taskExecutor">
                <batch:chunk reader="recuperarPessoasParaCalculoItemReader" writer="imprimirIdadeItemWriter"
                             processor="calculaIdadeItemProcess" commit-interval="100">
                </batch:chunk>
            </batch:tasklet>
        </batch:step>

    </batch:job>

    <bean id="taskExecutor" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
        <property name="corePoolSize" value="4"/>
        <property name="maxPoolSize" value="200"/>
        <property name="queueCapacity" value="25"/>
    </bean>
```


Para teste utilizamos o reader ***AbstractItemCountingItemStreamItemReader*** porem poderia ser utilizado o ***JdbcCursorItemReader***

```
<bean id="itemReader" class="org.spr...JdbcCursorItemReader">
    <property name="dataSource" ref="dataSource"/>
    <property name="sql" value="select ID, NAME, CREDIT from CUSTOMER"/>
    <property name="rowMapper">
        <bean class="org.springframework.batch.sample.domain.CustomerCreditRowMapper"/>
    </property>
</bean>
```

Porem para testes utilizamos o AbstractItemCountingItemStreamItemReader para personalizar meu Reader


## Comunicação entre os Steps

Para comunicação entre os Steps utilizamos o ExecutionContext no write do primeiro step gravamos execution para recuperacao posterior :


***Para injetar os executionContext***
```
<property name="executionContext"
          value="#{stepExecution.jobExecution.executionContext}">
 </property>
```

```
  List<String> pessoas = (List<String>) executionContext.get("pessoas");
        if(pessoas == null) {
            executionContext.put("pessoas", list);
        }else{
            pessoas.addAll(pessoas);
        }
```

***E o no reader do step posterior recuperamos da seguinte forma:***

```
<bean id="getPessoasParaCalculo" scope="step" class="br.com.rodrigo.calcularIdade.GetDataInExecutorProcess">
        <property name="executionContext" value="#{stepExecution.jobExecution.executionContext}"></property>
        <property name="key" value="pessoas" ></property>
    </bean>
```

***Criamos uma factoryBean buscar os dados do executionContext assim com o mesmo Reader podemos buscar varios dados diferentes***

```
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
```
