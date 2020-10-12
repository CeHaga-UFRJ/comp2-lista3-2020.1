package covid.out;

import covid.data.Caso;
import covid.util.Stats;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public abstract class Arquivo {
    public static void geraArquivo(Stats estatisticas){
        List<List<Caso>> listas = new ArrayList<List<Caso>>();
        listas.add(estatisticas.listaMaiorCaso());
        listas.add(estatisticas.listaMenorCaso());
        listas.add(estatisticas.listaMaiorMortos());
        listas.add(estatisticas.listaMenorMortos());
        listas.add(estatisticas.listaMaiorMortalidade());
        String[] nomes = new String[]{"maior_casos_100k", "menor_casos_100k", "maior_mortos_100k", "menor_mortos_100k", "maior_mortalidade"};
        for(int i = 0; i < nomes.length; i++) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("resources/arquivos/"+nomes[i]+".tsv"), "utf-8"))) {
                writer.write("cidade\tdata_ultima_medicao\tvalor\n");
                List<Caso> lista = listas.get(i);
                for(Caso c : lista){
                    double valor;
                    if(i == 0 || i == 1){
                        valor = c.getConfirmado100k();
                    }else if(i == 2 || i == 3){
                        valor = c.getMortos100k();
                    }else{
                        valor = c.getTaxaMorte();
                    }
                    writer.write(c.getCidade()+"\t"+c.getData()+"\t"+valor+"\n");
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo "+nomes[i]+".tsv");
                e.printStackTrace();
            }
        }
    }
}
