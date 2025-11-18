package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifpr.agenda.model.Evento;
import br.edu.ifpr.agenda.model.Pessoa;
import br.edu.ifpr.agenda.model.Funcionario;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private Connection con;

    public EventoDAO(Connection con) {
        this.con = con;
    }

   //inserir evento
    public int inserir(EventoDAO e) throws SQLException {
        String sql = "INSERT INTO evento (nome_evento, data_evento, hora_evento, local_evento, qtd_max_pessoas) " +
                "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, e.getNomeEvento());
        ps.setString(2, e.getLocal());
        ps.setInt(3, e.getQtdMaxPessoas());
        ps.setDate(4, Date.valueOf(e.getData().toLocalDate()));
        ps.setTime(5, Time.valueOf(e.getData().toLocalTime()));

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // retorna id do evento
        }

        return -1;
    }

   //atualiza evento
    public void atualizar(EventoDAO e, int idEvento) throws SQLException {

        String sql = "UPDATE evento SET nome_evento=?, data_evento=?, hora_evento=?, local_evento=?, qtd_max_pessoas=? " +
                "WHERE id_evento=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, e.getNomeEvento());
        ps.setDate(2, Date.valueOf(e.getData().toLocalDate())); //valueOf converte LocalDate para Date 
        ps.setTime(3, Time.valueOf(e.getData().toLocalTime()));//valueOf converte LocalTime para Time
        ps.setString(4, e.getLocal());
        ps.setInt(5, e.getQtdMaxPessoas());
        ps.setInt(6, idEvento);

        ps.executeUpdate();
    }

    //busca o evento apartir do id
    public EventoDAO buscarPorId(int idEvento) throws SQLException {

        String sql = "SELECT * FROM evento WHERE id_evento=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idEvento);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            EventoDAO e = new EventoDAO();

            LocalDate data = rs.getDate("data_evento").toLocalDate();
            LocalTime hora = rs.getTime("hora_evento").toLocalTime();
            LocalDateTime dataHora = LocalDateTime.of(data, hora);

            e.setData(dataHora);
            e.setLocal(rs.getString("local_evento"));
            e.setNomeEvento(rs.getString("nome_evento"));
            e.setQtdMaxPessoas(rs.getInt("qtd_max_pessoas"));

            // carregar convidados
            e.setConvidados(buscarConvidadosDoEvento(idEvento));

            // carregar funcionários
            e.setFuncionarios(buscarFuncionariosDoEvento(idEvento));

            return e;
        }

        return null;
    }

   //listar todos os eventos
    public List<EventoDAO> listarTodos() throws SQLException {
        List<EventoDAO> eventos = new ArrayList<>();

        String sql = "SELECT * FROM evento";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EventoDAO e = new EventoDAO(con);

            LocalDate data = rs.getDate("data_evento").toLocalDate();
            LocalTime hora = rs.getTime("hora_evento").toLocalTime();
            e.setData(LocalDateTime.of(data, hora));

            e.setLocal(rs.getString("local_evento"));
            e.setNomeEvento(rs.getString("nome_evento"));
            e.setQtdMaxPessoas(rs.getInt("qtd_max_pessoas"));

            eventos.add(e);
        }

        return eventos;
    }

    // remover evento
    public void remover(int idEvento) throws SQLException {
        String sql = "DELETE FROM evento WHERE id_evento=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idEvento);
        ps.executeUpdate();
    }

    //associa convidado a evento
    public void adicionarConvidado(int idEvento, int idConvidado) throws SQLException {

        String sql = "INSERT INTO convidado_evento (id_convidado, id_evento) VALUES (?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idConvidado);
        ps.setInt(2, idEvento);

        ps.executeUpdate();
    }

   //lista convidados de um evento
    public List<Pessoa> buscarConvidadosDoEvento(int idEvento) throws SQLException {
        List<Pessoa> convidados = new ArrayList<>();

        String sql = """
                SELECT p.id_pessoa, p.nome, p.cpf, p.telefone
                FROM pessoa p
                JOIN convidado c ON p.id_pessoa = c.id_pessoa
                JOIN convidado_evento ce ON ce.id_convidado = c.id_convidado
                WHERE ce.id_evento = ?
                """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idEvento);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Pessoa p = new Pessoa();
            p.setIdPessoa(rs.getInt("id_pessoa"));
            p.setNome(rs.getString("nome"));
            p.setCpf(rs.getString("cpf"));
            p.setTelefone(rs.getString("telefone"));
            convidados.add(p);
        }

        return convidados;
    }

    //associa funcionário a evento
    public void adicionarFuncionario(int idEvento, int idFuncionario) throws SQLException {
        String sql = "INSERT INTO funcionario_evento (id_funcionario, id_evento) VALUES (?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idFuncionario);
        ps.setInt(2, idEvento);

        ps.executeUpdate();
    }

   //lista funcionarios de um evento
    public List<Funcionario> buscarFuncionariosDoEvento(int idEvento) throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();

        String sql = """
                SELECT f.id_funcionario, p.id_pessoa, p.nome, p.cpf, p.telefone, f.funcao, f.salario
                FROM funcionario f
                JOIN pessoa p ON p.id_pessoa = f.id_pessoa
                JOIN funcionario_evento fe ON fe.id_funcionario = f.id_funcionario
                WHERE fe.id_evento = ?
                """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idEvento);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Funcionario f = new Funcionario();

            f.setIdFuncionario(rs.getInt("id_funcionario"));
            f.setFuncao(rs.getString("funcao"));
            f.setSalario(rs.getInt("salario"));
            f.setIdPessoa(rs.getInt("id_pessoa"));
            f.setNome(rs.getString("nome"));
            f.setCpf(rs.getString("cpf"));
            f.setTelefone(rs.getString("telefone"));

            funcionarios.add(f);
        }

        return funcionarios;
    }
}
 