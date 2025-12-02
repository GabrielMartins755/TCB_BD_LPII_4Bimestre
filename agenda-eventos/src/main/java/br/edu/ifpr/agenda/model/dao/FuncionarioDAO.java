package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.agenda.model.Funcionario;

public class FuncionarioDAO {
    private Connection con;

    public FuncionarioDAO(Connection con) {
        this.con = con;
    }

    public int inserir(Funcionario f) throws SQLException {

        String sql = """
                    INSERT INTO funcionario (id_pessoa, funcao, num_banco, salario)
                    VALUES (?, ?, ?, ?)
                """;

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, f.getIdPessoa());
        ps.setString(2, f.getFuncao());
        ps.setString(3, f.getNumBanco());
        ps.setInt(4, f.getSalario());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next())
            return rs.getInt(1);

        return -1;
    }

    public Funcionario buscarPorId(int idFuncionario) throws SQLException {
        String sql = """
                    SELECT f.*, p.nome, p.cpf, p.telefone, p.email
                    FROM funcionario f
                    JOIN pessoa p ON p.id_pessoa = f.id_pessoa
                    WHERE id_funcionario=?
                """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idFuncionario);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Funcionario f = new Funcionario();
            f.setIdFuncionario(rs.getInt("id_funcionario"));
            f.setIdPessoa(rs.getInt("id_pessoa"));
            f.setFuncao(rs.getString("funcao"));
            f.setNumBanco(rs.getString("num_banco"));
            f.setSalario(rs.getInt("salario"));
            f.setNome(rs.getString("nome"));
            f.setCpf(rs.getString("cpf"));
            f.setTelefone(rs.getString("telefone"));
            f.setEmail(rs.getString("email"));

            return f;
        }
        return null;
    }

    public List<Funcionario> listarTodos() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();

        String sql = """
                    SELECT f.*, p.nome, p.cpf, p.telefone
                    FROM funcionario f
                    JOIN pessoa p ON p.id_pessoa = f.id_pessoa
                """;

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Funcionario f = new Funcionario();
            f.setIdFuncionario(rs.getInt("id_funcionario"));
            f.setIdPessoa(rs.getInt("id_pessoa"));
            f.setFuncao(rs.getString("funcao"));
            f.setNumBanco(rs.getString("num_banco"));
            f.setSalario(rs.getInt("salario"));
            f.setNome(rs.getString("nome"));
            f.setCpf(rs.getString("cpf"));
            f.setTelefone(rs.getString("telefone"));
            lista.add(f);
        }

        return lista;
    }
}
