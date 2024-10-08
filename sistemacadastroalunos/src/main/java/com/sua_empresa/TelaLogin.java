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
    private UsuarioDAO usuarioDAO;

    public TelaLogin(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        setTitle("Tela de Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Componentes da interface
        JLabel labelNome = new JLabel("Nome:");
        JTextField textNome = new JTextField(20);
        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField textSenha = new JPasswordField(20);
        JButton buttonLogin = new JButton("Login");

        // Layout da interface
        JPanel panel = new JPanel();
        panel.add(labelNome);
        panel.add(textNome);
        panel.add(labelSenha);
        panel.add(textSenha);
        panel.add(buttonLogin);
        add(panel);

        // Ação para o botão de login
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textNome.getText().trim();
                String senha = new String(textSenha.getPassword()).trim();

                if (nome.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Nome e senha não podem estar vazios!");
                    return;
                }

                Usuario usuario = usuarioDAO.autenticar(nome, senha);
                if (usuario != null) {
                    try {
                        // Estabelece conexão com o banco de dados
                        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gerenciamento_alunos", "postgres", "postgres");
                        AlunoDAO alunoDAO = new AlunoDAO(connection);
                        
                        if ("Admin".equalsIgnoreCase(usuario.getTipo())) {
                            new TelaAdministrador(alunoDAO).setVisible(true);
                        } else if ("Professor".equalsIgnoreCase(usuario.getTipo())) {
                            new TelaProfessor(alunoDAO).setVisible(true);
                        }
                        dispose(); // Fecha a tela de login
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(TelaLogin.this, "Erro ao conectar ao banco de dados: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Nome ou senha incorretos!");
                }
            }
        });
    }
}
