package com.algaworks.brewer.repository;

import com.algaworks.brewer.model.Estilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by evandro on 16/09/16.
 */
@Repository
public interface Estilos extends JpaRepository<Estilo, Long> {

    /**
     * Option: indica que pode ou não existir o resultado
     * findByNOMEATRIBUTOIgnoreCase - Nome padrão do método sendo que NOMEATRIBUTO deverá ser o atributo
     * que desejamos consultar
     * @return - Instância do Objeto Estilo.
     */
    public Optional<Estilo> findByNomeIgnoreCase(String nome);

}
