package covid.comparators;

import covid.data.Caso;

import java.util.Comparator;

public class Mortalidade implements Comparator<Caso> {
    @Override
    public int compare(Caso c1, Caso c2){
        if(c1.getTaxaMorte() < c2.getTaxaMorte()) return 1;
        if(c1.getTaxaMorte() == c2.getTaxaMorte()) return 0;
        return -1;
    }
}
