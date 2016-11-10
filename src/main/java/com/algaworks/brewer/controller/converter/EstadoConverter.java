package com.algaworks.brewer.controller.converter;

import com.algaworks.brewer.model.Estado;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * Created by evandro on 04/11/16.
 */
public class EstadoConverter implements Converter<String, Estado> {

    @Override
    public Estado convert(String codigo) {

        if(!StringUtils.isEmpty(codigo)){
            Estado estado = new Estado();
            estado.setId(Long.valueOf(codigo));
            return estado;
        }

        return null;
    }

}
