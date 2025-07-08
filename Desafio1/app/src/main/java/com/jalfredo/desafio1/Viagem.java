package com.jalfredo.desafio1;

public class Viagem {

    private String nome;
    private String cidadeEstado;
    private int id;

    public Viagem() {
    }
    public Viagem(String nome, String cidadeEstado, int id) {
        this.nome = nome;
        this.cidadeEstado = cidadeEstado;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidadeEstado() {
        return cidadeEstado;
    }
    public void setCidadeEstado(String cidadeEstado) {
        this.cidadeEstado = cidadeEstado;
    }

    @Override
    public String toString() {
        return nome + " - " + cidadeEstado;
    }

    public static Viagem viagens[] = {new Viagem("Lago de Palmas", "Palmas-TO", 1), new Viagem("Rio Sono", "Jalapão-TO", 2),
            new Viagem("Rio Uruguai", "San Javier - AR", 3)};

}

