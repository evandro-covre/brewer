package com.algaworks.brewer.repository;

import com.algaworks.brewer.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by evandro on 10/11/16.
 */
public interface Cidades extends JpaRepository<Cidade, Long>{

    public List<Cidade> findByEstadoId(Long codigoEstado);
}
