package br.edu.ifpr.agenda.model;

import java.util.List;

public class Convidado extends Pessoa {
    private int idConvidado;
    
    private List<Pessoa> idPessoas;
    public int getIdConvidado() {
        return idConvidado;
    }
    public void setIdConvidado(int idConvidado) {
        this.idConvidado = idConvidado;
    }

    public List<Pessoa> getIdPessoas() {
        return idPessoas;
    }
}
