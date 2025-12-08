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
                case 2 -> listarEventos();
                case 3 -> buscarEvento();
                case 4 -> adicionarConvidado();
                case 5 -> inscreverFuncionario();
                case 6 -> removerPessoa();
                case 7 -> cancelarEvento();
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
        System.out.println("2. Listar eventos");
        System.out.println("3. Buscar evento");
        System.out.println("4. Adicionar convidados em evento");
        System.out.println("5. Inscrever funcionarios em evento");
        System.out.println("6. Remover pessoa");
        System.out.println("7. Cancelar evento");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadastrarEvento() {
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
    
    public static void listarEventos() {
        controller.listarEventos();
    }
    
    public static void buscarEvento() {
        System.out.print("ID do evento: ");
        int id = sc.nextInt();
        sc.nextLine();  

        controller.buscarEvento(id);
    }

    public static void adicionarConvidado() {
        System.out.print("ID do evento: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome do convidado: ");
        String nome = sc.nextLine();

        System.out.print("Data de nascimento: ");
        String dtNascimento = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        controller.adicionarConvidado(id, nome, dtNascimento, cpf, telefone, email);
    }


    public static void inscreverFuncionario() {
        System.out.print("ID do evento: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome do funcionário: ");
        String nomeFunc = sc.nextLine();

        System.out.print("Data de nascimento: ");
        String dtNascimento = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Função do funcionário: ");
        String funcao = sc.nextLine();

        System.out.print("Salário do funcionário: ");
        int salario = sc.nextInt();   

        System.out.print("Número da conta do Banco: ");
        String numBanco = sc.nextLine();    
        sc.nextLine();

        controller.adicionarFuncionario(id, nomeFunc, dtNascimento, cpf, telefone, email, funcao, salario, numBanco);
    }
    
    public static void removerPessoa() {
        System.out.print("ID do evento: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("ID da pessoa a remover: ");
        int idNome = sc.nextInt();
        
        controller.removerPessoa(id, idNome);
    }

    public static void cancelarEvento() {
        System.out.print("ID do evento a cancelar: ");
        int id = sc.nextInt();
        sc.nextLine();
    
        controller.removerEvento(id);
    }

    public static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
