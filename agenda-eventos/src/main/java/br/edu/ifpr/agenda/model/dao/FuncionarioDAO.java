package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifpr.agenda.model.AgendaEventos;
import br.edu.ifpr.agenda.model.Funcionario;

public class FuncionarioDAO {

    public void salvarFuncionario(Funcionario funcionario){
        Connection con = ConnectionFactory.getConnection();
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

/*
 * 


public class FuncionarioDAO {

    public void salvarFuncionario(Funcionario funcionario) {
        Connection con = ConnectionFactory.getConnection();
        try {
            String sqlFuncionario = 
            "INSERT INTO funcionarios(nome,dtNascimento,cpf,telefone,email,evento, funcao,numBanco, salario) VALUES (?,?,?,?)";
            PreparedStatement psFuncionario = con.prepareStatement(sqlFuncionario);
            psFuncionario.setString(1, funcionario.getNome());
            psFuncionario.setDate(2, funcionario.getDtNascimento());
            psFuncionario.setString(1, funcionario.getCpf());
            psFuncionario.setString(1, funcionario.getTelefone());
            psFuncionario.setString(3, funcionario.getEmail());
            psFuncionario.setString(4, funcionario.getEvento);
            psFuncionario.executeUpdate();
            System.out.println("Funcionario inserido com sucesso!");

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
 
 */