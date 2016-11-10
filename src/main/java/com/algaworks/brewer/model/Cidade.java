package com.algaworks.brewer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by evandro on 29/09/16.
 */
@Entity
@Table(name="cidade")
public class Cidade implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "cidade_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cidade_seq", sequenceName = "cidade_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "cid_id", nullable = false, updatable = false)
    private Long id;

    @JoinColumn(name = "est_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @NotNull(message = "Estado obrigatório")
    @JsonIgnore
    private Estado estado;

    @Column(name="cid_descr", length = 100, nullable = false)
    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @Column(name="cid_ddd", length = 3, nullable = true)
    @JsonIgnore
    private String ddd;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(estado, cidade.estado) &&
                Objects.equals(nome, cidade.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estado, nome);
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", estado=" + estado +
                ", nome='" + nome + '\'' +
                ", ddd='" + ddd + '\'' +
                '}';
    }
}
