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
                //case 1 -> cadastrarEvento();
                //case 2 -> adicionarConvidado();
                //case 3 -> buscarEvento();
                //case 4 -> inscreverPessoa();
                //case 5 -> listarEventos();
                //case 6 -> cancelarEvento();
                //case 7 -> removerPessoa();
                //case 8 -> System.out.println("Saindo...");
                //default -> System.out.println("Opção inválida!");
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

    
}
