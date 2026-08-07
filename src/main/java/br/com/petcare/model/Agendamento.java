package br.com.petcare.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {

    private int id;
    private LocalDate data;
    private LocalTime hora;
    private String status; // ex.: "AGENDADO", "EM_ANDAMENTO", "CONCLUIDO", "CANCELADO"
    private Pet pet;
    private Servico servico;
    private Funcionario funcionario;

    public Agendamento() {
        this.status = "AGENDADO";
    }

    public Agendamento(int id, LocalDate data, LocalTime hora, Pet pet, Servico servico, Funcionario funcionario) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.pet = pet;
        this.servico = servico;
        this.funcionario = funcionario;
        this.status = "AGENDADO";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "#" + id + " - " + data + " " + hora
                + " | pet: " + (pet != null ? pet.getNome() : "-")
                + " | servico: " + (servico != null ? servico.getDescricao() : "-")
                + " | funcionario: " + (funcionario != null ? funcionario.getNome() : "-")
                + " | status: " + status;
    }
}
