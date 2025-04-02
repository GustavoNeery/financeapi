package gustavoneery.financeapi.exceptions;

import gustavoneery.financeapi.dto.FieldError;
import gustavoneery.financeapi.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handlerBadRequest(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors().stream().map(e -> new FieldError(e.getField(), ex.toString())).toList();
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), errors);
    }
}
