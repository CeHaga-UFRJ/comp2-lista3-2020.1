package covid.util;

import covid.comparators.Casos100k;
import covid.comparators.Mortalidade;
import covid.comparators.Mortos100k;
import covid.data.Caso;

import java.util.PriorityQueue;

public class Stats {
    private PriorityQueue<Caso> maior_casos_100k;
    private int limite_maior_casos_100k;
    private PriorityQueue<Caso> menor_casos_100k;
    private int limite_menor_casos_100k;
    private PriorityQueue<Caso> maior_mortos_100k;
    private int limite_maior_mortos_100k;
    private PriorityQueue<Caso> menor_mortos_100k;
    private int limite_menor_mortos_100k;
    private PriorityQueue<Caso> maior_mortalidade;
    private int limite_maior_mortalidade;
    private PriorityQueue<Caso> menor_mortalidade;
    private int limite_menor_mortalidade;

    public Stats(){
        maior_casos_100k = new PriorityQueue<>(new Casos100k());
        menor_casos_100k = new PriorityQueue<>(new Casos100k().reversed());
        maior_mortos_100k = new PriorityQueue<>(new Mortos100k());
        menor_mortos_100k = new PriorityQueue<>(new Mortos100k().reversed());
        maior_mortalidade = new PriorityQueue<>(new Mortalidade());
        menor_mortalidade = new PriorityQueue<>(new Mortalidade().reversed());
    }
}
