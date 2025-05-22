package com.juanalejop.biblioteca.biblioteca_spring_boot.exception;

/**
 * Excepción lanzada cuando no se encuentra un préstamo con el ID especificado.
 */
public class PrestamoNoEncontradoException extends RuntimeException {

  /**
   * Constructor para expeción de préstamo no encontrado.
   *
   * @param id el ID del préstamo buscado
   */
  public PrestamoNoEncontradoException(Long id) {
    super("Prestamo no encontrado con ID: " + id);
  }
}