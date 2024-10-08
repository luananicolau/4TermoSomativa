package com.sua_empresa; // Declara o pacote da classe TelaAdministrador.

import com.sua_empresa.dao.AlunoDAO; // Importa a classe AlunoDAO.
import com.sua_empresa.model.Aluno; // Importa a classe Aluno.
import com.sua_empresa.reports.RelatorioAluno; // Importa a classe RelatorioAluno.

import javax.swing.*; // Importa classes do pacote Swing para a interface gráfica.
import java.awt.event.ActionEvent; // Importa a classe ActionEvent para eventos de botão.
import java.awt.event.ActionListener; // Importa a interface ActionListener para escuta de eventos.
import java.sql.SQLException; // Importa a classe SQLException para tratamento de exceções SQL.

public class TelaAdministrador extends JFrame {
    private AlunoDAO alunoDAO; // Atributo para acessar os dados dos alunos.

    // Construtor da classe que inicializa a tela do administrador.
    public TelaAdministrador(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO; // Atribui a instância de AlunoDAO ao atributo alunoDAO.
        setTitle("Tela do Administrador"); // Define o título da janela.
        setSize(500, 400); // Define o tamanho da janela.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão ao fechar a janela.

        // Componentes da interface
        JLabel labelNome = new JLabel("Nome:");
        JTextField textNome = new JTextField(20);
        JLabel labelIdade = new JLabel("Idade:");
        JTextField textIdade = new JTextField(3);
        JLabel labelCurso = new JLabel("Curso:");
        JTextField textCurso = new JTextField(20);
        JLabel labelMatricula = new JLabel("Matrícula:");
        JTextField textMatricula = new JTextField(10);

        JButton buttonCadastrar = new JButton("Cadastrar Aluno");
        JButton buttonListar = new JButton("Listar Alunos");
        JButton buttonAtualizar = new JButton("Atualizar Aluno");
        JButton buttonExcluir = new JButton("Excluir Aluno");
        JButton buttonRelatorio = new JButton("Gerar Relatório");

        // Layout da interface
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Define o layout vertical para o painel.
        panel.add(labelNome); // Adiciona o rótulo de nome.
        panel.add(textNome); // Adiciona o campo de texto para o nome.
        panel.add(labelIdade); // Adiciona o rótulo de idade.
        panel.add(textIdade); // Adiciona o campo de texto para a idade.
        panel.add(labelCurso); // Adiciona o rótulo de curso.
        panel.add(textCurso); // Adiciona o campo de texto para o curso.
        panel.add(labelMatricula); // Adiciona o rótulo de matrícula.
        panel.add(textMatricula); // Adiciona o campo de texto para a matrícula.
        panel.add(buttonCadastrar); // Adiciona o botão de cadastrar.
        panel.add(buttonListar); // Adiciona o botão de listar.
        panel.add(buttonAtualizar); // Adiciona o botão de atualizar.
        panel.add(buttonExcluir); // Adiciona o botão de excluir.
        panel.add(buttonRelatorio); // Adiciona o botão de relatório.

        add(panel); // Adiciona o painel à janela.

        // Ação para cadastrar aluno
        buttonCadastrar.addActionListener(e -> {
            String nome = textNome.getText();
            int idade = Integer.parseInt(textIdade.getText());
            String curso = textCurso.getText();
            String matricula = textMatricula.getText();

            Aluno aluno = new Aluno(idade, nome, idade, curso, matricula); // Cria um novo aluno.
            try {
                alunoDAO.cadastrar(aluno); // Cadastra o aluno no banco de dados.
                JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!"); // Mensagem de sucesso.
                clearFields(textNome, textIdade, textCurso, textMatricula); // Limpa os campos.
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + ex.getMessage()); // Mensagem de erro.
            }
        });

        // Ação para listar alunos
        buttonListar.addActionListener(e -> {
            StringBuilder alunosStr = new StringBuilder("Alunos cadastrados:\n");
            for (Aluno aluno : alunoDAO.listarAlunos()) { // Itera sobre a lista de alunos.
                alunosStr.append(aluno.getNome()).append(" - ").append(aluno.getCurso()).append("\n"); // Adiciona informações ao StringBuilder.
            }
            JOptionPane.showMessageDialog(this, alunosStr.toString()); // Exibe a lista de alunos.
        });

        // Ação para atualizar aluno
        buttonAtualizar.addActionListener(e -> {
            String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno para atualizar:");
            if (matricula != null && !matricula.isEmpty()) {
                Aluno aluno = alunoDAO.buscarPorMatricula(matricula); // Busca o aluno pela matrícula.
                if (aluno != null) {
                    String novoNome = JOptionPane.showInputDialog("Novo nome:", aluno.getNome()); // Obtém o novo nome.
                    int novaIdade = Integer.parseInt(JOptionPane.showInputDialog("Nova idade:", aluno.getIdade())); // Obtém a nova idade.
                    String novoCurso = JOptionPane.showInputDialog("Novo curso:", aluno.getCurso()); // Obtém o novo curso.

                    aluno.setNome(novoNome); // Atualiza o nome do aluno.
                    aluno.setIdade(novaIdade); // Atualiza a idade do aluno.
                    aluno.setCurso(novoCurso); // Atualiza o curso do aluno.

                    try {
                        alunoDAO.atualizar(aluno); // Atualiza o aluno no banco de dados.
                        JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!"); // Mensagem de sucesso.
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao atualizar aluno: " + ex.getMessage()); // Mensagem de erro.
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado!"); // Mensagem caso o aluno não seja encontrado.
                }
            }
        });

        // Ação para excluir aluno
        buttonExcluir.addActionListener(e -> {
            String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno para excluir:");
            if (matricula != null && !matricula.isEmpty()) {
                try {
                    alunoDAO.excluir(matricula); // Exclui o aluno do banco de dados.
                    JOptionPane.showMessageDialog(this, "Aluno excluído com sucesso!"); // Mensagem de sucesso.
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir aluno: " + ex.getMessage()); // Mensagem de erro.
                }
            }
        });

        // Ação para gerar relatório
        buttonRelatorio.addActionListener(e -> {
            String caminhoArquivo = JOptionPane.showInputDialog("Digite o caminho para salvar o relatório:");
            if (caminhoArquivo != null && !caminhoArquivo.isEmpty()) {
                RelatorioAluno relatorio = new RelatorioAluno(alunoDAO); // Cria uma nova instância de RelatorioAluno.
                try {
                    relatorio.gerarRelatorio(caminhoArquivo); // Gera o relatório.
                    JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!"); // Mensagem de sucesso.
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao gerar relatório: " + ex.getMessage()); // Mensagem de erro.
                }
            } else {
                JOptionPane.showMessageDialog(this, "Caminho do arquivo não pode estar vazio!"); // Mensagem caso o caminho esteja vazio.
            }
        });
    }

    // Método para limpar os campos de texto
    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText(""); // Limpa cada campo de texto.
        }
    }
}
