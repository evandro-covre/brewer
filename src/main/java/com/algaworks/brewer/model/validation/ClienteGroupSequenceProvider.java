package com.algaworks.brewer.model.validation;

import com.algaworks.brewer.model.Cliente;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evandro on 10/11/16.
 */
public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Cliente>{

    @Override
    public List<Class<?>> getValidationGroups(Cliente cliente) {
        List<Class<?>> grupos = new ArrayList<>();
        grupos.add(Cliente.class);

        if(isPessoaSelecionada(cliente)){
            grupos.add(cliente.getTipoPessoa().getGrupo());
        }
        return null;
    }

    private boolean isPessoaSelecionada(Cliente cliente) {
        return cliente != null && cliente.getTipoPessoa() != null;
    }

}
