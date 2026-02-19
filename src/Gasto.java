import java.time.LocalDate;

public class Gasto {
    private String descricao;
    private String categoria;
    private double valor;
    private LocalDate data;

    public Gasto (String descricao, String categoria, double valor, LocalDate data) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
    }

    public String getDescricao() {return descricao;}
    public String getCategoria() {return categoria;}
    public double getValor() {return valor;}
    public LocalDate getData() {return data;}

    @Override
    public String toString() {
        return String.format("Descrição: %s | Categoria: %s | Valor: %.2f | Data: %s",
                descricao, categoria, valor, data.toString());
    }

}
