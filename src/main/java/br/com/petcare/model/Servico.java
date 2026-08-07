package br.com.petcare.model;


public class Servico {

    private int id;
    private String descricao;
    private double preco;
    private int duracaoMinutos;

    public Servico() {
    }

    public Servico(int id, String descricao, double preco, int duracaoMinutos) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.duracaoMinutos = duracaoMinutos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public String toString() {
        return "#" + id + " - " + descricao + " | R$ " + preco + " | " + duracaoMinutos + " min";
    }
}
