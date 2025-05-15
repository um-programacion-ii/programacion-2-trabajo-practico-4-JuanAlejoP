package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroRepository {
    List<Libro> findAll();
    Optional<Libro> findById(Long id);
    Optional<Libro> findByIsbn(String isbn);
    Libro save(Libro libro);
    void deleteById(Long id);
    boolean existsById(Long id);
}