package com.sua_empresa.model;

public class Curso {
    private int id; // ID do curso
    private String nome; // Nome do curso

    // Construtor
    public Curso(int id, String nome) {
        this.id = id; // Inicializa o ID do curso
        this.nome = nome; // Inicializa o nome do curso
    }

    // Getters e Setters
    public int getId() {
        return id; // Retorna o ID do curso
    }

    public void setId(int id) {
        this.id = id; // Permite alterar o ID do curso
    }

    public String getNome() {
        return nome; // Retorna o nome do curso
    }

    public void setNome(String nome) {
        this.nome = nome; // Permite alterar o nome do curso
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
