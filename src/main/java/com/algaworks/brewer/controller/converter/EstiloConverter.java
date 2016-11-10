package com.algaworks.brewer.controller.converter;

import com.algaworks.brewer.model.Estilo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * Created by evandro on 19/09/16.
 */
public class EstiloConverter implements Converter<String, Estilo> {

    /**
     * Pelos testes realizados em aula, se o valor da String for nulo, o Spring não tentará fazer a conversão
     * @param codigo - Código do Estilo selecionado em CadastroCerveja.html
     * @return - Devolve o objeto Estilo que foi encontrado
     */
    @Override
    public Estilo convert(String codigo) {

        if (!StringUtils.isEmpty(codigo)) {
            Estilo estilo = new Estilo();
            estilo.setCodigo(Long.valueOf(codigo));
            return estilo;
        }

        return null;
    }
}
