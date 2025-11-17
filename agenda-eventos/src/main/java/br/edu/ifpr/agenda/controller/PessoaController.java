package br.edu.ifpr.agenda.controller;

import br.edu.ifpr.agenda.model.Pessoa;
import br.edu.ifpr.agenda.model.dao.PessoaDAO;

public class PessoaController {
    private PessoaDAO dao;

    public PessoaController() {
        this.dao = new PessoaDAO(null);
    }

    public void cadastrarPessoa(Pessoa pessoa){
        if(pessoa.getNome() == null  || pessoa.getNome().isEmpty()){
            System.out.println("Nome não pode ser nulo");
            return;
        }
        if(pessoa.getCpf() == null  || pessoa.getCpf().isEmpty()){
            System.out.println("CPF não pode ser nulo");
            return;
        }
        if (pessoa.getTelefone() == null || pessoa.getTelefone().isEmpty()) {
            System.out.println("Telefone não pode de nulo");
        }
        if(pessoa.getEmail() == null  || pessoa.getEmail().isEmpty()){
            System.out.println("E-mail não pode ser nulo");
            return;
        }

        dao.salvarPessoa(pessoa);

    }
}
