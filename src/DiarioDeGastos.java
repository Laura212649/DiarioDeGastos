import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;

public class DiarioDeGastos {
    private List<Gasto> gastos = new ArrayList<>();

    public void adicionarGasto(Gasto gasto) {
        gastos.add(gasto);
    }

    public void editarGasto(int indice, Gasto novoGasto) {
        if (indice >= 0 && indice < gastos.size()) {
            gastos.set(indice, novoGasto);
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void removerGastos(int indice) {
        if (indice >= 0 && indice < gastos.size()) {
            gastos.remove(indice);
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public List<Gasto> consultarGastos() {
        return gastos;
    }

    public List<Gasto> consultarPorMes(int ano, int mes) {
        List<Gasto> resultado = new ArrayList<>();
        for (Gasto gasto : gastos) {
            if (gasto.getData().getYear() == ano && gasto.getData().getMonthValue() == mes) resultado.add(gasto);
        }
        return resultado;
    }

    public List<Gasto> consultarPorSemana(int ano, int semana) {
        List<Gasto> resultado = new ArrayList<>();
        for (Gasto gasto : gastos) {
            if (gasto.getData().getYear() == ano && gasto.getData().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) == semana)
                resultado.add(gasto);
        }
        return resultado;
    }

    public int quantidadeGastos() {
        return gastos.size();
    }

    public Gasto getGasto(int indice) {
        if (indice >= 0 && indice < gastos.size()) return gastos.get(indice);
        return null;
    }
}



