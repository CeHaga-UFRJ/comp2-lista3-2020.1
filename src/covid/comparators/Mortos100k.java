package covid.comparators;

import covid.data.Caso;

import java.util.Comparator;

public class Mortos100k implements Comparator<Caso> {
    @Override
    public int compare(Caso c1, Caso c2){
        double morteCaso1 = c1.getMortos100k();
        double morteCaso2 = c2.getMortos100k();
        if(morteCaso1 < morteCaso2) return 1;
        if(morteCaso1 == morteCaso2) {
            if(c1.getData().isBefore(c2.getData())) return 1;
            if(c1.getData().isAfter(c2.getData())) return -1;
            return 0;
        }
        return -1;
    }
}
