package com.sua_empresa;

import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.model.Aluno;
import com.sua_empresa.reports.RelatorioAluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaProfessor extends JFrame {
    private AlunoDAO alunoDAO;

    public TelaProfessor(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
        setTitle("Tela do Professor");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Componentes da interface
        JButton buttonListar = new JButton("Listar Alunos");
        JButton buttonRelatorio = new JButton("Gerar Relatório");
        JButton buttonBoletim = new JButton("Gerar Boletim");
        JButton buttonInserirNotas = new JButton("Inserir Notas"); // Novo botão para inserir notas

        // Layout da interface
        JPanel panel = new JPanel();
        panel.add(buttonListar);
        panel.add(buttonRelatorio);
        panel.add(buttonBoletim);
        panel.add(buttonInserirNotas); // Adicionando o botão de inserir notas
        add(panel);

        // Ação para listar alunos
        buttonListar.addActionListener(e -> {
            StringBuilder alunosStr = new StringBuilder("Alunos cadastrados:\n");
            for (Aluno aluno : alunoDAO.listarAlunos()) {
                alunosStr.append(aluno.getNome()).append(" - ").append(aluno.getCurso()).append("\n");
            }
            JOptionPane.showMessageDialog(this, alunosStr.toString());
        });

        // Ação para gerar relatório
        buttonRelatorio.addActionListener(e -> {
            String caminhoArquivo = JOptionPane.showInputDialog("Digite o caminho para salvar o relatório:");
            if (caminhoArquivo != null && !caminhoArquivo.isEmpty()) {
                RelatorioAluno relatorio = new RelatorioAluno(alunoDAO);
                try {
                    relatorio.gerarRelatorio(caminhoArquivo);
                    JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao gerar relatório: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Caminho do arquivo não pode estar vazio!");
            }
        });

       // Ação para gerar boletim
buttonBoletim.addActionListener(e -> {
    String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno para gerar o boletim:");
    if (matricula != null && !matricula.isEmpty()) {
        Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
        if (aluno != null) {
            StringBuilder boletim = new StringBuilder();
            boletim.append("Boletim Escolar\n");
            boletim.append("Nome: ").append(aluno.getNome()).append("\n");
            boletim.append("Curso: ").append(aluno.getCurso()).append("\n");
            boletim.append("Nota Prova 1: ").append(aluno.getNotaProva1()).append("\n"); // Atualizado para exibir Prova 1
            boletim.append("Nota Prova 2: ").append(aluno.getNotaProva2()).append("\n"); // Atualizado para exibir Prova 2
            JOptionPane.showMessageDialog(this, boletim.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Aluno não encontrado!");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Matrícula não pode estar vazia!");
    }
});


        // Ação para inserir notas
        buttonInserirNotas.addActionListener(e -> {
            String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno para inserir as notas:");
            if (matricula != null && !matricula.isEmpty()) {
                Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
                if (aluno != null) {
                    try {
                        // Inserindo a nota da Prova 1
                        String notaProva1Str = JOptionPane.showInputDialog("Digite a nota da Prova 1:");
                        double notaProva1 = Double.parseDouble(notaProva1Str);
                        aluno.setNotaProva1(notaProva1);

                        // Inserindo a nota da Prova 2
                        String notaProva2Str = JOptionPane.showInputDialog("Digite a nota da Prova 2:");
                        double notaProva2 = Double.parseDouble(notaProva2Str);
                        aluno.setNotaProva2(notaProva2);

                        JOptionPane.showMessageDialog(this, "Notas inseridas com sucesso para o aluno " + aluno.getNome());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Por favor, insira um número válido para as notas.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado!");
                }
            }
        });
    }
}
