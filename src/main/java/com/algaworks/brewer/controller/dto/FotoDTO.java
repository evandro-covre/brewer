package com.algaworks.brewer.controller.dto;

/**
 * Created by evandro on 24/09/16.
 */
public class FotoDTO {
    /**
     * Objeto DTO utilizado para trafegar as informações da foto que queremos gravar na base de dados.
     *
     */
    private String nome;
    private String contentType;

    public FotoDTO(String nome, String contentType) {
        this.nome = nome;
        this.contentType = contentType;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
