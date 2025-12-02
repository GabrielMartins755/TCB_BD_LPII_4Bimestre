package br.edu.ifpr.agenda.controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.edu.ifpr.agenda.model.Evento;
import br.edu.ifpr.agenda.model.Funcionario;
import br.edu.ifpr.agenda.model.Pessoa;
import br.edu.ifpr.agenda.model.dao.ConnectionFactory;
import br.edu.ifpr.agenda.model.dao.ConvidadoDAO;
import br.edu.ifpr.agenda.model.dao.EventoDAO;
import br.edu.ifpr.agenda.model.dao.FuncionarioDAO;
import br.edu.ifpr.agenda.model.dao.PessoaDAO;

public class EventoController {
    private PessoaDAO pessoaDAO;
    private EventoDAO eventoDAO;
    private ConvidadoDAO convidadoDAO;
    private FuncionarioDAO funcionarioDAO;
    private FuncionarioController funcionarioController;

    public EventoController() {
        Connection con = ConnectionFactory.getConnection();
        pessoaDAO = new PessoaDAO(con);
        eventoDAO = new EventoDAO(con);
        convidadoDAO = new ConvidadoDAO(con);
        funcionarioDAO = new FuncionarioDAO(con);
        funcionarioController = new FuncionarioController();
    }

    public int cadastrarEvento(String nome, String data, String hora, String local, int max) {
        try {
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
            LocalDate dataConvertida = LocalDate.parse(data, formatoData);
            LocalTime horaConvertida = LocalTime.parse(hora, formatoHora);
            LocalDateTime dataCompleta = LocalDateTime.of(dataConvertida, horaConvertida);

            Evento e = new Evento();
            e.setNomeEvento(nome);
            e.setData(dataCompleta);
            e.setHora(horaConvertida);
            e.setLocal(local);
            e.setQtdMaxPessoas(max);
            
            return eventoDAO.inserir(e);
        } catch (Exception ex) {
            System.out.println("Erro ao criar evento: " + ex.getMessage());
            return -1;
        }
    }
    
    public void buscarEvento(int id) {
        try {
            Evento e = eventoDAO.buscarPorId(id);

            if (e == null) {
                System.out.println("Evento não encontrado!");
                return;
            }

            System.out.println("\n=== Detalhes do evento ===");
            System.out.println("ID: " + e.getIdEvento());
            System.out.println("Nome: " + e.getNomeEvento());
            System.out.println("Data: " + e.getData());
            System.out.println("Hora: " + e.getHora());
            System.out.println("Local: " + e.getLocal());
            System.out.println("Máx. pessoas: " + e.getQtdMaxPessoas());

            System.out.println("\nConvidados:");
            e.getConvidados().forEach(c -> System.out.println("- " + c.getNome()));

            System.out.println("\nFuncionários:");
            e.getFuncionarios().forEach(f -> System.out.println("- " + f.getNome()));

        } catch (Exception ex) {
            System.out.println("Erro ao buscar evento: " + ex.getMessage());
        }
    }

    public void listarEventos() {
        try {
            List<Evento> eventos = eventoDAO.listarTodos();

            if (eventos.isEmpty()) {
                System.out.println("Nenhum evento encontrado.");
            }

            System.out.println("\n=== EVENTOS CADASTRADOS ===");
            for (Evento e : eventos) {
                System.out.println(e);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar eventos: " + e.getMessage());
        }
    }

    public void atualizarEvento(int id, Evento eventoAtualizado) {
        try {
            eventoDAO.atualizar(eventoAtualizado, id);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar evento: " + e.getMessage());
        }
    }

    public void removerEvento(int id) {
        try {
            eventoDAO.remover(id);
        } catch (Exception e) {
            System.out.println("Erro ao remover evento: " + e.getMessage());
        }
    }
    public void adicionarConvidado(int idEvento, String nomeConv, String dtNascimento, String cpf, String telefone, String email) {
        try {
            int idPessoa = pessoaDAO.inserirPessoa(nomeConv, dtNascimento, cpf, telefone, email);
            int idConvidado = convidadoDAO.inserirConvidado(idPessoa);
            
            eventoDAO.adicionarConvidado(idEvento, idConvidado);

            System.out.println("Convidado adicionado ao evento!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar convidado: " + e.getMessage());
        }
    }
    
    public void adicionarFuncionario(int idEvento, String nomeFunc, String dtNascimento, String cpf, String telefone, String email, String funcao, int salario, String numBanco) {
        try {
            int idPessoa = pessoaDAO.inserirPessoa(nomeFunc, dtNascimento, cpf, telefone, email);
            int idFuncionario = funcionarioController.cadastrarFuncionario(idPessoa, funcao, numBanco, salario);

            eventoDAO.adicionarFuncionario(idEvento, idFuncionario);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar funcionário: " + e.getMessage());
        }
    }

    public void removerPessoa(int idEvento, int idPessoa) {
        try {
            boolean removido = eventoDAO.removerPessoa(idEvento, idPessoa);

            if (removido) {
                System.out.println("Pessoa removida do evento!");
            } else {
                System.out.println("Pessoa não estava inscrita no evento.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover pessoa: " + e.getMessage());
        }
    }
    
    public List<Pessoa> listarConvidados(int idEvento) {
        try {
            return eventoDAO.buscarConvidadosDoEvento(idEvento);
        } catch (Exception e) {
            System.out.println("Erro ao listar convidados: " + e.getMessage());
            return null;
        }
    }

    public List<Funcionario> listarFuncionarios(int idEvento) {
        try {
            return eventoDAO.buscarFuncionariosDoEvento(idEvento);
        } catch (Exception e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
            return null;
        }
    }
}
