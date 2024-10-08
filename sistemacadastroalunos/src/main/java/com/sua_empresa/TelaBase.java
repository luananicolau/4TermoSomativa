package com.sua_empresa; // Declara o pacote da classe TelaBase.

import javax.swing.*; // Importa classes do pacote Swing para a interface gráfica.

public class TelaBase {
    protected JFrame frame; // Atributo protegido para armazenar a janela principal.

    // Construtor da classe TelaBase.
    public TelaBase() {
        frame = new JFrame("Sistema de Gerenciamento de Alunos"); // Cria uma nova janela com o título especificado.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão ao fechar a janela.
        frame.setSize(500, 400); // Define o tamanho da janela.
    }

    // Método para mostrar a janela.
    public void mostrar() {
        frame.setVisible(true); // Torna a janela visível.
    }

    // Método para esconder a janela.
    public void esconder() {
        frame.setVisible(false); // Torna a janela invisível.
    }
}
