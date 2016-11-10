package com.algaworks.brewer.model;

import com.algaworks.brewer.validation.SKU;
import com.algaworks.brewer.validation.ValorPositivo;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by evandro on 31/08/16.
 */
@Entity
@Table(name="cerveja")
public class Cerveja implements Serializable{

    @Id
    @SequenceGenerator(name = "cerveja_seq", sequenceName = "cerveja_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "cerveja_seq", strategy= GenerationType.SEQUENCE)
    private Long codigo;

    @SKU
    @NotBlank(message = "SKU obrigatório")
    private String sku;

    @NotBlank(message = "Nome obrigatório")
    @Size(max=50, message = "Nome com máximo de 30 caracteres")
    private String nome;

    @NotBlank
    @Size(max = 200, message = "Tamanho da Descrição entre 1 e 50")
    private String descricao;

    @NotNull(message = "Valor obrigatório")
//    @ValorPositivo
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que R$ 0,01")
    @DecimalMax(value = "9999999.99", message = "Valor deve ser menor que R$ 9.999.999,99")
    private BigDecimal valor;

    @Column(name="teor_alcolico")
    @DecimalMax(value = "100.0", message = "Teor alcóolico deve ser menor que 100")
    private BigDecimal teorAlcoolico;

    @DecimalMax(value = "100.0", message = "Comissão deve ser menor que 100")
    @DecimalMin(value = "0.0", message = "Comissão deve ser maior que 0")
    private BigDecimal comissao;

    @Max(value=99999, message = "Quantidade em estoque deve ser menor que 99.999")
    @Column(name="quantidade_estoque")
    private Integer quantidadeEstoque;

    @Enumerated(EnumType.STRING)
    private Origem origem;

    @Enumerated(EnumType.STRING)
    private Sabor sabor;

    @ManyToOne
    @JoinColumn(name = "codigo_estilo")
    private Estilo estilo;

    @Size(max = 100, message = "Nome do Arquivo com no máximo 100 caracteres")
    private String foto;

    @Size(max = 100, message = "Nome do Arquivo com no máximo 100 caracteres")
    @Column(name = "content_type")
    private String contentType;

    /*
    Antes de persistir ou atualizar será chamado o método prePersistUpdate e passará o
    SKU para Uppercase
    O método tem que ser "private void"
     */
    @PrePersist
    @PreUpdate
    private void prePersistUpdate(){
        sku = sku.toUpperCase();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTeorAlcoolico() {
        return teorAlcoolico;
    }

    public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
        this.teorAlcoolico = teorAlcoolico;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cerveja cerveja = (Cerveja) o;

        return codigo != null ? codigo.equals(cerveja.codigo) : cerveja.codigo == null;

    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }
}
