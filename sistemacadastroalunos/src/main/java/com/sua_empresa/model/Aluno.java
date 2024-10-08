package com.sua_empresa.model; // Declara o pacote da classe Aluno.

public class Aluno {
    private int id; // Identificador único do aluno.
    private String nome; // Nome do aluno.
    private int idade; // Idade do aluno.
    private String curso; // Curso em que o aluno está matriculado.
    private String matricula; // Número de matrícula do aluno.
    private double notaProva1; // Nota da primeira prova.
    private double notaProva2; // Nota da segunda prova.

    // Construtor da classe Aluno que inicializa os atributos id, nome, idade, curso e matrícula.
    public Aluno(int id, String nome, int idade, String curso, String matricula) {
        this.id = id; // Atribui o valor do parâmetro id ao atributo id.
        this.nome = nome; // Atribui o valor do parâmetro nome ao atributo nome.
        this.idade = idade; // Atribui o valor do parâmetro idade ao atributo idade.
        this.curso = curso; // Atribui o valor do parâmetro curso ao atributo curso.
        this.matricula = matricula; // Atribui o valor do parâmetro matricula ao atributo matricula.
    }

    // Getters e Setters

    // Retorna o id do aluno.
    public int getId() {
        return id;
    }

    // Retorna o nome do aluno.
    public String getNome() {
        return nome;
    }

    // Retorna a idade do aluno.
    public int getIdade() {
        return idade;
    }

    // Retorna o curso do aluno.
    public String getCurso() {
        return curso;
    }

    // Retorna a matrícula do aluno.
    public String getMatricula() {
        return matricula;
    }

    // Retorna a nota da primeira prova.
    public double getNotaProva1() {
        return notaProva1;
    }

    // Define a nota da primeira prova.
    public void setNotaProva1(double notaProva1) {
        this.notaProva1 = notaProva1; // Atribui o valor do parâmetro notaProva1 ao atributo notaProva1.
    }

    // Retorna a nota da segunda prova.
    public double getNotaProva2() {
        return notaProva2;
    }

    // Define a nota da segunda prova.
    public void setNotaProva2(double notaProva2) {
        this.notaProva2 = notaProva2; // Atribui o valor do parâmetro notaProva2 ao atributo notaProva2.
    }

    // Método que define a nova idade do aluno (não implementado).
    public void setIdade(int novaIdade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIdade'"); // Lança uma exceção indicando que o método não está implementado.
    }

    // Método que define o novo curso do aluno (não implementado).
    public void setCurso(String novoCurso) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCurso'"); // Lança uma exceção indicando que o método não está implementado.
    }

    // Método que define o novo nome do aluno (não implementado).
    public void setNome(String novoNome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNome'"); // Lança uma exceção indicando que o método não está implementado.
    }
}
