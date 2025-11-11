package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifpr.agenda.model.AgendaEventos;
import br.edu.ifpr.agenda.model.Pessoa;


public class PessoaDAO {

    public void salvarPessoa(Pessoa pessoa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvarPessoa'");
    }

   /*  public void salvarPessoa(Pessoa pessoa) {
        Connection con = ConnectionFactory.getConnection();
        try {
            String sqlPessoa = 
            "INSERT INTO pessoas(nome,dtNascimento,cpf,telefone,email,evento) VALUES (?,?,?,?)";
            PreparedStatement psPessoa = con.prepareStatement(sqlPessoa);
            psPessoa.setString(1, pessoa.getNome());
            psPessoa.setDate(2, pessoa.getDtNascimento());
            psPessoa.setString(1, pessoa.getCpf());
            psPessoa.setString(1, pessoa.getTelefone());
            psPessoa.setString(3, pessoa.getEmail());
            psPessoa.setString(4, pessoa.getEvento);
            psPessoa.executeUpdate();
            System.out.println("Convidado inserido com sucesso!");

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }*/

}
 

