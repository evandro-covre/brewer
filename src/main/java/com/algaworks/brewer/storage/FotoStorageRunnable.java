package com.algaworks.brewer.storage;

import com.algaworks.brewer.controller.dto.FotoDTO;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by evandro on 24/09/16.
 */
public class FotoStorageRunnable implements Runnable {

    private MultipartFile[] files;
    // Resultado deixou de retonar uma String para retonar um DTO
    private DeferredResult<FotoDTO> resultado;

    private FotoStorage fotoStorage;

    public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado, FotoStorage fotoStorage) {
        this.files = files;
        this.resultado = resultado;
        this.fotoStorage = fotoStorage;
    }

    @Override
    public void run() {
        System.out.println(">> FotoStorageRunnable - Salvando");

        // Convertendo para retornar o FotoDTO
        String nomeFoto = this.fotoStorage.salvarTemporariamente(files);//files[0].getOriginalFilename();
        String contentType = files[0].getContentType();
        resultado.setResult(new FotoDTO(nomeFoto, contentType));
    }
}
