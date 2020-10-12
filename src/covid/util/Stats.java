package covid.util;

import covid.collections.SetCidade;
import covid.comparators.*;
import covid.data.Caso;

import java.time.LocalDate;
import java.util.*;

public class Stats {
    private SetCidade maior_casos_100k;
    private SetCidade menor_casos_100k;
    private SetCidade maior_mortos_100k;
    private SetCidade menor_mortos_100k;
    private SetCidade maior_mortalidade;
    private LocalDate ultDia;
    private LocalDate primDia;

    public Stats(){
        ultDia = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(),1).minusDays(1);
        primDia = LocalDate.of(ultDia.getYear(),ultDia.getMonthValue(),1);
        maior_casos_100k = new SetCidade(new Casos100k().reversed());
        menor_casos_100k = new SetCidade(new Casos100k());
        maior_mortos_100k = new SetCidade(new Mortos100k().reversed());
        menor_mortos_100k = new SetCidade(new Mortos100k());
        maior_mortalidade = new SetCidade(new Mortalidade().reversed());
    }

    public void add(Caso c){
        if(c.getConfirmado100k() > 0) {
            maior_casos_100k.add(c);
            menor_casos_100k.add(c);
        }
        if(c.getMortos100k() > 0) {
            maior_mortos_100k.add(c);
            menor_mortos_100k.add(c);
        }
        if(c.getData().isAfter(primDia) && c.getData().isBefore(ultDia)){
            maior_mortalidade.add(c);
        }
    }

    public List<Caso> listaMaiorCaso(){
        List<Caso> lista = new ArrayList<Caso>(maior_casos_100k);
        lista.sort(maior_casos_100k.comparator().reversed());
        return lista;
    }

    public List<Caso> listaMenorCaso(){
        List<Caso> lista = new ArrayList<Caso>(menor_casos_100k);
        lista.sort(menor_casos_100k.comparator().reversed());
        return lista;
    }

    public List<Caso> listaMaiorMortos(){
        List<Caso> lista = new ArrayList<Caso>(maior_mortos_100k);
        lista.sort(maior_mortos_100k.comparator().reversed());
        return lista;
    }

    public List<Caso> listaMenorMortos(){
        List<Caso> lista = new ArrayList<Caso>(menor_mortos_100k);
        lista.sort(menor_mortos_100k.comparator().reversed());
        return lista;
    }

    public List<Caso> listaMaiorMortalidade(){
        List<Caso> lista = new ArrayList<Caso>(maior_mortalidade);
        lista.sort(maior_mortalidade.comparator().reversed());
        return lista;
    }
}
