package br.org.fundatec.model.fundatec.model;

import javax.persistence.*;
import java.util.Objects;

public class Votos {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE , generator="voto_generator")
    @TableGenerator(name="voto_generator",
            table="chave",
            pkColumnName="id",
            valueColumnName="valor",
            allocationSize=100)
    @Column(name = "id")
    private Long id;

    @Column(name = "voto")
    private String nome;


    public Votos() {
    }

    public Votos(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Votos votos = (Votos) o;
        return Objects.equals(id, votos.id) && Objects.equals(nome, votos.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "Votos{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
