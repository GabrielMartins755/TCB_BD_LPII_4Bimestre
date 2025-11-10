package br.edu.ifpr.agenda.model;

public class Funcionario extends Pessoa{
    private String funcao;
    private String numBanco;
    private String salario;
    
    public Funcionario(){
    }
    public String getFuncao() {
        return funcao;
    }
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    public String getNumBanco() {
        return numBanco;
    }
    public void setNumBanco(String numBanco) {
        this.numBanco = numBanco;
    }
    public String getSalario() {
        return salario;
    }
    public void setSalario(String salario) {
        this.salario = salario;
    }
}