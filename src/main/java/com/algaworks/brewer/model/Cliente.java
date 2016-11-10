package com.algaworks.brewer.model;

import com.algaworks.brewer.model.validation.group.CnpjGroup;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by evandro on 07/09/16.
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "cliente_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "cli_id", nullable = false, updatable = false)
    private Long id;

    @NotBlank(message = "Razão Social obrigatório")
    @Column(name = "cli_nm_razao", length = 100, nullable = false)
    private String razaoSocial;

    @NotBlank(message = "Telefone obrigatório")
    @Column(name = "cli_fone1", length = 14, nullable = false)
    private String telefone;

    @NotBlank(message = "E-mail obrigatório")
    @Email(message = "E-mail inválido")
    @Column(name = "cli_email", length = 100, nullable = false)
    private String email;

    @NotBlank(message = "CNPJ obrigatório")
    @CNPJ(groups = CnpjGroup.class)
    @Column(name = "cli_cnpj", length = 20, nullable = false)
    private String cnpj;

    @NotBlank(message = "Situação obrigatório")
    @Column(name = "cli_ativo", length = 1, nullable = false)
    private String situacao;

    @Column(name="cli_tp_pessoa", length=1)
    private TipoPessoa tipoPessoa;

    @JoinColumn(name = "end_id", nullable = false)
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @NotNull(message = "Endereço de localização obrigatório")
    private Endereco endereco;

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if (this.id == null && other.id != null
                || this.id != null && !this.id.equals(other.id)) { //NOPMD
            return false;
        }
        return true;
    }

}