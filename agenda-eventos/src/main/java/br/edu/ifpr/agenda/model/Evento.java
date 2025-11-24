package br.edu.ifpr.agenda.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private int idEvento;
    private LocalDateTime data;
    private String local;
    private String nomeEvento;
    private int qtdMaxPessoas;
    private List<Pessoa> convidados = new ArrayList<>();
    private List<Funcionario> funcionarios = new ArrayList<>();
    
    public Evento() {
    }

    public void listarConvidados() {
        if (convidados.isEmpty()) {
            System.out.println("Nenhum convidado neste evento.");
        } else {
            System.out.println("Convidados do evento " + nomeEvento + ":");
            for (Pessoa p : convidados) {
                System.out.format("- %s | CPF: %s | Telefone: %s\n", p.getNome(), p.getCpf(), p.getTelefone());
            }
        }
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
   
    public void adicionarFuncionario(Funcionario f) {
        funcionarios.add(f);
    }
  
    public void adicionarConvidado(Pessoa p) {
        convidados.add(p);
    }

    public List<Pessoa> getConvidados() {
        return convidados;
    } 
    
    public int getQtdMaxPessoas() {
        return qtdMaxPessoas;
    }
   
    public void setQtdMaxPessoas(int qtdMaxPessoas) {
        this.qtdMaxPessoas = qtdMaxPessoas;
    }
   
    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
  
    public void setConvidados(List<Pessoa> convidados) {
        this.convidados = convidados;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
}
