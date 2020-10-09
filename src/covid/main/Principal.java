package covid.main;

import covid.data.Caso;
import covid.util.Leitura;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre com o nome do lugar que deseja ver estatísticas (Deixe em branco se quiser ver estatísticas gerais)");
        String lugar = sc.nextLine();
        if(!lugar.isEmpty()){
            HashMap<String, List<Caso>> mapa = Leitura.leDados();

        }
    }
}
