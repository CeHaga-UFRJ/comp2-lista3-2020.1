package covid.data;

import java.time.LocalDate;

public class Caso {
    private LocalDate data;
    private String estado;
    private String cidade;
    private boolean isEstado;
    private int confirmado;
    private int mortes;
    private int posicao;
    private boolean ultimo;
    private Integer populacao2019;
    private Integer populacao2020;
    private Double confirmadoPorMil;
    private double taxaMorte;

    public Caso(LocalDate data, String estado, String cidade, boolean isEstado, int confirmado, int mortes, int posicao, boolean ultimo, Integer populacao2019, Integer populacao2020, Double confirmadoPorMil, double taxaMorte) {
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

    public Caso(String caso){
        String[] dados = caso.split(",");
        data = LocalDate.parse(dados[0]);
        estado = dados[1];
        cidade = dados[2];
        isEstado = dados[3].equals("state");
        confirmado = Integer.parseInt(dados[4]);
        mortes = Integer.parseInt(dados[5]);
        posicao = Integer.parseInt(dados[6]);
        ultimo = dados[7].equals("True");
        populacao2019 = dados[8].isEmpty() ? null : Integer.parseInt(dados[8]);
        populacao2020 = dados[9].isEmpty() ? null : Integer.parseInt(dados[9]);
        try {
            confirmadoPorMil = Double.parseDouble(dados[11]);
        }catch(NumberFormatException e){
            confirmadoPorMil = null;
        }
        taxaMorte = Double.parseDouble(dados[12]);
    }

    public boolean isEstado(){
        return this.isEstado;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(int confirmado) {
        this.confirmado = confirmado;
    }

    public int getMortes() {
        return mortes;
    }

    public void setMortes(int mortes) {
        this.mortes = mortes;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public boolean isUltimo() {
        return ultimo;
    }

    public void setUltimo(boolean ultimo) {
        this.ultimo = ultimo;
    }

    public Integer getPopulacao2019() {
        return populacao2019;
    }

    public void setPopulacao2019(Integer populacao2019) {
        this.populacao2019 = populacao2019;
    }

    public Integer getPopulacao2020() {
        return populacao2020;
    }

    public void setPopulacao2020(Integer populacao2020) {
        this.populacao2020 = populacao2020;
    }

    public Double getConfirmadoPorMil() {
        return confirmadoPorMil;
    }

    public void setConfirmadoPorMil(Double confirmadoPorMil) {
        this.confirmadoPorMil = confirmadoPorMil;
    }

    public double getTaxaMorte() {
        return taxaMorte;
    }

    public void setTaxaMorte(double taxaMorte) {
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

    public String toString(){
        String nome = isEstado ? estado : cidade;
        return "Caso num "+posicao+" referente a "+nome;
    }
}
