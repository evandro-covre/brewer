package com.algaworks.brewer.service.exception;

/**
 * Created by evandro on 21/09/16.
 */
public class NomeEstiloJaCadastradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Método de retorno da Excessão
     * @param message - Mensagem de retorno da excessão.
     */
    public NomeEstiloJaCadastradoException(String message) {
        super(message);
    }

}
