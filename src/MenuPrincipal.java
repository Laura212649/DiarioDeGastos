import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

public class MenuPrincipal {
        private DiarioDeGastos diario;
        private Scanner scanner;

        public MenuPrincipal(DiarioDeGastos diario) {
            this.diario = diario;
            this.scanner = new Scanner(System.in);
        }

        public void iniciar() {
            int opcao;
            do {
                System.out.println("\n==== DIÁRIO DE GASTOS ====");
                System.out.println("1. Adicionar gasto");
                System.out.println("2. Editar gasto");
                System.out.println("3. Remover gasto");
                System.out.println("4. Consultar gastos");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        adicionar();
                        break;
                    case 2:
                        editar();
                        break;
                    case 3:
                        remover();
                        break;
                    case 4:
                        consultar();
                        break;
                    case 5:
                        System.out.println("Saindo");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } while (opcao != 5);
        }

        private void adicionar() {
            System.out.print("Descrição: ");
            String desc = scanner.nextLine();
            System.out.print("Categoria: ");
            String cat = scanner.nextLine();
            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Data (AAAA-MM-DD): ");
            LocalDate data = LocalDate.parse(scanner.nextLine());

            diario.adicionarGasto(new Gasto(desc, cat, valor, data));
            System.out.println("Gasto adicionado!");
        }

        private void editar() {
            listarGastos();
            System.out.print("Informe o número do gasto a editar: ");
            int indice = scanner.nextInt();
            scanner.nextLine();

            Gasto antigo = diario.getGasto(indice);
            if (antigo == null) {
                System.out.println("Índice inválido!");
                return;
            }

            System.out.print("Nova descrição [" + antigo.getDescricao() + "]: ");
            String desc = scanner.nextLine();
            if (desc.isEmpty()) desc = antigo.getDescricao();

            System.out.print("Nova categoria [" + antigo.getCategoria() + "]: ");
            String cat = scanner.nextLine();
            if (cat.isEmpty()) cat = antigo.getCategoria();

            System.out.print("Novo valor [" + antigo.getValor() + "]: ");
            String valorStr = scanner.nextLine();
            double valor = valorStr.isEmpty() ? antigo.getValor() : Double.parseDouble(valorStr);

            System.out.print("Nova data [" + antigo.getData() + "]: ");
            String dataStr = scanner.nextLine();
            LocalDate data = dataStr.isEmpty() ? antigo.getData() : LocalDate.parse(dataStr);

            diario.editarGasto(indice, new Gasto(desc, cat, valor, data));
            System.out.println("Gasto editado!");
        }

        private void remover() {
            listarGastos();
            System.out.print("Informe o número do gasto a remover: ");
            int indice = scanner.nextInt();
            scanner.nextLine();

            diario.removerGastos(indice);
            System.out.println("Gasto removido!");
        }

        private void consultar() {
            System.out.println("1. Todos os gastos");
            System.out.println("2. Por mês");
            System.out.println("3. Por semana");
            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    listarGastos();
                    break;
                case 2:
                    System.out.print("Ano: ");
                    int anoM = scanner.nextInt();
                    System.out.print("Mês (1-12): ");
                    int mes = scanner.nextInt();
                    scanner.nextLine();
                    List<Gasto> porMes = diario.consultarPorMes(anoM, mes);
                    if (porMes.isEmpty())
                        System.out.println("Nenhum gasto encontrado.");
                    else
                        porMes.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Ano: ");
                    int anoS = scanner.nextInt();
                    System.out.print("Semana (1-53): ");
                    int semana = scanner.nextInt();
                    scanner.nextLine();
                    List<Gasto> porSemana = diario.consultarPorSemana(anoS, semana);
                    if (porSemana.isEmpty())
                        System.out.println("Nenhum gasto encontrado.");
                    else
                        porSemana.forEach(System.out::println);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        private void listarGastos() {
            List<Gasto> lista = diario.consultarGastos();
            if (lista.isEmpty()) {
                System.out.println("Nenhum gasto registrado.");
                return;
            }
            for (int i = 0; i < lista.size(); i++) {
                System.out.printf("[%d] %s\n", i, lista.get(i));
            }
        }
    }

