package com.algaworks.brewer.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by evandro on 13/09/16.
 */
@Entity
@Table(name="estilo")
public class Estilo implements Serializable{


    @Id
    @SequenceGenerator(name = "estilo_seq", sequenceName = "estilo_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "estilo_seq", strategy= GenerationType.SEQUENCE)
    private Long codigo;

    @NotBlank(message = "Nome é obrigatório!")
    @Size(max = 20, message = "O Nome só pode conter até 20 caracteres")
    private String nome;

    @OneToMany(mappedBy = "estilo")
    private List<Cerveja> cervejas;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cerveja> getCervejas() {
        return cervejas;
    }

    public void setCervejas(List<Cerveja> cervejas) {
        this.cervejas = cervejas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estilo estilo = (Estilo) o;

        return codigo != null ? codigo.equals(estilo.codigo) : estilo.codigo == null;

    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

}
