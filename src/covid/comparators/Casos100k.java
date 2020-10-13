package covid.comparators;

import covid.data.Caso;

import java.util.Comparator;

/**
 * Comparador de casos por 100k habitantes
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class Casos100k implements Comparator<Caso> {
    @Override
    public int compare(Caso c1, Caso c2){
        if(c1.getConfirmado100k() < c2.getConfirmado100k()) return 1;
        if(c1.getConfirmado100k() == c2.getConfirmado100k()) {
            if(c1.getData().isBefore(c2.getData())) return 1;
            if(c1.getData().isAfter(c2.getData())) return -1;
            return 0;
        }
        return -1;
    }
}
