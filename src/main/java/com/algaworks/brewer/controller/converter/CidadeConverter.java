package com.algaworks.brewer.controller.converter;

import com.algaworks.brewer.model.Cidade;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * Created by evandro on 03/11/16.
 */
public class CidadeConverter implements Converter<String, Cidade> {

    @Override
    public Cidade convert(String codigo) {

        if(!StringUtils.isEmpty(codigo)){
            Cidade cidade = new Cidade();
            cidade.setId(Long.valueOf(codigo));
            return cidade;
        }

        return null;
    }
}
