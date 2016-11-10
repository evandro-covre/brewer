package com.algaworks.brewer.config;

import com.algaworks.brewer.service.CadastroCervejaService;
import com.algaworks.brewer.storage.FotoStorage;
import com.algaworks.brewer.storage.local.FotoStorageLocal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by evandro on 19/09/16.
 */

@Configuration
@ComponentScan(basePackageClasses = CadastroCervejaService.class)
public class ServiceConfig {

    // Inversão de dependência para caso queiramos trocar o local da foto, apenas refazemos a
    // criação do objeto FotoStorageLocal();
    @Bean
    public FotoStorage fotoStorage(){
        return new FotoStorageLocal();
    }
}
