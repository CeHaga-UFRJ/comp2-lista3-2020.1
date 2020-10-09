package covid.comparators;

import covid.data.Caso;

import java.util.Comparator;

public class Casos100k implements Comparator<Caso> {
    @Override
    public int compare(Caso c1, Caso c2){
        if(c1.getConfirmadoPorMil() < c2.getConfirmadoPorMil()) return 1;
        if(c1.getConfirmadoPorMil() == c2.getConfirmadoPorMil()) return 0;
        return -1;
    }
}
