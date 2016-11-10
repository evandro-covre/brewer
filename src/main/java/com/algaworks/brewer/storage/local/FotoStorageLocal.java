package com.algaworks.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import com.algaworks.brewer.storage.FotoStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * Created by evandro on 24/09/16.
 */
public class FotoStorageLocal implements FotoStorage{

    private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageLocal.class);

    private Path local;
    private Path localTemp;

    public FotoStorageLocal(){
        this.local = getDefault().getPath(System.getenv("HOME"), ".brewerfotos");
        criarPastas();
    }

    public FotoStorageLocal(Path path){
        this.local = path;
        criarPastas();
    }

    @Override
    public String salvarTemporariamente(MultipartFile[] files) {
        String novoNome = null;
        if (files != null && files.length > 0){
            MultipartFile arquivo = files[0];
            novoNome = renomearArquivo(arquivo.getOriginalFilename());

            try {
                arquivo.transferTo(new File(this.localTemp.toAbsolutePath().toString() + getDefault().getSeparator() +
                        novoNome));
            } catch (IOException e) {
                throw new RuntimeException("Erro ao salvar foto na pasta temporária", e);
            }
        }

        return novoNome;

    }

    private void criarPastas() {
        try {
            Files.createDirectories(this.local);
            this.localTemp = getDefault().getPath(this.local.toString(), "temp");
            Files.createDirectories(this.localTemp);

            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("Diretório das fotos criadas");
                LOGGER.debug("Diretório Padrão: "+ this.local.toAbsolutePath());
                LOGGER.debug("Diretório Temporário: "+this.localTemp.toAbsolutePath());

            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar pasta para gravar foto", e);
        }
    }


    private String renomearArquivo(String nomeOriginal){
        String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(String.format("Arquivo Original %s, renomeado - %s", nomeOriginal, novoNome));
        }

        return novoNome;
    }
}
