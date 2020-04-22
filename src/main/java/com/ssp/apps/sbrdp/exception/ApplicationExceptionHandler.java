package com.ssp.apps.sbrdp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException() {
        return new ResponseEntity<Object>("Wowwwww... Sad to hear that some thing went wrong.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
