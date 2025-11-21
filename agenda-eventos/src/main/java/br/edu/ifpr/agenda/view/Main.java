package br.edu.ifpr.agenda.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.edu.ifpr.agenda.controller.EventoController;
import br.edu.ifpr.agenda.model.Evento;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static EventoController controller = new EventoController();

    public static void main(String[] args) {

        int opcao;

        do {
            mostrarMenu();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarEvento();
                case 5 -> listarEventos();
                case 8 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

            if (opcao != 8) {
                System.out.println("\nPressione ENTER para continuar");
                sc.nextLine();
            }

        } while (opcao != 8);
    }

    public static void mostrarMenu() {
        System.out.println("\n=== MENU AGENDA ===");
        System.out.println("1. Cadastrar evento");
        System.out.println("2. Adicionar convidados em evento");
        System.out.println("3. Buscar evento");
        System.out.println("4. Inscrever pessoa em evento");
        System.out.println("5. Listar eventos");
        System.out.println("6. Cancelar evento");
        System.out.println("7. Remover pessoa");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadastrarEvento() {

        System.out.print("Nome do evento: ");
        String nome = sc.nextLine();

        System.out.print("Local do evento: ");
        String local = sc.nextLine();

        System.out.print("Quantidade máxima de pessoas: ");
        int max = sc.nextInt();
        sc.nextLine();

        System.out.print("Data (DD-MM-AAAA): ");
        String data = sc.nextLine();

        System.out.print("Hora (HH:MM): ");
        String hora = sc.nextLine();

        LocalDateTime dt = LocalDateTime.parse(data + " " + hora,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

        int id = controller.criarEvento(nome, dt, local, max);

        System.out.println("Evento criado! ID = " + id);
    }

    public static void listarEventos() {
        List<Evento> eventos = controller.listarEventos();

        System.out.println("\n=== LISTA DE EVENTOS ===");

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado.");
            return;
        }

        for (Evento e : eventos) {
            System.out.printf("ID: %d | Nome: %s | Local: %s | Data: %s | Máx: %d\n",
                    e.getIdEvento(),
                    e.getNomeEvento(),
                    e.getLocal(),
                    e.getData(),
                    e.getQtdMaxPessoas());
        }
    }
}
