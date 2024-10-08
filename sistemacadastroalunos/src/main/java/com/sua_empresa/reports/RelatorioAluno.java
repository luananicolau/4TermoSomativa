package com.sua_empresa.reports; // Declara o pacote da classe RelatorioAluno.

import com.sua_empresa.model.Aluno; // Importa a classe Aluno.
import com.sua_empresa.dao.AlunoDAO; // Importa a classe AlunoDAO.

import java.io.FileWriter; // Importa a classe FileWriter para escrita em arquivos.
import java.io.IOException; // Importa a classe IOException para tratamento de exceções.
import java.util.List; // Importa a classe List para uso de listas.

public class RelatorioAluno {
    private AlunoDAO alunoDAO; // Atributo para acessar os dados dos alunos.

    // Construtor da classe que inicializa o alunoDAO.
    public RelatorioAluno(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO; // Atribui a instância de AlunoDAO ao atributo alunoDAO.
    }

    // Método para gerar um relatório de alunos e salvar em um arquivo.
    public void gerarRelatorio(String caminhoArquivo) {
        List<Aluno> alunos = alunoDAO.listarAlunos(); // Obtém a lista de alunos do AlunoDAO.

        try (FileWriter writer = new FileWriter(caminhoArquivo)) { // Abre o arquivo para escrita.
            writer.write("Relatório de Alunos\n"); // Escreve o cabeçalho do relatório.
            writer.write("-------------------\n");

            // Loop para escrever as informações de cada aluno no relatório.
            for (Aluno aluno : alunos) {
                writer.write("Nome: " + aluno.getNome() + "\n"); // Nome do aluno.
                writer.write("Idade: " + aluno.getIdade() + "\n"); // Idade do aluno.
                writer.write("Curso: " + aluno.getCurso() + "\n"); // Curso do aluno.
                writer.write("Matrícula: " + aluno.getMatricula() + "\n"); // Matrícula do aluno.
                writer.write("-------------------\n"); // Separador entre os alunos.
            }

            System.out.println("Relatório gerado com sucesso!"); // Mensagem de sucesso.
        } catch (IOException e) { // Tratamento de exceção caso ocorra um erro ao escrever o arquivo.
            System.out.println("Erro ao gerar o relatório: " + e.getMessage()); // Mensagem de erro.
        }
    }
}
