package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.agenda.model.Pessoa;

public class PessoaDAO {

    private Connection con;

    public PessoaDAO(Connection con) {
        this.con = con;
    }

    public int buscarPessoaPorNome(String nome) throws SQLException {
        String sql = "SELECT id_pessoa FROM pessoa WHERE nome = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nome);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_pessoa");
        }

        return -1;
    }

    // ...existing code...
    public int inserirPessoa(String nome, String dtNascimento, String cpf, String telefone, String email) throws SQLException {
        // Primeiro verifica se já existe pessoa com esse CPF
        String sqlBusca = "SELECT id_pessoa FROM pessoa WHERE cpf = ?";
        try (PreparedStatement psBusca = con.prepareStatement(sqlBusca)) {
            psBusca.setString(1, cpf);
            try (ResultSet rs = psBusca.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_pessoa");
                }
            }
        }

        // Inserir nova pessoa
        String sql = "INSERT INTO pessoa (nome, dt_nascimento, cpf, telefone, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nome);
            ps.setString(2, dtNascimento);
            ps.setString(3, cpf);
            ps.setString(4, telefone);
            ps.setString(5, email);

            ps.executeUpdate();

            try (ResultSet rs2 = ps.getGeneratedKeys()) {
                if (rs2.next()) {
                    return rs2.getInt(1);
                }
            }
        }

        return -1;
    }
// ...existing code...

    public Pessoa buscarPorId(int idPessoa) throws SQLException {
        String sql = "SELECT * FROM pessoa WHERE id_pessoa=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idPessoa);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Pessoa p = new Pessoa();
            p.setIdPessoa(rs.getInt("id_pessoa"));
            p.setNome(rs.getString("nome"));
            p.setCpf(rs.getString("cpf"));
            p.setTelefone(rs.getString("telefone"));
            p.setEmail(rs.getString("email"));
            p.setDtNascimento(rs.getDate("dt_nascimento").toLocalDate());
            return p;
        }

        return null;
    }

    public List<Pessoa> listarTodas() throws SQLException {
        List<Pessoa> lista = new ArrayList<>();

        String sql = "SELECT * FROM pessoa";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Pessoa p = new Pessoa();
            p.setIdPessoa(rs.getInt("id_pessoa"));
            p.setNome(rs.getString("nome"));
            p.setCpf(rs.getString("cpf"));
            p.setTelefone(rs.getString("telefone"));
            p.setEmail(rs.getString("email"));
            p.setDtNascimento(rs.getDate("dt_nascimento").toLocalDate());
            lista.add(p);
        }

        return lista;
    }

    public void salvarPessoa(Pessoa pessoa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvarPessoa'");
    }
}
