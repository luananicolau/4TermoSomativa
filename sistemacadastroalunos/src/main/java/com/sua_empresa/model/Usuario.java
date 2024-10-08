package com.sua_empresa.model; // Declara o pacote da classe Usuario.

public class Usuario {
    private String nome; // Nome do usuário.
    private String senha; // Senha do usuário.
    private String tipo; // Tipo de usuário (ex: administrador, professor).

    // Construtor da classe Usuario que inicializa os atributos nome, senha e tipo.
    public Usuario(String nome, String senha, String tipo) {
        this.nome = nome; // Atribui o valor do parâmetro nome ao atributo nome.
        this.senha = senha; // Atribui o valor do parâmetro senha ao atributo senha.
        this.tipo = tipo; // Atribui o valor do parâmetro tipo ao atributo tipo.
    }

    // Getter para retornar o nome do usuário.
    public String getNome() {
        return nome;
    }

    // Getter para retornar a senha do usuário.
    public String getSenha() {
        return senha;
    }

    // Getter para retornar o tipo do usuário.
    public String getTipo() {
        return tipo;
    }
}
