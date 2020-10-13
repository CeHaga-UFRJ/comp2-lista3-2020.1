package covid.out;

import covid.data.Caso;
import covid.data.DataPoint;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.GZIPInputStream;

public abstract class Web {
    public static boolean grafico(HashMap<String, List<Caso>> mapa){
        if(mapa.isEmpty()) return false;
        LocalDate dataFim = null;
        LocalDate dataInicio = null;
        HashMap<LocalDate, DataPoint> itensCasos = new HashMap<>();
        HashMap<LocalDate, DataPoint> itensMortes = new HashMap<>();
        for(List<Caso> lista : mapa.values()){
            for(Caso c : lista){
                if(dataFim == null) dataFim = c.getData();
                dataInicio = c.getData();
                DataPoint dpCaso = new DataPoint(c.getData(), c.getConfirmado(), "Casos");
                DataPoint dpMorte = new DataPoint(c.getData(), c.getMortes(), "Mortes");
                if(itensCasos.containsKey(c.getData())){
                    itensCasos.get(c.getData()).add(dpCaso);
                }else{
                    itensCasos.put(c.getData(),dpCaso);
                }
                if(itensMortes.containsKey(c.getData())){
                    itensMortes.get(c.getData()).add(dpMorte);
                }else{
                    itensMortes.put(c.getData(),dpMorte);
                }
            }
        }
        List<DataPoint> l1 = new ArrayList<>(itensCasos.values());
        List<DataPoint> l2 = new ArrayList<>(itensMortes.values());
        String itens = DataPoint.toString(l1, l2);
        StringBuilder saida = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/template/paginaTemplate.html")))){
            String linha;
            while((linha = br.readLine()) != null){
                saida.append(linha
                        .replace(":data_inicio:","'"+dataInicio.toString()+"'")
                        .replace(":data_fim:","'"+dataFim.toString()+"'")
                        .replace(":items:",itens) + "\n"
                );
            }
        }catch (IOException e){
            System.err.println("Erro ao abrir template");
            e.printStackTrace();
            return false;
        }
        try (PrintWriter out = new PrintWriter("resources/arquivos/grafico.html")) {
            out.println(saida);
        }catch (IOException e){
            System.err.println("Erro ao criar gráfico");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
