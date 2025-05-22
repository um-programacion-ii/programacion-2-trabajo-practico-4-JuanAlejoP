package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;

import java.util.List;

/**
 * Interfaz de servicio para operaciones de gestión de libros.
 */
public interface LibroService {
    /**
     * Obtiene la lista de todos los libros almacenados.
     *
     * @return Lista de {@link Libro} existentes.
     */
    List<Libro> obtenerTodos();

    /**
     * Busca un libro por su ISBN.
     *
     * @param isbn Código ISBN del libro a buscar.
     * @return Instancia de {@link Libro} encontrada.
     * @throws com.juanalejop.biblioteca.biblioteca_spring_boot.exception.LibroNoEncontradoException Si no existe un libro con el ISBN proporcionado.
     */
    Libro buscarPorIsbn(String isbn);

    /**
     * Guarda un nuevo libro en el sistema.
     *
     * @param libro Objeto {@link Libro} a persistir.
     * @return La instancia de {@link Libro} guardada, incluyendo su ID asignado.
     */
    Libro guardar(Libro libro);

    /**
     * Actualiza los datos de un libro existente.
     *
     * @param id    Identificador del libro a actualizar.
     * @param libro Objeto {@link Libro} con los datos modificados.
     * @return La instancia de {@link Libro} actualizada.
     * @throws com.juanalejop.biblioteca.biblioteca_spring_boot.exception.LibroNoEncontradoException Si no existe un libro con el ID proporcionado.
     */
    Libro actualizar(Long id, Libro libro);

    /**
     * Elimina un libro por su identificador.
     *
     * @param id Identificador del libro a eliminar.
     */
    void eliminar(Long id);
}