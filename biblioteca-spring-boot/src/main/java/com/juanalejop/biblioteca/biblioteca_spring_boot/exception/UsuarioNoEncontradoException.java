package com.juanalejop.biblioteca.biblioteca_spring_boot.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
  public UsuarioNoEncontradoException(Long id) {
    super("Usuario no encontrado con ID: " + id);
  }
}