package br.com.rodrigo.calcularIdade.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dgrodrigo on 17/07/16.
 */
public class Pessoa implements Serializable {

    private int id;
    private String nome;
    private Date dataNascimento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
