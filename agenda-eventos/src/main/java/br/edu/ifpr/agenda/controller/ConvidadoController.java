package br.edu.ifpr.agenda.controller;

import java.sql.Connection;
import java.util.List;

import br.edu.ifpr.agenda.model.dao.ConnectionFactory;
import br.edu.ifpr.agenda.model.dao.ConvidadoDAO;

public class ConvidadoController {
    private ConvidadoDAO ConvidadoDAO;

    public ConvidadoController() {
        Connection con = ConnectionFactory.getConnection();
        ConvidadoDAO = new ConvidadoDAO(con);
    }

    public int cadastrarConvidado(String nomePessoa) {
        try {
            return ConvidadoDAO.inserirPessoa(nomePessoa);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar convidado: " + e.getMessage());
        }
        return -1;
    }

    public List<Integer> listarConvidados() {
        try {
            return ConvidadoDAO.listarTodosIds();
        } catch (Exception e) {
            System.out.println("Erro ao listar convidados: " + e.getMessage());
        }
        return null;
    }
}
