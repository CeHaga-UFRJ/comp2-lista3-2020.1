package covid.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para representar um ponto no gráfico
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class DataPoint {
    private LocalDate x;
    private int y;
    private String group;

    public DataPoint(LocalDate x, int y, String group) {
        this.x = x;
        this.y = y;
        this.group = group;
    }

    /**
     * Soma os valores se os pontos se referem ao mesmo dia e grupo
     * @param dp DataPoint a ser somado
     * @return true se foi possível somar, false caso contrário
     */
    public boolean add(DataPoint dp){
        if(!dp.x.isEqual(x) || !dp.group.equals(group)) return false;
        y += dp.y;
        return true;
    }

    @Override
    public String toString() {
        return "{" +
                "x:'" + x +
                "', y:" + y +
                ", group:\"" + group + '\"' +
                '}';
    }

    /**
     * Junta duas listas e transforma em uma String no formato do gráfico
     * @param l1 Lista a ser junta
     * @param l2 Outra lista a ser junta
     * @return Uma String no formato esperado pelo template gráfico
     */
    public static String toString(List<DataPoint> l1, List<DataPoint> l2){
        List<DataPoint> l = new ArrayList<>(l1);
        l.addAll(l2);
        boolean prim = true;
        StringBuilder saida = new StringBuilder();
        for(DataPoint dp : l){
            if(!prim){
                saida.append(',');
            }
            saida.append(dp.toString());
            prim = false;
        }
        return saida.toString();
    }
}
