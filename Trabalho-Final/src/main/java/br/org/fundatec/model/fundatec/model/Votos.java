package br.org.fundatec.model.fundatec.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
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

   @Temporal(TemporalType.DATE)
    @Column(name = "voto")
    private Calendar data;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id")
    private Funcionario funcionario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    private Restaurante restaurante;


    public Votos() {
        super();
    }

    public Votos(Calendar data, Restaurante restaurante) {
        super();
        this.data = data;
        this.restaurante = restaurante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Votos votos = (Votos) o;
        return Objects.equals(id, votos.id) && Objects.equals(data, votos.data) && Objects.equals(funcionario, votos.funcionario) && Objects.equals(restaurante, votos.restaurante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, funcionario, restaurante);
    }

    @Override
    public String toString() {
        return "Votos{" +
                "data=" + data +
                ", funcionario=" + funcionario +
                ", restaurante=" + restaurante +
                '}';
    }


}
