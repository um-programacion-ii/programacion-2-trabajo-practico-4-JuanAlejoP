package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LibroRepositoryImpl implements LibroRepository {
    private final List<Libro> libros = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @Override
    public List<Libro> findAll() {
        return new ArrayList<>(libros);
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return libros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Libro> findByIsbn(String isbn) {
        return libros.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst();
    }

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

    @Override
    public void deleteById(Long id) {
        libros.removeIf(l -> l.getId().equals(id));
    }
}