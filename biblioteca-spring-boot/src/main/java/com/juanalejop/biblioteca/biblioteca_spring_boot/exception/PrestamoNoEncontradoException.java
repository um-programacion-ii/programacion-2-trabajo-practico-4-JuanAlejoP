package com.juanalejop.biblioteca.biblioteca_spring_boot.exception;

public class PrestamoNoEncontradoException extends RuntimeException {
  public PrestamoNoEncontradoException(Long id) {
    super("Prestamo no encontrado con ID: " + id);
  }
}