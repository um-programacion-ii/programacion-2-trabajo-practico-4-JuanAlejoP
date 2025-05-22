package com.juanalejop.biblioteca.biblioteca_spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manejador global de excepciones para la aplicación Biblioteca Spring Boot.
 * <p>
 * Captura las excepciones específicas lanzadas en los controladores y devuelve
 * respuestas HTTP apropiadas con el mensaje de error.
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción {@link LibroNoEncontradoException}.
     *
     * @param ex la excepción lanzada cuando no se encuentra un libro
     * @return una respuesta HTTP 404 (Not Found) con el mensaje de error
     */
    @ExceptionHandler(LibroNoEncontradoException.class)
    public ResponseEntity<String> handleLibroNotFound(LibroNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Maneja la excepción {@link UsuarioNoEncontradoException}.
     *
     * @param ex la excepción lanzada cuando no se encuentra un usuario
     * @return una respuesta HTTP 404 (Not Found) con el mensaje de error
     */
    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNotFound(UsuarioNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Maneja la excepción {@link PrestamoNoEncontradoException}.
     *
     * @param ex la excepción lanzada cuando no se encuentra un préstamo
     * @return una respuesta HTTP 404 (Not Found) con el mensaje de error
     */
    @ExceptionHandler(PrestamoNoEncontradoException.class)
    public ResponseEntity<String> handlePrestamoNotFound(PrestamoNoEncontradoException ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}