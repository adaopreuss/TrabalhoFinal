package br.org.fundatec.model.fundatec.controller;



import br.org.fundatec.model.fundatec.model.Restaurante;
import br.org.fundatec.model.fundatec.repository.FuncionarioRepository;
import br.org.fundatec.model.fundatec.repository.RestauranteRepository;


import javax.persistence.PersistenceException;
import java.util.List;

public class EmpresaController {

    private FuncionarioRepository funcionarioRepository;
    private RestauranteRepository restauranteRepository;


    public EmpresaController() {
        this.funcionarioRepository = new FuncionarioRepository();
        this.restauranteRepository = new RestauranteRepository();
    }


    public void inserirAluno(String nome, String materiaNome, Integer nota) throws  AplicacaoException {
        Restaurante restaurante = restauranteRepository.buscarPorNome(restauranteNome);

        if(restaurante == null) {
           restaurante = new Restaurante(restauranteNome);
        }

        Aluno aluno = new Aluno(nome);
        aluno.add(new Nota(nota, materia));

        try {
            alunoRepository.inserir(aluno);
        }catch (PersistenceException e) {
            e.printStackTrace();
            throw new AplicacaoException("Falha ao inserir Aluno");
        }
    }

    public void inserirNota(Integer idAluno, String materiaNome, Integer nota) throws  AplicacaoException {
        Aluno aluno = alunoRepository.buscar(idAluno);

        if(aluno == null) {
            throw new AplicacaoException("Aluno nao encontrado");
        }

        Materia materia = materiaRepository.buscarPorNome(materiaNome);

        if(materia == null) {
            materia = new Materia(materiaNome);
        }

        aluno.add(new Nota(nota, materia));

        try {
            alunoRepository.atualizar(aluno);
        }catch (PersistenceException e) {
            e.printStackTrace();
            throw new AplicacaoException("Falha ao inserir Nota");
        }
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.buscar();
    }

    public List<Materia> listarMaterias() {
        return materiaRepository.buscar();
    }

    public List<Nota> listarNotas(Integer idAluno) throws  AplicacaoException{
        Aluno aluno = alunoRepository.buscar(idAluno);

        if(aluno == null) {
            throw new AplicacaoException("Aluno nao encontrado");
        }

        return aluno.getNotas();
    }
}
