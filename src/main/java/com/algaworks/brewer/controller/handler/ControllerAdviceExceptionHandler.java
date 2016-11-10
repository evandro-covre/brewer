package com.algaworks.brewer.controller.handler;

import com.algaworks.brewer.service.exception.NomeEstiloJaCadastradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by evandro on 22/09/16.
 */
@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    // Caso o software lance a excessão NomeEstiloJaCadastradoException e não esteja sendo tratado,
    // o Spring utilizará esse método como padrão para tratamento da excessão.
    @ExceptionHandler(NomeEstiloJaCadastradoException.class)
    public ResponseEntity<String> handleNomeEstiloJaCadastradoException(NomeEstiloJaCadastradoException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
