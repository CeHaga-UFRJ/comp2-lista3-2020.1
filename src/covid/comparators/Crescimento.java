package covid.comparators;

import covid.data.Caso;

import java.util.Comparator;

/**
 * Comparador de taxa de crescimento
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class Crescimento implements Comparator<Caso> {
    @Override
    public int compare(Caso c1, Caso c2) {
        if(c1.getTaxaCrescimento() < c2.getTaxaCrescimento()) return 1;
        if(c1.getTaxaCrescimento() == c2.getTaxaCrescimento()) {
            if(c1.getData().isBefore(c2.getData())) return 1;
            if(c1.getData().isAfter(c2.getData())) return -1;
            return 0;
        }
        return -1;
    }
}
