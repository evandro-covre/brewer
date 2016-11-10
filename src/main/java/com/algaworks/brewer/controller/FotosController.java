package com.algaworks.brewer.controller;

import com.algaworks.brewer.controller.dto.FotoDTO;
import com.algaworks.brewer.storage.FotoStorage;
import com.algaworks.brewer.storage.FotoStorageRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by evandro on 24/09/16.
 */

// Controlador RestController já agiliza a declaração, não sendo necessário as anotações de ResponseBody (EstiloController)
@RestController
@RequestMapping("/fotos")
public class FotosController {

    @Autowired
    private FotoStorage fotoStorage;

    /**
     * @RequestParam("files[]") MultipartFile[] files indica que o parâmetro de Request que está sendo passado
     * se chamará files[] e que deverá ser mapeado para files. o nome file[] foi abstraido do debug da página
     * quando estava sendo enviado um post da foto
     */
    @PostMapping
    public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files){

        DeferredResult<FotoDTO> resultado = new DeferredResult<>();

        // 14.4 - thread de salvamento da imagem
        Thread thread = new Thread(new FotoStorageRunnable(files, resultado, fotoStorage));
        thread.start();

        return resultado;

    }
}
