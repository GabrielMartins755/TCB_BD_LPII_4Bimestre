package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifpr.agenda.model.AgendaEventos;
import br.edu.ifpr.agenda.model.Evento;

public class EventoDAO {
    public void salvarEvento (Evento evento){
        Connection con = ConnectionFactory.getConnection();

    }
}
/*
 * public class EventoDAO {

    public void salvarEvento(Evento evento) {
        Connection con = ConnectionFactory.getConnection();
        try {
            String sqlEvento = 
            "INSERT INTO eventos(data,nomeEvento,local, qtdMaxPessoas,convidados,funcionarios) VALUES (?,?,?,?)";
            PreparedStatement psEvento = con.prepareStatement(sqlEvento);
            psEvento.setString(1, evento.getData());
            psEvento.setDate(2, evento.getDtNomeEvento());
            psEvento.setString(1, evento.getLocal());
            psEvento.setString(1, evento.getQtdMaxPessoas());
            psEvento.setString(3, evento.getConvidados());
            psEvento.setString(4, evento.getFuncionarios);
            psEvento.executeUpdate();
            System.out.println("Evento inserido com sucesso!");

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
 */