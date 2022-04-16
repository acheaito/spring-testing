package com.cheaito.springtesting.handler;

import com.cheaito.springtesting.handler.domain.EndpointError;
import com.cheaito.springtesting.memo.exception.MemoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.time.Instant;

@ControllerAdvice(annotations = RestController.class)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        return createResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MemoNotFoundException.class)
    public ResponseEntity<?> handleMemoNotFoundException(MemoNotFoundException ex) {
        return createResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<?> createResponse(HttpStatus status, String message) {
        EndpointError errorBody = EndpointError
                .builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(message)
                .build();
        return new ResponseEntity<>(errorBody, status);
    }
}
