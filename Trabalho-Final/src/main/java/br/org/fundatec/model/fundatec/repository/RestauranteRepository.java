package br.org.fundatec.model.fundatec.repository;


import br.org.fundatec.model.fundatec.model.Restaurante;

import javax.persistence.*;
import java.util.List;

public class RestauranteRepository  {

    private EntityManager em;

    public RestauranteRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("votoDB");
        em = factory.createEntityManager();
    }

    public void inserir(Restaurante restaurante) {
        this.em.getTransaction().begin();
        this.em.persist(restaurante);
        this.em.getTransaction().commit();
    }

    public List<Restaurante> buscar() {
        TypedQuery<Restaurante> buscarTodosQuery = this.em.createQuery("select e from Restaurante e", Restaurante.class);
        return buscarTodosQuery.getResultList();
    }

    public Restaurante buscarPorNome(String nome) {
        TypedQuery<Restaurante> query = this.em.createQuery("select e from Restaurante e where e.nome like :nome", Restaurante.class);
        query.setParameter("nome", nome);

        try{
            return query.getSingleResult();
        }catch (NoResultException e) {
            return  null;
        }

    }
}
