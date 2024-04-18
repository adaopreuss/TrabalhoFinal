package br.org.fundatec.model.fundatec.controller;



import br.org.fundatec.model.fundatec.exception.AplicacaoException;
import br.org.fundatec.model.fundatec.model.Funcionario;
import br.org.fundatec.model.fundatec.model.Restaurante;
import br.org.fundatec.model.fundatec.model.Votos;
import br.org.fundatec.model.fundatec.repository.FuncionarioRepository;
import br.org.fundatec.model.fundatec.repository.RestauranteRepository;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

public class EmpresaController {

    private FuncionarioRepository funcionarioRepository;
    private RestauranteRepository restauranteRepository;
    private EntityManager em;


    public EmpresaController() {
        this.funcionarioRepository = new FuncionarioRepository();
        this.restauranteRepository = new RestauranteRepository();
    }


    public void inserirFuncionario(String nome, String restauranteNome, Calendar voto) throws AplicacaoException {
        Restaurante restaurante = restauranteRepository.buscarPorNome(restauranteNome);

        if (restaurante == null) {
            restaurante = new Restaurante(restauranteNome);
        }

        Funcionario funcionario = new Funcionario(nome);
        funcionario.add(new Votos(voto, restaurante));


        try {
            funcionarioRepository.inserir(funcionario);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new AplicacaoException("Falha ao inserir Funcionario");
        }
    }

    public void inserirNota(Integer idFuncionario, String restauranteNome, Calendar voto) throws AplicacaoException {
        Funcionario funcionario = funcionarioRepository.buscar(idFuncionario);

        if (funcionario == null) {
            throw new AplicacaoException("Funcionario nao encontrado");
        }
        Restaurante restaurante = restauranteRepository.buscarPorNome(restauranteNome);

        if (restaurante == null) {
            restaurante = new Restaurante(restauranteNome);
        }

        funcionario.add(new Votos(voto, restaurante));

        try {
            funcionarioRepository.atualizar(funcionario);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new AplicacaoException("Falha ao inserir Voto");
        }
    }

    public List<Funcionario> listarFuncionario() {
        return funcionarioRepository.buscar();
    }

    public List<Restaurante> listarRestaurante() {
        return restauranteRepository.buscar();
    }

    public List<Votos> busca (Funcionario funcionario, Calendar data){
        TypedQuery<Votos> query =
         this.em.createQuery
                 ("select e from Votos e where e. funcionario = :funcionario and e.data", Votos.class);
        Query.setParameter(("funcionario"), funcionario);
        query.setParameter("dada", data, TemporalType.DATE);

        try {
            return query.getResultList();
        }catch (NoResultException e ){
            return null;
        }
    }


    
}