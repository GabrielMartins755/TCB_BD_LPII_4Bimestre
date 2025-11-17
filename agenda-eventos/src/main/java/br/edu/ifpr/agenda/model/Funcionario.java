package br.edu.ifpr.agenda.model;

public class Funcionario extends Pessoa{
    private String funcao;
    private String numBanco;
    private int salario;
    
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
    public int getSalario() {
        return salario;
    }
    public void setSalario(int salario) {
        this.salario = salario;
    }
    public void setIdFuncionario(int int1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIdFuncionario'");
    }
}
