package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.EstadoLibro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Prestamo;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Usuario;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoRepositoryImplTest {
    private PrestamoRepositoryImpl repo;
    private Libro libro1;
    private Libro libro2;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        repo = new PrestamoRepositoryImpl();
        libro1 = new Libro(null, "979-8888771389", "The Fragrant Flower Blooms with Dignity 1", "Saka Mikami", EstadoLibro.DISPONIBLE);
        libro2 = new Libro(null, "979-8888771396", "The Fragrant Flower Blooms with Dignity 2", "Saka Mikami", EstadoLibro.PRESTADO);
        usuario = new Usuario(null, "Juan", "juan@ejemplo.com", true);
    }

    @Test
    void saveAndFindById() {
        LocalDate hoy = LocalDate.now();
        Prestamo prestamo = new Prestamo(null, libro1, usuario, hoy, null);

        repo.save(prestamo);

        Optional<Prestamo> encontrado = repo.findById(prestamo.getId());
        assertTrue(encontrado.isPresent(), "Debería encontrar el préstamo por ID");
        assertEquals(libro1.getIsbn(), encontrado.get().getLibro().getIsbn());
        assertEquals(hoy, encontrado.get().getFechaPrestamo());
    }

    @Test
    void findAllAndDelete() {
        LocalDate fecha = LocalDate.of(2025, 5, 1);
        Prestamo prestamo1 = repo.save(new Prestamo(null, libro1, usuario, fecha, null));
        Prestamo prestamo2 = repo.save(new Prestamo(null, libro2, usuario, fecha.plusDays(1), fecha.plusDays(7)));
        assertEquals(2, repo.findAll().size(), "Debería encontrar dos préstamos");

        repo.deleteById(prestamo1.getId());
        assertEquals(1, repo.findAll().size(), "Debería quedar un solo préstamo después de eliminar");
    }

    @Test
    void findById_encuentraYNoEncuentra() {
        Prestamo prestamo = repo.save(new Prestamo(null, libro1, usuario, LocalDate.now(), null));

        Optional<Prestamo> encontrado = repo.findById(prestamo.getId());
        assertTrue(encontrado.isPresent());
        assertEquals(prestamo.getId(), encontrado.get().getId());
        assertFalse(repo.findById(777L).isPresent());
    }

    @Test
    void existsById_funcionaCorrecto() {
        Prestamo prestamo = repo.save(new Prestamo(null, libro2, usuario, LocalDate.now(), null));
        assertTrue(repo.existsById(prestamo.getId()));
        assertFalse(repo.existsById(555L));
    }
}