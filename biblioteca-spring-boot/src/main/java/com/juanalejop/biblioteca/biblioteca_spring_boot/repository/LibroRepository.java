package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad {@link Libro}.
 * <p>
 * Define las operaciones CRUD básicas para gestionar libros en memoria.
 */
public interface LibroRepository {

    /**
     * Obtiene todos los libros almacenados.
     *
     * @return lista de todos los libros, vacía si no hay ninguno.
     */
    List<Libro> findAll();

    /**
     * Busca un libro por su identificador.
     *
     * @param id identificador único del libro.
     * @return {@link Optional} con el libro si existe, o vacío en caso contrario.
     */
    Optional<Libro> findById(Long id);

    /**
     * Busca un libro por su ISBN.
     *
     * @param isbn código ISBN del libro.
     * @return {@link Optional} con el libro si existe, o vacío en caso contrario.
     */
    Optional<Libro> findByIsbn(String isbn);

    /**
     * Guarda un libro en el repositorio. Si el libro no tiene ID, se asigna uno nuevo.
     * Si ya existe, reemplaza la entrada previa.
     *
     * @param libro instancia de {@link Libro} a guardar.
     * @return el libro guardado, con ID asignado.
     */
    Libro save(Libro libro);

    /**
     * Elimina un libro por su identificador.
     *
     * @param id identificador único del libro a eliminar.
     */
    void deleteById(Long id);

    /**
     * Comprueba si existe un libro con el ID dado.
     *
     * @param id identificador único a verificar.
     * @return {@code true} si existe, {@code false} en caso contrario.
     */
    boolean existsById(Long id);
}