package br.edu.ifpr.agenda.controller;

import br.edu.ifpr.agenda.model.Evento;
import br.edu.ifpr.agenda.model.Pessoa;
import br.edu.ifpr.agenda.model.dao.ConnectionFactory;
import br.edu.ifpr.agenda.model.dao.ConvidadoDAO;
import br.edu.ifpr.agenda.model.dao.EventoDAO;
import br.edu.ifpr.agenda.model.dao.FuncionarioDAO;
import br.edu.ifpr.agenda.model.Funcionario;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class EventoController {

    private EventoDAO eventoDAO;
    private ConvidadoDAO convidadoDAO;
    private FuncionarioDAO funcionarioDAO;

    public EventoController() {
        Connection con = ConnectionFactory.getConnection();
        eventoDAO = new EventoDAO(con);
        convidadoDAO = new ConvidadoDAO(con);
        funcionarioDAO = new FuncionarioDAO(con);
    }

    // Criar evento
    public int criarEvento(String nome, LocalDateTime data, String local, int max) {
        try {
            Evento e = new Evento();
            e.setNomeEvento(nome);
            e.setData(data);
            e.setLocal(local);
            e.setQtdMaxPessoas(max);

            return eventoDAO.inserir(e);

        } catch (Exception ex) {
            System.out.println("Erro ao criar evento: " + ex.getMessage());
        }
        return -1;
    }

    // Buscar evento
    public Evento buscarEvento(int id) {
        try {
            return eventoDAO.buscarPorId(id);
        } catch (Exception ex) {
            System.out.println("Erro ao buscar evento: " + ex.getMessage());
        }
        return null;
    }

    // Listar eventos
    public List<Evento> listarEventos() {
        try {
            return eventoDAO.listarTodos();
        } catch (Exception e) {
            System.out.println("Erro ao listar eventos: " + e.getMessage());
        }
        return null;
    }

    // Atualizar
    public void atualizarEvento(int id, Evento eventoAtualizado) {
        try {
            eventoDAO.atualizar(eventoAtualizado, id);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar evento: " + e.getMessage());
        }
    }

    // Remover
    public void removerEvento(int id) {
        try {
            eventoDAO.remover(id);
        } catch (Exception e) {
            System.out.println("Erro ao remover evento: " + e.getMessage());
        }
    }

    // Adicionar convidado
    public void adicionarConvidado(int idEvento, int idPessoa) {
        try {
            int idConvidado = convidadoDAO.inserir(idPessoa);
            eventoDAO.adicionarConvidado(idEvento, idConvidado);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar convidado: " + e.getMessage());
        }
    }

    // Adicionar funcionário
    public void adicionarFuncionario(int idEvento, int idFuncionario) {
        try {
            eventoDAO.adicionarFuncionario(idEvento, idFuncionario);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar funcionário: " + e.getMessage());
        }
    }

    // Listar convidados do evento
    public List<Pessoa> listarConvidados(int idEvento) {
        try {
            return eventoDAO.buscarConvidadosDoEvento(idEvento);
        } catch (Exception e) {
            System.out.println("Erro ao listar convidados: " + e.getMessage());
        }
        return null;
    }

    // Listar funcionários do evento
    public List<Funcionario> listarFuncionarios(int idEvento) {
        try {
            return eventoDAO.buscarFuncionariosDoEvento(idEvento);
        } catch (Exception e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }
        return null;
    }
}
