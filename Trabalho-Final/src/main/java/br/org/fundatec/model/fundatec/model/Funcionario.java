package br.org.fundatec.model.fundatec.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE , generator="funcionario_generator")
    @TableGenerator(name="funcionario_generator",
            table="chave",
            pkColumnName="id",
            valueColumnName="valor",
            allocationSize=100)
    @Column(name = "id")

    private Integer id;
    @Column(name = "nome")
    private String nome;


    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "funcionario")
    private List<Votos> votos;

    public Funcionario() {
    }

    public Funcionario( String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
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
        Funcionario that = (Funcionario) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(votos, that.votos);
    }

    public List<Votos> getVotos() {
        return votos;
    }

    public void setVotos(List<Votos> votos) {
        this.votos = votos;
    }

    public void add(Votos voto) {
        if(votos == null) {
            votos = new ArrayList<>();
        }

        voto.setFuncionario(this);
        votos.add(voto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, votos);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", votos=" + votos +
                '}';
    }


}
