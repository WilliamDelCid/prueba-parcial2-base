package com.example.ejemplobackend.exception;

import com.example.ejemplobackend.dto.Mensaje;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestControllerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Mensaje> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<String> mensajes = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(err -> mensajes.add(err.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Mensaje(mensajes.stream().collect(Collectors.joining(","))));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Mensaje> handleCustomException(CustomException e){
        return ResponseEntity.status(e.getStatus())
                .body(new Mensaje(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Mensaje> handleException(Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new Mensaje(e.getMessage()));
    }




}
