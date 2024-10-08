package com.sua_empresa.dao;

import com.sua_empresa.model.Aluno; // Importa a classe Aluno que representa a entidade Aluno.
import java.sql.Connection; // Importa a classe Connection para gerenciar a conexão com o banco de dados.
import java.sql.PreparedStatement; // Importa a classe PreparedStatement para executar consultas parametrizadas.
import java.sql.ResultSet; // Importa a classe ResultSet para manipular os resultados das consultas.
import java.sql.SQLException; // Importa a classe SQLException para tratar erros relacionados ao SQL.
import java.util.ArrayList; // Importa a classe ArrayList para armazenar a lista de alunos.
import java.util.List; // Importa a interface List para representar uma lista de objetos.

public class AlunoDAO {
    private Connection connection; // Declara a conexão com o banco de dados.

    // Construtor que recebe a conexão como parâmetro.
    public AlunoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para cadastrar um novo aluno no banco de dados.
    public void cadastrar(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO alunos (nome, idade, curso, matricula) VALUES (?, ?, ?, ?)"; // Consulta SQL para inserção.
        try (PreparedStatement stmt = connection.prepareStatement(sql)) { // Prepara a consulta.
            stmt.setString(1, aluno.getNome()); // Define o nome do aluno.
            stmt.setInt(2, aluno.getIdade()); // Define a idade do aluno.
            stmt.setString(3, aluno.getCurso()); // Define o curso do aluno.
            stmt.setString(4, aluno.getMatricula()); // Define a matrícula do aluno.
            stmt.executeUpdate(); // Executa a atualização no banco de dados.
        }
    }

    // Método para listar todos os alunos no banco de dados.
    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>(); // Cria uma lista para armazenar os alunos.
        String sql = "SELECT * FROM alunos"; // Consulta SQL para seleção de todos os alunos.

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // Executa a consulta e obtém o resultado.

            // Percorre os resultados e cria objetos Aluno.
            while (rs.next()) {
                Aluno aluno = new Aluno(
                    rs.getInt("id"), // Obtém o ID do aluno.
                    rs.getString("nome"), // Obtém o nome do aluno.
                    rs.getInt("idade"), // Obtém a idade do aluno.
                    rs.getString("curso"), // Obtém o curso do aluno.
                    rs.getString("matricula") // Obtém a matrícula do aluno.
                );
                alunos.add(aluno); // Adiciona o aluno à lista.
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro SQL.
        }

        return alunos; // Retorna a lista de alunos.
    }

    // Método para excluir um aluno pelo número de matrícula.
    public void excluir(String matricula) throws SQLException {
        String sql = "DELETE FROM alunos WHERE matricula = ?"; // Consulta SQL para exclusão.
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matricula); // Define a matrícula do aluno a ser excluído.
            stmt.executeUpdate(); // Executa a exclusão no banco de dados.
        }
    }

    // Método para atualizar os dados de um aluno.
    public void atualizar(Aluno aluno) throws SQLException {
        String sql = "UPDATE alunos SET nome = ?, idade = ?, curso = ? WHERE matricula = ?"; // Consulta SQL para atualização.
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome()); // Define o novo nome do aluno.
            stmt.setInt(2, aluno.getIdade()); // Define a nova idade do aluno.
            stmt.setString(3, aluno.getCurso()); // Define o novo curso do aluno.
            stmt.setString(4, aluno.getMatricula()); // Define a matrícula do aluno que está sendo atualizado.
            stmt.executeUpdate(); // Executa a atualização no banco de dados.
        }
    }

    // Método para buscar um aluno pelo número de matrícula.
    public Aluno buscarPorMatricula(String matricula) {
        String sql = "SELECT * FROM alunos WHERE matricula = ?"; // Consulta SQL para buscar aluno.
        Aluno aluno = null; // Declaração da variável aluno como nula.

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matricula); // Define a matrícula do aluno a ser buscado.
            ResultSet rs = stmt.executeQuery(); // Executa a consulta e obtém o resultado.
            if (rs.next()) { // Verifica se há um resultado.
                aluno = new Aluno(
                    rs.getInt("id"), // Obtém o ID do aluno.
                    rs.getString("nome"), // Obtém o nome do aluno.
                    rs.getInt("idade"), // Obtém a idade do aluno.
                    rs.getString("curso"), // Obtém o curso do aluno.
                    rs.getString("matricula") // Obtém a matrícula do aluno.
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro SQL.
        }

        return aluno; // Retorna o aluno encontrado ou nulo se não encontrado.
    }

    // Método não implementado para adicionar um aluno, caso necessário.
    public void adicionarAluno(Aluno aluno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionarAluno'"); // Lança uma exceção para indicar que o método não está implementado.
    }

    // Método para atualizar as notas do aluno.
    public void atualizarNotas(Aluno aluno) throws SQLException {
        String sql = "UPDATE alunos SET nota_prova1 = ?, nota_prova2 = ? WHERE matricula = ?"; // Consulta SQL para atualizar as notas.
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, aluno.getNotaProva1()); // Define a nota da primeira prova.
            stmt.setDouble(2, aluno.getNotaProva2()); // Define a nota da segunda prova.
            stmt.setString(3, aluno.getMatricula()); // Define a matrícula do aluno cujas notas estão sendo atualizadas.
            stmt.executeUpdate(); // Executa a atualização no banco de dados.
        }
    }
}
