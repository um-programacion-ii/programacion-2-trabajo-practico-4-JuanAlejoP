package com.juanalejop.biblioteca.biblioteca_spring_boot.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
  public UsuarioNoEncontradoException(String isbn) {
    super("Usuario no encontrado con ISBN: " + isbn);
  }

  public UsuarioNoEncontradoException(Long id) {
    super(("Usuario no encontrado con ID: " + id));
  }
}