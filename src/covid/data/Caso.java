package covid.data;

import java.util.Date;

public class Caso {
    private Date data;
    private String estado;
    private String cidade;
    private boolean isEstado;
    private int confirmado;
    private int mortes;
    private int posicao;
    private boolean ultimo;
    private int populacao2019;
    private int populacao2020;
    private double confirmadoPorMil;
    private double taxaMorte;

    public Caso(Date data, String estado, String cidade, boolean isEstado, int confirmado, int mortes, int posicao, boolean ultimo, int populacao2019, int populacao2020, double confirmadoPorMil, double taxaMorte) {
        this.data = data;
        this.estado = estado;
        this.cidade = cidade;
        this.isEstado = isEstado;
        this.confirmado = confirmado;
        this.mortes = mortes;
        this.posicao = posicao;
        this.ultimo = ultimo;
        this.populacao2019 = populacao2019;
        this.populacao2020 = populacao2020;
        this.confirmadoPorMil = confirmadoPorMil;
        this.taxaMorte = taxaMorte;
    }

    public boolean isEquals(Object o){
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof Caso)) return false;
        Caso c = (Caso)o;
        if(c.estado.equals(estado) && c.cidade.equals(cidade) && c.posicao == posicao) return true;
        return false;
    }
}
