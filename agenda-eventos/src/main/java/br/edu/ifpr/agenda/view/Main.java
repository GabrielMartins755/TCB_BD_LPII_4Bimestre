package br.edu.ifpr.agenda.view;

import br.edu.ifpr.agenda.controller.PessoaController;
import br.edu.ifpr.agenda.model.AgendaEventos;
import br.edu.ifpr.agenda.model.Evento;
import br.edu.ifpr.agenda.model.Funcionario;
import br.edu.ifpr.agenda.model.Pessoa;
import br.edu.ifpr.agenda.model.dao.EventoDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static Evento agenda = new Evento();

    public static void main(String[] args) {
        int opcao;
        do {    
            //limparTela();
            mostrarMenu();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarEvento();
                    break;
                case 2:
                    break;
                case 3:
                   // buscarEvento();
                    break;
                case 4:
                    //inscreverPessoa();
                    break;
                case 5:
                    
                    break;
                case 6:
                   
                    break;
                case 7:
                    
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
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
        System.out.println("5. listar eventos");
        System.out.println("6. Cancelar evento");
        System.out.println("7. Remover pessoa");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadastrarEvento() {
        Evento eventoDAO = new Evento();
        System.out.print("Nome do evento: ");
        eventoDAO.setNomeEvento(sc.nextLine());

        System.out.print("Local do evento: ");
        eventoDAO.setLocal(sc.nextLine());

        System.out.print("Quantidade máxima de pessoas: ");
        eventoDAO.setQtdMaxPessoas(sc.nextInt());
        sc.nextLine();

        System.out.print("Data (DD-MM-AAAA): ");
        String dataStr = sc.nextLine();

        System.out.print("Hora (HH:MM): ");
        String horaStr = sc.nextLine();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(dataStr + " " + horaStr, formato);
        eventoDAO.setData(dataHora);

        /*System.out.print("Deseja adicionar funcionários ao evento? (sim/nao): ");
        String resposta = sc.nextLine();

        if (resposta.equalsIgnoreCase("sim")) {
            System.out.print("Quantas funções diferentes você quer cadastrar? ");
            int qtdFuncoes = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < qtdFuncoes; i++) {
                System.out.print("\nFunção: ");
                String funcao = sc.nextLine();

                System.out.format("Salário para a função %s: ", funcao);
                String salario = sc.nextLine();

                System.out.print("Quantidade de funcionários nessa função: ");
                int qtd = sc.nextInt();
                sc.nextLine();

                for (int j = 0; j < qtd; j++) {
                    Funcionario f = new Funcionario();

                    System.out.format("\n--- Funcionário %d da função %s ---\n", j + 1, funcao);
                    System.out.print("Nome: ");
                    f.setNome(sc.nextLine());

                    System.out.print("Número do banco: ");
                    f.setNumBanco(sc.nextLine());

                    f.setFuncao(funcao);
                    f.setSalario(salario);
                    evento.adicionarFuncionario(f);
                }
            }

            System.out.println("\nTotal de funcionários cadastrados: " + evento.getFuncionarios().size());
        }*/
        eventoDAO.inserir(eventoDAO);
    }

    

   /* public static void buscarEvento() {
        System.out.print("Digite o nome do evento: ");
        String nomeBusca = sc.nextLine();
        Evento buscado = agenda.buscarEvento(nomeBusca);

        if (buscado != null) {
            System.out.println("\n=== EVENTO ENCONTRADO ===");
            System.out.println("Nome: " + buscado.getNomeEvento());
            System.out.println("Local: " + buscado.getLocal());
            System.out.println("Data: " + buscado.getData());
            System.out.println("Capacidade: " + buscado.getQtdMaxPessoas());
            System.out.println("Total de convidados: " + buscado.getConvidados().size());
            System.out.println("Total de funcionários: " + buscado.getFuncionarios().size());

            int opcao;
            do {
                System.out.println("\n--- O que deseja visualizar? ---");
                System.out.println("1. Listar funcionários");
                System.out.println("2. Listar convidados");
                System.out.println("3. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        if (buscado.getFuncionarios().isEmpty()) {
                            System.out.println("Nenhum funcionário cadastrado neste evento.");
                        } else {
                            System.out.println("\n=== FUNCIONÁRIOS DO EVENTO ===");
                            for (Funcionario f : buscado.getFuncionarios()) {
                                System.out.format("Nome: %s | Função: %s | Salário: %s | Banco: %s\n", f.getNome(), f.getFuncao(), f.getSalario(), f.getNumBanco());
                            }
                        }
                        break;

                    case 2:
                        if (buscado.getConvidados().isEmpty()) {
                            System.out.println("Nenhum convidado cadastrado neste evento.");
                        } else {
                            System.out.println("\n=== CONVIDADOS DO EVENTO ===");
                            for (Pessoa p : buscado.getConvidados()) {
                                System.out.format("Nome: %s | CPF: %s | Email: %s | Telefone: %s\n", p.getNome(), p.getCpf(), p.getEmail(), p.getTelefone());
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Voltando ao inicio");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } while (opcao != 3);

        } else {
            System.out.println("Evento não encontrado.");
        }
    }

    public static void inscreverPessoa() {
        System.out.print("Evento para inscrição: ");
        String nomeEvento = sc.nextLine();
        Evento eventoEscolhido = agenda.buscarEvento(nomeEvento);

        if (eventoEscolhido == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        if (eventoEscolhido.getConvidados().size() >= eventoEscolhido.getQtdMaxPessoas()) {
            System.out.println("O evento já atingiu a capacidade máxima de convidados!");
            return;
        }

        Pessoa pessoa = new Pessoa();

        System.out.print("Nome da pessoa: ");
        pessoa.setNome(sc.nextLine());

        System.out.print("CPF: ");
        pessoa.setCpf(sc.nextLine());

        System.out.print("Data de nascimento (DD-MM-AAAA): ");
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        pessoa.setDtNascimento(LocalDate.parse(sc.nextLine(), formatoData));

        System.out.print("Telefone: ");
        pessoa.setTelefone(sc.nextLine());

        System.out.print("Email: ");
        pessoa.setEmail(sc.nextLine());

        eventoEscolhido.adicionarConvidado(pessoa);
        pessoa.setEvento(eventoEscolhido);

        System.out.format("Pessoa %s inscrita no evento %s", pessoa.getNome(), eventoEscolhido.getNomeEvento());
        System.out.format("Total de convidados agora: %d / %d\n", eventoEscolhido.getConvidados().size(), eventoEscolhido.getQtdMaxPessoas());
    }



    public static void limparTela() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
        System.out.print("\033\143");
    }*/
}
