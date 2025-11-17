package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import br.edu.ifpr.agenda.model.Pessoa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private Connection con;

    public PessoaDAO(Connection con) {
        this.con = con;
    }

    public int inserir(Pessoa p) throws SQLException {
        String sql = """
            INSERT INTO pessoa (nome, dt_nascimento, cpf, telefone, email)
            VALUES (?, ?, ?, ?, ?)
        """;

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, p.getNome());
        ps.setDate(2, Date.valueOf(p.getDtNascimento()));
        ps.setString(3, p.getCpf());
        ps.setString(4, p.getTelefone());
        ps.setString(5, p.getEmail());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);

        return -1;
    }

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
 

