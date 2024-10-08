package com.sua_empresa;

import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.dao.UsuarioDAO;
import com.sua_empresa.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TelaLogin extends JFrame {
    private UsuarioDAO usuarioDAO; // Atributo para gerenciar a autenticação do usuário.

    public TelaLogin(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO; // Inicializa o DAO de usuários.
        setTitle("Tela de Login"); // Define o título da janela.
        setSize(300, 200); // Define o tamanho da janela.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão ao fechar a janela.

        // Componentes da interface
        JLabel labelNome = new JLabel("Nome:"); // Rótulo para o nome.
        JTextField textNome = new JTextField(20); // Campo de texto para o nome.
        JLabel labelSenha = new JLabel("Senha:"); // Rótulo para a senha.
        JPasswordField textSenha = new JPasswordField(20); // Campo de texto para a senha (com máscara).
        JButton buttonLogin = new JButton("Login"); // Botão de login.

        // Layout da interface
        JPanel panel = new JPanel();
        panel.add(labelNome);
        panel.add(textNome);
        panel.add(labelSenha);
        panel.add(textSenha);
        panel.add(buttonLogin);
        add(panel); // Adiciona o painel à janela.

        // Ação para o botão de login
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textNome.getText().trim(); // Obtém o nome do usuário.
                String senha = new String(textSenha.getPassword()).trim(); // Obtém a senha do usuário.

                // Verifica se os campos estão vazios.
                if (nome.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Nome e senha não podem estar vazios!");
                    return;
                }

                // Autentica o usuário
                Usuario usuario = usuarioDAO.autenticar(nome, senha);
                if (usuario != null) {
                    try {
                        // Estabelece conexão com o banco de dados
                        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gerenciamento_alunos", "postgres", "postgres");
                        AlunoDAO alunoDAO = new AlunoDAO(connection);
                        
                        // Redireciona para a tela do Administrador ou do Professor com base no tipo de usuário
                        if ("Admin".equalsIgnoreCase(usuario.getTipo())) {
                            new TelaAdministrador(alunoDAO).setVisible(true); // Abre a tela do administrador.
                        } else if ("Professor".equalsIgnoreCase(usuario.getTipo())) {
                            new TelaProfessor(alunoDAO).setVisible(true); // Abre a tela do professor.
                        }
                        dispose(); // Fecha a tela de login.
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(TelaLogin.this, "Erro ao conectar ao banco de dados: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Nome ou senha incorretos!"); // Mensagem de erro se a autenticação falhar.
                }
            }
        });
    }
}
