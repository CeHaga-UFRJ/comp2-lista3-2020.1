package covid.util;

import covid.collections.SetCidade;
import covid.comparators.*;
import covid.data.Caso;

import java.time.LocalDate;
import java.util.*;

public class Stats {
    private SetCidade maior_casos_100k;
    private SetCidade menor_casos_100k;
    private SetCidade maior_mortalidade;
    private SetCidade menor_mortalidade;
    private SetCidade maior_taxa_crescimento;
    private LocalDate ultDia;
    private LocalDate primDia;
    private Caso ultCaso;
    private Caso primCaso;

    public Stats(){
        ultDia = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(),1).minusDays(1);
        primDia = LocalDate.of(ultDia.getYear(),ultDia.getMonthValue(),1);
        ultDia = ultDia.plusDays(1);
        maior_casos_100k = new SetCidade(new Casos100k().reversed());
        menor_casos_100k = new SetCidade(new Casos100k());
        maior_mortalidade = new SetCidade(new Mortalidade().reversed());
        menor_mortalidade = new SetCidade(new Mortalidade());
        maior_taxa_crescimento = new SetCidade(new Crescimento().reversed());
    }

    public void add(Caso c){
        if(c.getData().isBefore(primDia)) {
            primCaso = null;
            ultCaso = null;
            return;
        }
        if(c.getData().isAfter(ultDia)) {
            primCaso = null;
            ultCaso = null;
        }
        if(c.getData().isEqual(ultDia)) ultCaso = c;
        if(c.getData().isEqual(primDia)) primCaso = c;
        if(c.getConfirmado100k() > 0 && c.isUltimo()) {
            maior_casos_100k.add(c);
            menor_casos_100k.add(c);
        }
        if(c.getTaxaMorte() > 0 && c.isUltimo()) {
            maior_mortalidade.add(c);
            menor_mortalidade.add(c);
        }
        if(primCaso != null && ultCaso != null){
            if(primCaso.getConfirmado() == 0) return;
            double taxa = ultCaso.getConfirmado() / (double)primCaso.getConfirmado() - 1;
            ultCaso.setTaxaCrescimento(taxa);
            maior_taxa_crescimento.add(ultCaso);
            primCaso = null;
            ultCaso = null;
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

    public List<Caso> listaMaiorMortalidade(){
        List<Caso> lista = new ArrayList<Caso>(maior_mortalidade);
        lista.sort(maior_mortalidade.comparator().reversed());
        return lista;
    }

    public List<Caso> listaMenorMortalidade(){
        List<Caso> lista = new ArrayList<Caso>(menor_mortalidade);
        lista.sort(menor_mortalidade.comparator().reversed());
        return lista;
    }

    public List<Caso> listaMaiorTaxaCrescimento(){
        List<Caso> lista = new ArrayList<Caso>(maior_taxa_crescimento);
        lista.sort(maior_taxa_crescimento.comparator().reversed());
        return lista;
    }
}
