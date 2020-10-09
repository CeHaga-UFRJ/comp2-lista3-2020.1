package covid.util;

import java.util.*;
import java.util.zip.GZIPInputStream;
import java.io.*;
import covid.data.Caso;

public abstract class Leitura {
    public static HashMap<String, List<Caso>> leDados(){
        HashMap<String, List<Caso>> mapa = new HashMap<String, List<Caso>>();

        //Le arquivo
        FileInputStream file;
        try{
            file = new FileInputStream("resources/casos/caso.csv.gz");
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não existe");
            return null;
        }

        //Abre como gzip
        GZIPInputStream gzip;
        try {
            gzip = new GZIPInputStream(file);
        }catch(IOException e){
            System.out.println("Erro ao ler arquivo");
            return null;
        }

        //Coloca num buffer e cria o HashMap de casos
        BufferedReader br = new BufferedReader(new InputStreamReader(gzip));
        try{
            String linha;
            br.readLine(); //Cabecalho
            while((linha = br.readLine()) != null){
                Caso caso = new Caso(linha);
                String key;
                if(caso.isEstado()){
                    key = caso.getEstado();
                }else{
                    key = caso.getCidade();
                }
                if(mapa.containsKey(key)){
                    mapa.get(key).add(caso);
                }else{
                    List<Caso> lista = new ArrayList<Caso>();
                    lista.add(caso);
                    mapa.put(key,lista);
                }
            }
        }catch(IOException e){
            System.out.println("Erro ao ler arquivo");
            return null;
        }

        return mapa;
    }
}
