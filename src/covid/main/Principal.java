package covid.main;

import covid.data.Caso;
import covid.util.Leitura;

import java.util.*;

public class Principal {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre com o nome do lugar que deseja ver estatísticas (Deixe em branco se quiser ver estatísticas gerais)");
        String lugar = sc.nextLine();
        HashMap<String, List<Caso>> mapa = null;
        long start = System.currentTimeMillis();
        mapa = Leitura.leDados(lugar);
        if(mapa.isEmpty()){
            System.out.print("Local não encontrado");
            System.exit(1);
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Tudo foi executado em " + (float)elapsed/1000.0 + "segundos");
    }
}
