package br.edu.ifpr.agenda.controller;

import java.sql.Connection;
import java.util.List;

import br.edu.ifpr.agenda.model.Funcionario;
import br.edu.ifpr.agenda.model.dao.ConnectionFactory;
import br.edu.ifpr.agenda.model.dao.FuncionarioDAO;

public class FuncionarioController {
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        Connection con = ConnectionFactory.getConnection();
        funcionarioDAO = new FuncionarioDAO(con);
    }

    public int cadastrarFuncionario(int idPessoa, String funcao, String banco, int salario) {
        try {
            Funcionario f = new Funcionario();
            f.setIdPessoa(idPessoa);
            f.setFuncao(funcao);
            f.setNumBanco(banco);
            f.setSalario(salario);

            return funcionarioDAO.inserir(f);

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
        return -1;
    }

    public Funcionario buscarFuncionario(int id) {
        try {
            return funcionarioDAO.buscarPorId(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
        }
        return null;
    }

    public List<Funcionario> listarFuncionarios() {
        try {
            return funcionarioDAO.listarTodos();
        } catch (Exception e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }
        return null;
    }
}
