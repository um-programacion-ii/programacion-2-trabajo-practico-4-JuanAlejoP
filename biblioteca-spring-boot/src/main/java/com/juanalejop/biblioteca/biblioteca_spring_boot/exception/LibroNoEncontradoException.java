package com.juanalejop.biblioteca.biblioteca_spring_boot.exception;

public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(String isbn) {
      super("Libro no encontrado con ISBN: " + isbn);
    }
    public LibroNoEncontradoException(Long id) {
      super(("Libro no encontrado con ID: " + id));
    }
}
