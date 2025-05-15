package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.EstadoLibro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibroRepositoryImplTest {
    private LibroRepositoryImpl repo;

    @BeforeEach
    void setUp() {
        repo = new LibroRepositoryImpl();
    }

    @Test
    void saveAndFindById() {
        Libro libro = new Libro(null, "979-8888771389", "The Fragrant Flower Blooms with Dignity 1", "Saka Mikami", EstadoLibro.DISPONIBLE);
        repo.save(libro);
        Optional<Libro> encontrado = repo.findById(libro.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("The Fragrant Flower Blooms with Dignity 1", encontrado.get().getTitulo());
    }

    @Test
    void findAllAndDelete() {
        Libro l1 = repo.save(new Libro(null, "979-8888771389", "The Fragrant Flower Blooms with Dignity 1", "Saka Mikami", EstadoLibro.DISPONIBLE));
        Libro l2 = repo.save(new Libro(null, "979-8888771396", "The Fragrant Flower Blooms with Dignity 2", "Saka Mikami", EstadoLibro.PRESTADO));
        assertEquals(2, repo.findAll().size());
        repo.deleteById(l1.getId());
        assertEquals(1, repo.findAll().size());
    }
}