package com.juanalejop.biblioteca.biblioteca_spring_boot.exception;

/**
 * Excepción lanzada cuando no se encuentra un usuario con el ID especificado.
 */
public class UsuarioNoEncontradoException extends RuntimeException {

  /**
   * Constructor para expeción de usuario no encontrado.
   *
   * @param id el ID del usuario buscado
   */
  public UsuarioNoEncontradoException(Long id) {
    super("Usuario no encontrado con ID: " + id);
  }
}