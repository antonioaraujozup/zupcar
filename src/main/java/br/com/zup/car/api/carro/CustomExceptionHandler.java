package br.com.zup.car.api.carro;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> mensagens = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return ResponseEntity.badRequest().body(mensagens);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handlerConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        String constraintName = ex.getConstraintName();

        String message = "Erro de violação de restrição do banco de dados";

        if(constraintName.equals("UK_NOME")) {
            message = "Carro já cadastrado";
        }

        if(constraintName.equals("UK_PLACA")) {
            message = "Unidade com placa já cadastrada";
        }

        if(constraintName.equals("UK_CHASSI")) {
            message = "Unidade com chassi já cadastrado";
        }

        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=",""),
                "timestamp", LocalDateTime.now(),
                "message", message
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}
