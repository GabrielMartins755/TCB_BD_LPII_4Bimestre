package br.edu.ifpr.agenda.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConvidadoDAO {

    private Connection con;

    public ConvidadoDAO(Connection con) {
        this.con = con;
    }

    public int inserir(int idPessoa) throws SQLException {

        String sql = "INSERT INTO convidado (id_pessoa) VALUES (?)";

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, idPessoa);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);

        return -1;
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
