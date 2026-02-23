import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
                DiarioDeGastos diario;
                try {
                    diario = Persistencia.carregar("gastos.txt");
                    System.out.println("(Dados carregados!)");
                } catch(Exception e) {
                    diario = new DiarioDeGastos();
                    System.out.println("(Iniciando sem dados anteriores)");
                }

                MenuPrincipal menu = new MenuPrincipal(diario);
                menu.iniciar();

                try {
                    Persistencia.salvar(diario, "gastos.txt");
                    System.out.println("(Dados salvos!)");
                } catch(Exception e) {
                    System.out.println("(Falha ao salvar dados!)");
                }
            }
        }
