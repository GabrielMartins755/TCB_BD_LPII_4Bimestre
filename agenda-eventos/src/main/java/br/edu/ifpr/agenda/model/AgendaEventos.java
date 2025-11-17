package br.edu.ifpr.agenda.model;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class AgendaEventos {
    private List<Evento> eventos = new ArrayList<>();

    public AgendaEventos() {
    }

    public void cadastrarEvento(Evento evento) {
        eventos.add(evento);
        System.out.println("Evento cadastrado: " + evento.getNomeEvento());
    }

    public Evento buscarEvento(String nomeEvento) {
        for (Evento evento : eventos) {
            if (evento.getNomeEvento().equalsIgnoreCase(nomeEvento)) return evento;
        }
    
        System.out.println("Evento não encontrado: " + nomeEvento);
        return null;
    }

    public void listarEventos() {
        System.out.println("Eventos na agenda:");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy 'às' HH:mm");
        for (Evento evento : eventos) {
        
            String dataFormatada = evento.getData().format(formato);
            System.out.format("Evento:%s\n- Data:%s\n- Local:%s\n", evento.getNomeEvento(), dataFormatada, evento.getLocal());
        }
    }

    public void cancelarEvento(Evento evento) {
        eventos.remove(evento);
    }
    
    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
/*
 */