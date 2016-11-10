package com.algaworks.brewer.service;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.Estilos;
import com.algaworks.brewer.service.exception.NomeEstiloJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by evandro on 20/09/16.
 */

@Service
public class CadastroEstiloService {
    @Autowired
    private Estilos estilos;

    @Transactional
    public Estilo salvar(Estilo estilo){

        Optional<Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome());

        // isPresent indica se a consulta retornou ou não algum registro.
        if(estiloOptional.isPresent()){
            throw new NomeEstiloJaCadastradoException("Nome do estilo já cadastrado");
        }

        return estilos.saveAndFlush(estilo);
    }

}
