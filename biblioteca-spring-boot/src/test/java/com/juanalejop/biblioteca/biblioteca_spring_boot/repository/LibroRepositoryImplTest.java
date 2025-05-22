package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.EstadoLibro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la implementación de repositorio de libros (LibroRepositoryImpl).
 * <p>
 * Verifica operaciones CRUD básicas: guardar, buscar por ID, buscar todos, eliminar y buscar por ISBN.
 */
class LibroRepositoryImplTest {
    private LibroRepositoryImpl repo;

    /**
     * Inicializa un nuevo repositorio antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        repo = new LibroRepositoryImpl();
    }

    /**
     * Verifica que al guardar un libro y luego buscarlo por ID, se obtenga el mismo.
     */
    @Test
    void saveAndFindById() {
        Libro libro = new Libro(null, "979-8888771389", "The Fragrant Flower Blooms with Dignity 1", "Saka Mikami", EstadoLibro.DISPONIBLE);
        repo.save(libro);
        Optional<Libro> encontrado = repo.findById(libro.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("The Fragrant Flower Blooms with Dignity 1", encontrado.get().getTitulo());
    }

    /**
     * Verifica que findAll retorne todos los libros guardados y deleteById elimine un libro.
     */
    @Test
    void findAllAndDelete() {
        Libro l1 = repo.save(new Libro(null, "979-8888771389", "The Fragrant Flower Blooms with Dignity 1", "Saka Mikami", EstadoLibro.DISPONIBLE));
        Libro l2 = repo.save(new Libro(null, "979-8888771396", "The Fragrant Flower Blooms with Dignity 2", "Saka Mikami", EstadoLibro.PRESTADO));
        assertEquals(2, repo.findAll().size());
        repo.deleteById(l1.getId());
        assertEquals(1, repo.findAll().size());
    }


    /**
     * Verifica que buscar por ISBN retorne el libro correcto o vacíe si no existe.
     */
    @Test
    void findByIsbn_encuentraYNoEncuentra() {
        Libro libro = repo.save(new Libro(null, "ABC", "T", "A", EstadoLibro.DISPONIBLE));
        assertTrue(repo.findByIsbn("ABC").isPresent());
        assertFalse(repo.findByIsbn("ZZZ").isPresent());
    }

    /**
     * Verifica que existsById funcione correctamente para IDs existentes y no existentes.
     */
    @Test
    void existsById_funcionaCorrecto() {
        Libro libro = repo.save(new Libro(null, "XYZ", "T2", "B", EstadoLibro.DISPONIBLE));
        assertTrue(repo.existsById(libro.getId()));
        assertFalse(repo.existsById(999L));
    }
}