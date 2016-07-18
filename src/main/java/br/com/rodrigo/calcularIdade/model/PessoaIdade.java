package br.com.rodrigo.calcularIdade.model;

import java.io.Serializable;

/**
 * Created by dgrodrigo on 17/07/16.
 */
public class PessoaIdade implements Serializable{

    private String nome;
    private int idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
