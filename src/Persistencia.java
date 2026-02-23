import java.io.*;
import java.time.LocalDate;
public class Persistencia {
        public static void salvar(DiarioDeGastos diario, String nomeArquivo) throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));
            for(Gasto g : diario.consultarGastos()) {
                writer.write(String.format("%s;%s;%.2f;%s\n",
                        g.getDescricao(),
                        g.getCategoria(),
                        g.getValor(),
                        g.getData().toString()));
            }
            writer.close();
        }

        public static DiarioDeGastos carregar(String nomeArquivo) throws IOException {
            DiarioDeGastos diario = new DiarioDeGastos();
            File file = new File(nomeArquivo);

            if(!file.exists())
                return diario;

            BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if(partes.length == 4) {
                    String desc = partes[0];
                    String cat = partes[1];
                    double val = Double.parseDouble(partes[2]);
                    LocalDate data = LocalDate.parse(partes[3]);
                    diario.adicionarGasto(new Gasto(desc, cat, val, data));
                }
            }
            reader.close();
            return diario;
        }
    }
