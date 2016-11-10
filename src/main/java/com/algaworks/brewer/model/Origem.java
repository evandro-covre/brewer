package com.algaworks.brewer.model;

/**
 * Created by evandro on 13/09/16.
 */
public enum Origem {
    NACIONAL("Nacional"),
    INTERNACIONAL("Internacional");

    private String descricao;

    Origem(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }


}
