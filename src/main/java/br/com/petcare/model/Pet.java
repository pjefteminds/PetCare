package br.com.petcare.model;


public class Pet {

    private int id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private Cliente cliente;

    public Pet() {
    }

    public Pet(int id, String nome, String especie, String raca, int idade, Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        String dono = (cliente != null) ? cliente.getNome() : "sem dono cadastrado";
        return "#" + id + " - " + nome + " (" + especie + ", " + raca + ", " + idade + " anos) - dono: " + dono;
    }
}
