package com.sua_empresa.dao;

import com.sua_empresa.model.Usuario; // Importa a classe Usuario que representa a entidade Usuario.

import java.sql.Connection; // Importa a classe Connection para gerenciar a conexão com o banco de dados.
import java.sql.PreparedStatement; // Importa a classe PreparedStatement para executar consultas parametrizadas.
import java.sql.ResultSet; // Importa a classe ResultSet para manipular os resultados das consultas.
import java.sql.SQLException; // Importa a classe SQLException para tratar erros relacionados ao SQL.

public class UsuarioDAO {
    private Connection connection; // Declara a conexão com o banco de dados.

    // Construtor que recebe a conexão como parâmetro.
    public UsuarioDAO(Connection connection) {
        this.connection = connection; // Inicializa a conexão.
    }

    // Método para autenticar um usuário com base no nome e senha.
    public Usuario autenticar(String nome, String senha) {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?"; // Consulta SQL para autenticação.
        try (PreparedStatement stmt = connection.prepareStatement(sql)) { // Prepara a consulta.
            stmt.setString(1, nome); // Define o nome do usuário.
            stmt.setString(2, senha); // Define a senha do usuário.
            ResultSet rs = stmt.executeQuery(); // Executa a consulta e obtém o resultado.

            if (rs.next()) { // Verifica se há um resultado.
                String tipo = rs.getString("tipo"); // Obtém o tipo de usuário (ex: administrador, professor).
                return new Usuario(nome, senha, tipo); // Retorna um objeto Usuario com os dados encontrados.
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro SQL.
        }
        return null; // Retorna null se o usuário não for encontrado ou a senha estiver incorreta.
    }
}
