package com.algaworks.brewer.repository;

import com.algaworks.brewer.model.Cerveja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by evandro on 14/09/16.
 */
@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long>{
    // O segundo parâmetro é o tipo de dado da coluna @Id

}
