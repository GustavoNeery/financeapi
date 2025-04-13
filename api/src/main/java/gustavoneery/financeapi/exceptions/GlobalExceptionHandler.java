package gustavoneery.financeapi.exceptions;

import gustavoneery.financeapi.dto.FieldError;
import gustavoneery.financeapi.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors()
                .stream()
                .map(e -> new FieldError(e.getField(), e.getDefaultMessage()))
                .toList();
        return new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation Error", errors);
    }
}
