package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.exception.LibroNoEncontradoException;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de {@link LibroService} que interactúa con el repositorio de libros.
 */
@Service
public class LibroServiceImpl implements LibroService{
    private final LibroRepository libroRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param libroRepository Repositorio JPA para operaciones CRUD de {@link Libro}.
     */
    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Libro buscarPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new LibroNoEncontradoException(isbn));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Libro actualizar(Long id, Libro libro) {
        if (!libroRepository.existsById(id)) {
            throw new LibroNoEncontradoException(id);
        }
        libro.setId(id);
        return libroRepository.save(libro);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
}