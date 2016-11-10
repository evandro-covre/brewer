package com.algaworks.brewer.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by evandro on 29/09/16.
 */
@Entity
@Table(name = "estado")
public class Estado implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "estado_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "estado_seq", sequenceName = "estado_seq", initialValue = 1, allocationSize = 1)
    @Column(name="est_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "est_sigla", nullable = false, length = 5)
    @NotBlank(message = "Sigla obrigatório")
    private String sigla;

    @Column(name = "est_nome", nullable = false, length = 30)
    @NotBlank(message = "Descrição obrigatório")
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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
        Estado estado = (Estado) o;
        return Objects.equals(sigla, estado.sigla) &&
                Objects.equals(nome, estado.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sigla, nome);
    }
}