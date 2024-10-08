package com.sua_empresa;

import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.dao.UsuarioDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Conexão ao banco de dados
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gerenciamento_alunos", "postgres", "postgres");
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

            // Exibir a tela de login
            TelaLogin telaLogin = new TelaLogin(usuarioDAO);
            telaLogin.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados: " + ex.getMessage());
        }
    }
}
