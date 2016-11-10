package com.algaworks.brewer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by evandro on 29/09/16.
 */
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable{

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(generator = "endcod_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "endcod_seq", sequenceName = "endcod_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "end_id", nullable = false, updatable = false)
    private Long id;

    @JoinColumn(name = "cid_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Cidade cidade;

    @Column(name = "end_logradouro", length = 100, nullable = false)
    private String logradouro;

    @Column(name = "end_numero", length = 10, nullable = false)
    private String numero;

    @Column(name = "end_complto", length = 40, nullable = true)
    private String complemento;

    @Column(name = "end_bairro", length = 50, nullable = true)
    private String bairro;

    @Column(name = "end_cep", length = 10, nullable = false)
    private String cep;

    @Transient // Indica que o objeto não irá para o banco de dados
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id) &&
                Objects.equals(cidade, endereco.cidade) &&
                Objects.equals(logradouro, endereco.logradouro) &&
                Objects.equals(numero, endereco.numero) &&
                Objects.equals(complemento, endereco.complemento) &&
                Objects.equals(bairro, endereco.bairro) &&
                Objects.equals(cep, endereco.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cidade, logradouro, numero, complemento, bairro, cep);
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", cidade=" + cidade +
                ", estado=" + estado +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}