package br.com.zup.car.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnidadeJaReservadaException extends RuntimeException {

    public UnidadeJaReservadaException(String message) {
        super(message);
    }

}
