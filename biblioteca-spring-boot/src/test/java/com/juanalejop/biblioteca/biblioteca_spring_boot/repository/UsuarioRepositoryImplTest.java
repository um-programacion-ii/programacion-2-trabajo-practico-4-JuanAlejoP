package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRepositoryImplTest {
    private UsuarioRepositoryImpl repo;

    @BeforeEach
    void setUp() {
        repo = new UsuarioRepositoryImpl();
    }

    @Test
    void saveAndFindById() {
        Usuario usuario = new Usuario(null, "Juan", "juan@ejemplo.com", true);
        repo.save(usuario);

        Optional<Usuario> encontrado = repo.findById(usuario.getId());
        assertTrue(encontrado.isPresent(), "Debería encontrar el usuario por ID");
        assertEquals("Juan", encontrado.get().getNombre());
    }

    @Test
    void findAllAndDelete() {
        Usuario usuario1 = repo.save(new Usuario(null, "Marcelo", "marcelo@ejemplo.com", true));
        Usuario usuario2 = repo.save(new Usuario(null, "Jose", "jose@ejemplo.com", false));

        assertEquals(2, repo.findAll().size(), "Debería haber dos usuarios");
        repo.deleteById(usuario1.getId());
        assertEquals(1, repo.findAll().size(), "Debería quedar un solo usuario después de eliminar");
    }

    @Test
    void findById_encuentraYNoEncuentra() {
        Usuario usuario = repo.save(new Usuario(null, "Marcelo", "marcelo@ejmeplo.com", true));

        Optional<Usuario> encontrado = repo.findById(usuario.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("Marcelo", encontrado.get().getNombre());
        assertFalse(repo.findById(999L).isPresent());
    }

    @Test
    void existsById_funcionaCorrecto() {
        Usuario usuario = repo.save(new Usuario(null, "Jose", "jose@ejemplo.com", false));
        assertTrue(repo.existsById(usuario.getId()));
        assertFalse(repo.existsById(12345L));
    }
}