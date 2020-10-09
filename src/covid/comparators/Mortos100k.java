package covid.comparators;

import covid.data.Caso;

import java.util.Comparator;

public class Mortos100k implements Comparator<Caso> {
    @Override
    public int compare(Caso c1, Caso c2){
        double morteCaso1 = c1.getMortes() / (double)c1.getConfirmado();
        double morteCaso2 = c2.getMortes() / (double)c2.getConfirmado();
        if(morteCaso1 < morteCaso2) return 1;
        if(morteCaso1 == morteCaso2) return 0;
        return -1;
    }
}
