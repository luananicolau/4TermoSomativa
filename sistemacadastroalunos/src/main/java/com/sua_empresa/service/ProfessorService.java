package com.sua_empresa.service;

import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.model.Aluno;

import javax.swing.*;
import java.util.List;

public class ProfessorService {
    private AlunoDAO alunoDAO;

    public ProfessorService(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public List<Aluno> listarAlunos() {
        return alunoDAO.listarAlunos();
    }

    public String gerarBoletim(String matricula) {
        Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
        if (aluno == null) {
            return "Aluno não encontrado!";
        }
        return String.format("Boletim Escolar\nNome: %s\nCurso: %s\nNota Prova 1: %.2f\nNota Prova 2: %.2f",
                aluno.getNome(), aluno.getCurso(), aluno.getNotaProva1(), aluno.getNotaProva2());
    }

    public String inserirNotas(String matricula, double notaProva1, double notaProva2) {
        Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
        if (aluno == null) {
            return "Aluno não encontrado!";
        }
        aluno.setNotaProva1(notaProva1);
        aluno.setNotaProva2(notaProva2);
        return "Notas inseridas com sucesso para o aluno " + aluno.getNome();
    }
}
