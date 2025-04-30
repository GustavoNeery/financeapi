package gustavoneery.financeapi.exceptions;

import gustavoneery.financeapi.dto.FieldError;
import gustavoneery.financeapi.dto.ResponseError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

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

    @ExceptionHandler({ HttpClientErrorException.NotFound.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseError> notFoundExceptionHandler(RuntimeException exception, HttpServletRequest req) {
        return this.buildErrorResponseEntity(exception, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ResponseError> buildErrorResponseEntity(Exception exception, HttpStatus statusCode) {
        ResponseError error = new ResponseError(statusCode.value(), exception.getMessage(), List.of());
        return new ResponseEntity<>(error, statusCode);
    }
}
