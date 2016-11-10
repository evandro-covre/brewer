package com.algaworks.brewer.repository;

import com.algaworks.brewer.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by evandro on 10/11/16.
 */
@Repository
public interface Estados extends JpaRepository<Estado, Long> {
}
