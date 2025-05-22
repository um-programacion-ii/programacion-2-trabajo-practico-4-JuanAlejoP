package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementación en memoria del {@link LibroRepository}.
 * <p>
 * Utiliza una lista interna y un contador atómico para asignar IDs.
 */
@Repository
public class LibroRepositoryImpl implements LibroRepository {
    private final List<Libro> libros = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Libro> findAll() {
        return new ArrayList<>(libros);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Libro> findById(Long id) {
        return libros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Libro> findByIsbn(String isbn) {
        return libros.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Libro save(Libro libro) {
        if (libro.getId() == null) {
            libro.setId(nextId.getAndIncrement());
        } else {
            deleteById((libro.getId()));
        }
        libros.add(libro);
        return libro;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        libros.removeIf(l -> l.getId().equals(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsById(Long id) {
        return libros.stream()
                .anyMatch(l -> l.getId().equals(id));
    }
}