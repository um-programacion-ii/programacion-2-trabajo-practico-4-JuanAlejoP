package com.juanalejop.biblioteca.biblioteca_spring_boot.exception;

/**
 * Excepción lanzada cuando no se encuentra un libro con un ISBN o ID específicos.
 */
public class LibroNoEncontradoException extends RuntimeException {

    /**
     * Constructor para expeción de libro no encontrado por ISBN.
     *
     * @param isbn el ISBN del libro buscado
     */
    public LibroNoEncontradoException(String isbn) {
      super("Libro no encontrado con ISBN: " + isbn);
    }

    /**
     * Constructor para expeción de libro no encontrado por ID.
     *
     * @param id el ID del libro buscado
     */
    public LibroNoEncontradoException(Long id) {
      super("Libro no encontrado con ID: " + id);
    }
}