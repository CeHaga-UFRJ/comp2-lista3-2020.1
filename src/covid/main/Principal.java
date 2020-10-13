package covid.main;

import covid.data.Caso;
import covid.out.Web;
import covid.util.Leitura;

import java.util.*;

/**
 * Classe principal do programa
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class Principal {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre com o nome do lugar que deseja ver estatísticas (Deixe em branco se quiser ver estatísticas gerais)");
        String lugar = sc.nextLine();
        HashMap<String, List<Caso>> mapa = null;
        mapa = Leitura.leDados(lugar);
        if(mapa.isEmpty()){
            System.out.print("Local não encontrado");
            System.exit(1);
        }
        boolean grafico = Web.grafico(mapa);
        if(!grafico) System.out.println("Ocorreu um problema na geração desse gráfico, ele não foi gerado. Mas você ainda pode ver as estatísticas gerais");
    }
}
