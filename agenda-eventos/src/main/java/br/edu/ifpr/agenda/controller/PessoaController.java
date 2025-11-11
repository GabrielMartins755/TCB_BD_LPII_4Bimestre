package br.edu.ifpr.agenda.controller;

import br.edu.ifpr.agenda.model.Pessoa;
import br.edu.ifpr.agenda.model.dao.PessoaDAO;

public class PessoaController {
    private PessoaDAO dao;

    public PessoaController() {
        this.dao = new PessoaDAO();
    }

    public void cadastrarPessoa(Pessoa pessoa){
        if(pessoa.getNome() == null  || pessoa.getNome().isEmpty()){
            System.out.println("Nome não pode ser vazio");
            return;
        }

        dao.salvarPessoa(pessoa);
        
    }

    

}
