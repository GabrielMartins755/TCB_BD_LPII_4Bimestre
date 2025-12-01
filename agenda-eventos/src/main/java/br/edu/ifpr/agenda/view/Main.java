package br.edu.ifpr.agenda.view;

import java.util.Scanner;

import br.edu.ifpr.agenda.controller.EventoController;

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
                case 2 -> adicionarConvidado();
                case 3 -> buscarEvento();
                case 4 -> inscreverPessoa();
                case 5 -> listarEventos();
                case 6 -> cancelarEvento();
                case 7 -> removerPessoa();
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

    public static void cadastrarEvento(){
        System.out.print("Nome do evento: ");
        String nome = sc.nextLine();

        System.out.print("Data (DD-MM-AAAA): ");
        String data = sc.nextLine();

        System.out.print("Hora do evento: ");
        String hora = sc.nextLine();

        System.out.print("Local: ");
        String local = sc.nextLine();

        System.out.print("Quantidade máxima de pessoas: ");
        int qtdPessoas = sc.nextInt();

        controller.cadastrarEvento(nome, data, hora, local, qtdPessoas);
    }

     public static void adicionarConvidado() {
        System.out.print("ID do evento: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome do convidado que deseja adicionar: ");
        String nomeP = sc.nextLine();

        controller.adicionarConvidado(id, nomeP);
    }

    public static void buscarEvento() {
        System.out.print("ID do evento: ");
        int id = sc.nextInt();
        sc.nextLine();

        controller.buscarEvento(id);
    }
    
    public static void listarEventos() {
        controller.listarEventos();
    }

    public static void inscreverPessoa() {
        System.out.print("ID do evento: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome da pessoa: ");
        String nomePessoa = sc.nextLine();

        controller.adicionarConvidado(id, nomePessoa);
    }


    public static void cancelarEvento() {
        System.out.print("ID do evento a cancelar: ");
        int id = sc.nextInt();
        sc.nextLine();

        controller.removerEvento(id);
    }

    public static void removerPessoa() {
        System.out.print("ID do evento: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("ID da pessoa a remover: ");
        int idNome = sc.nextInt();

        controller.removerPessoa(id, idNome);
    }
}
