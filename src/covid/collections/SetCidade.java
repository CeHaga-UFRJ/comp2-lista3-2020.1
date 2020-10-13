package covid.collections;

import covid.data.Caso;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Classe wrapper para uma PriorityQueue
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class SetCidade implements Collection<Caso> {
    private PriorityQueue<Caso> lista;

    public SetCidade(Comparator<Caso> comp){
        lista = new PriorityQueue<Caso>(comp);
    }

    @Override
    public int size() {
        return lista.size();
    }

    @Override
    public boolean isEmpty() {
        return lista.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return lista.contains(o);
    }

    @Override
    public Iterator<Caso> iterator() {
        return lista.iterator();
    }

    @Override
    public Object[] toArray() {
        return lista.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return lista.toArray(ts);
    }

    /**
     * Adiciona um novo Caso tirando repetições e limitando a 10 itens totais
     * @param caso Caso a ser adicionado
     * @return true se foi possível adicionar, false caso contrário
     */
    @Override
    public boolean add(Caso caso) {
        if(!lista.isEmpty()){
            for(Caso c : lista){
                if(c.isPlaceEquals(caso)){
                    int comp = lista.comparator().compare(c,caso);
                    if(comp < 0) lista.remove(c);
                    else return false;
                    break;
                }
            }
        }
        lista.add(caso);
        if(lista.size() <= 10){
            return true;
        }
        Caso prim = lista.poll();
        if(prim == caso) return false;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return lista.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return lista.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends Caso> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return lista.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return lista.retainAll(collection);
    }

    @Override
    public void clear() {
        lista.clear();
    }

    public Comparator<? super Caso> comparator() {
        return lista.comparator();
    }
}
