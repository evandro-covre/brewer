package com.algaworks.brewer.service;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.Cervejas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by evandro on 19/09/16.
 */
@Service
public class CadastroCervejaService {

    @Autowired
    private Cervejas cervejas;

    @Transactional
    public void salvar(Cerveja cerveja){
        cervejas.save(cerveja);
    }

}
