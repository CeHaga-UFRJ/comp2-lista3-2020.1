package covid.util;

import java.util.*;
import java.util.zip.GZIPInputStream;
import java.io.*;
import covid.data.Caso;
import covid.out.Arquivo;

/**
 * Classe abstrata para realizar a leitura do arquivo
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public abstract class Leitura {
    /**
     * Gera um mapa dos locais com os casos de uma região escolhida
     * <p>O mapa gerado tem como chave uma String com uma localização e como valores respectivos uma lista dos casos dessa região</p>
     * @param lugar A região da qual se deseja obter os dados
     * @return O mapa dos casos por região
     */
    public static HashMap<String, List<Caso>> leDados(String lugar){
        Stats estatisticas = new Stats();

        HashMap<String, List<Caso>> mapa = new HashMap<String, List<Caso>>();
        String estado, cidade;
        if(lugar.isEmpty()){
            estado = null;
            cidade = null;
        }else if(lugar.contains("//")){
            String[] lugarSplit = lugar.split("//");
            cidade = lugarSplit[0];
            estado = lugarSplit[1];
        }else{
            estado = lugar;
            cidade = null;
        }

        //Coloca num buffer e cria o HashMap de casos
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("resources/casos/caso.csv.gz"))))){
            String linha;
            br.readLine(); //Cabecalho
            while((linha = br.readLine()) != null){
                Caso caso = new Caso(linha);
                String key;
                if(caso.isEstado()){
                    if(cidade != null) continue;
                    key = caso.getEstado();
                    if(estado != null && !key.equals(estado)) continue;
                }else{
                    estatisticas.add(caso);
                    if(cidade == null) continue;
                    key = caso.getCidade();
                    if(!key.equals(cidade)) continue;
                }
                if(mapa.containsKey(key)){
                    mapa.get(key).add(caso);
                }else{
                    List<Caso> lista = new ArrayList<Caso>();
                    lista.add(caso);
                    mapa.put(key,lista);
                }
            }
        }catch(FileNotFoundException e){
            System.err.println("Arquivo não encontrado");
            return null;
        }catch(IOException e){
            System.out.println("Erro ao ler arquivo");
            e.printStackTrace();
            return null;
        }
        Arquivo.geraArquivo(estatisticas);
        return mapa;
    }
}
