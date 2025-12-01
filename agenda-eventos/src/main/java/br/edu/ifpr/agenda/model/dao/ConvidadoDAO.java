package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConvidadoDAO {
    private Connection con;

    public ConvidadoDAO(Connection con) {
        this.con = con;
    }

    public int inserirPessoa(String nomePessoa) throws SQLException {
        String sql = "INSERT INTO pessoa (nome) VALUES (?)";

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, nomePessoa);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);

        return -1;
    }

    private int inserirConvidado(int idPessoa) throws SQLException {
        String sql = "INSERT INTO convidado (id_pessoa) VALUES (?)";

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, idPessoa);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // id_convidado
        }

        return -1;
    }

    // 3. Método usado pelo controller → retorna id_convidado
    public int inserirID(String nomePessoa) throws SQLException {
        int idPessoa = inserirPessoa(nomePessoa);
        return inserirConvidado(idPessoa); // retorna id_convidado
    }


    public List<Integer> listarTodosIds() throws SQLException {
        List<Integer> lista = new ArrayList<>();

        String sql = "SELECT id_convidado FROM convidado";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(rs.getInt("id_convidado"));
        }

        return lista;
    }
}
