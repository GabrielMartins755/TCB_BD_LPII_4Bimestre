package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.agenda.model.Evento;
import br.edu.ifpr.agenda.model.Funcionario;
import br.edu.ifpr.agenda.model.Pessoa;

public class EventoDAO {

    private Connection con;

    public EventoDAO(Connection con) {
        this.con = con;
    }

    public int inserir(Evento e) throws SQLException {
        String sql = "INSERT INTO evento (nome_evento, data_evento, hora_evento, local_evento, qtd_max_pessoas) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, e.getNomeEvento());
            ps.setDate(2, Date.valueOf(e.getData().toLocalDate()));
            ps.setTime(3, Time.valueOf(e.getData().toLocalTime()));
            ps.setString(4, e.getLocal());
            ps.setInt(5, e.getQtdMaxPessoas());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public void atualizar(Evento e, int idEvento) throws SQLException {
        String sql = """
                UPDATE evento 
                SET nome_evento=?, data_evento=?, hora_evento=?, local_evento=?, qtd_max_pessoas=?
                WHERE id_evento=?
                """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNomeEvento());
            ps.setDate(2, Date.valueOf(e.getData().toLocalDate()));
            ps.setTime(3, Time.valueOf(e.getData().toLocalTime()));
            ps.setString(4, e.getLocal());
            ps.setInt(5, e.getQtdMaxPessoas());
            ps.setInt(6, idEvento);
            ps.executeUpdate();
        }
    }

    public Evento buscarPorId(int idEvento) throws SQLException {
        String sql = "SELECT * FROM evento WHERE id_evento=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEvento);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Evento e = new Evento();

                    LocalDate data = rs.getDate("data_evento").toLocalDate();
                    LocalTime hora = rs.getTime("hora_evento").toLocalTime();

                    e.setIdEvento(idEvento);
                    e.setNomeEvento(rs.getString("nome_evento"));
                    e.setLocal(rs.getString("local_evento"));
                    e.setHora(hora);
                    e.setQtdMaxPessoas(rs.getInt("qtd_max_pessoas"));
                    e.setData(LocalDateTime.of(data, hora));

                    e.setConvidados(buscarConvidadosDoEvento(idEvento));
                    e.setFuncionarios(buscarFuncionariosDoEvento(idEvento));

                    return e;
                }
            }
        }
        return null;
    }

    public List<Evento> listarTodos() throws SQLException {
        List<Evento> eventos = new ArrayList<>();

        String sql = "SELECT * FROM evento";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Evento e = new Evento();
                LocalDate data = rs.getDate("data_evento").toLocalDate();
                LocalTime hora = rs.getTime("hora_evento").toLocalTime();

                e.setIdEvento(rs.getInt("id_evento"));
                e.setNomeEvento(rs.getString("nome_evento"));
                e.setLocal(rs.getString("local_evento"));
                e.setQtdMaxPessoas(rs.getInt("qtd_max_pessoas"));
                e.setData(LocalDateTime.of(data, hora));

                eventos.add(e);
            }
        }

        return eventos;
    }

    public void remover(int idEvento) throws SQLException {
        String sql = "DELETE FROM evento WHERE id_evento=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEvento);
            ps.executeUpdate();
        }
    }

    public boolean removerPessoa(int idEvento, int idPessoa) throws SQLException {
        String sqlConvidado = """
        DELETE ce FROM convidado_evento ce
        JOIN convidado c ON ce.id_convidado = c.id_convidado
        WHERE ce.id_evento = ? AND c.id_pessoa = ?
    """;

        try (PreparedStatement ps = con.prepareStatement(sqlConvidado)) {
            ps.setInt(1, idEvento);
            ps.setInt(2, idPessoa);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        }

        String sqlFuncionario = """
        DELETE fe FROM funcionario_evento fe
        JOIN funcionario f ON fe.id_funcionario = f.id_funcionario
        WHERE fe.id_evento = ? AND f.id_pessoa = ?
    """;

        try (PreparedStatement ps = con.prepareStatement(sqlFuncionario)) {
            ps.setInt(1, idEvento);
            ps.setInt(2, idPessoa);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        }

        return false;
    }

    public boolean removerPessoaPorNome(int idEvento, String nomePessoa) throws SQLException {

        String sqlConvidado = """
        SELECT p.id_pessoa
        FROM pessoa p
        JOIN convidado c ON p.id_pessoa = c.id_pessoa
        JOIN convidado_evento ce ON ce.id_convidado = c.id_convidado
        WHERE ce.id_evento = ? AND p.nome = ?
    """;

        try (PreparedStatement ps = con.prepareStatement(sqlConvidado)) {
            ps.setInt(1, idEvento);
            ps.setString(2, nomePessoa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idPessoa = rs.getInt("id_pessoa");
                return this.removerPessoa(idEvento, idPessoa); // CORRIGIDO
            }
        }

        String sqlFuncionario = """
        SELECT p.id_pessoa
        FROM pessoa p
        JOIN funcionario f ON p.id_pessoa = f.id_pessoa
        JOIN funcionario_evento fe ON fe.id_funcionario = f.id_funcionario
        WHERE fe.id_evento = ? AND p.nome = ?
    """;

        try (PreparedStatement ps = con.prepareStatement(sqlFuncionario)) {
            ps.setInt(1, idEvento);
            ps.setString(2, nomePessoa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idPessoa = rs.getInt("id_pessoa");
                return this.removerPessoa(idEvento, idPessoa);
            }
        }

        return false;
    }

    public void adicionarConvidado(int idEvento, int idConvidado) throws SQLException {
        String sql = "INSERT INTO convidado_evento (id_convidado, id_evento) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idConvidado);
        ps.setInt(2, idEvento);
        ps.executeUpdate();
    }

    public List<Pessoa> buscarConvidadosDoEvento(int idEvento) throws SQLException {
        List<Pessoa> convidados = new ArrayList<>();

        String sql = """
                SELECT p.id_pessoa, p.nome, p.cpf, p.telefone, p.email, p.dt_nascimento
                FROM pessoa p
                JOIN convidado c ON p.id_pessoa = c.id_pessoa
                JOIN convidado_evento ce ON ce.id_convidado = c.id_convidado
                WHERE ce.id_evento = ?
                """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEvento);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pessoa p = new Pessoa();
                    p.setIdPessoa(rs.getInt("id_pessoa"));
                    p.setNome(rs.getString("nome"));
                    p.setCpf(rs.getString("cpf"));
                    p.setTelefone(rs.getString("telefone"));
                    convidados.add(p);
                }
            }
        }

        return convidados;
    }

    public void adicionarFuncionario(int idEvento, int idFuncionario) throws SQLException {
        String sql = "INSERT INTO funcionario_evento (id_funcionario, id_evento) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFuncionario);
            ps.setInt(2, idEvento);
            ps.executeUpdate();
        }
    }

    public List<Funcionario> buscarFuncionariosDoEvento(int idEvento) throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();

        String sql = """
                SELECT f.id_funcionario, p.id_pessoa, p.nome, p.cpf, p.telefone, f.funcao, f.salario
                FROM funcionario f
                JOIN pessoa p ON p.id_pessoa = f.id_pessoa
                JOIN funcionario_evento fe ON fe.id_funcionario = f.id_funcionario
                WHERE fe.id_evento = ?
                """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEvento);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Funcionario f = new Funcionario();
                    // f.setIdFuncionario(rs.getInt("id_funcionario"));
                    f.setFuncao(rs.getString("funcao"));
                    f.setSalario(rs.getInt("salario"));
                    f.setIdPessoa(rs.getInt("id_pessoa"));
                    f.setNome(rs.getString("nome"));
                    f.setCpf(rs.getString("cpf"));
                    f.setTelefone(rs.getString("telefone"));

                    funcionarios.add(f);
                }
            }
        }

        return funcionarios;
    }
}
