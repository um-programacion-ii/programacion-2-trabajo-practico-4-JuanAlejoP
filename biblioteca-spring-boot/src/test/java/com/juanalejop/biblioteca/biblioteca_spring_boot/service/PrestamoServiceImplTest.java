package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.exception.PrestamoNoEncontradoException;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.EstadoLibro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Prestamo;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Usuario;
import com.juanalejop.biblioteca.biblioteca_spring_boot.repository.PrestamoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PrestamoServiceImplTest {
    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    @Test
    void cuandoBuscaPorIdExiste_entoncesRetornaPrestamo() {
        Long id = 1L;
        Libro libro = new Libro(id, "979-8888771389", "The Fragrant Flower Blooms with Dignity", "Saka Mikami", EstadoLibro.DISPONIBLE);
        Usuario usuario = new Usuario(1L, "Juan", "juan@ejemplo.com", true);
        Prestamo esperado = new Prestamo(id, libro, usuario,
                LocalDate.now(),LocalDate.now().plusDays(7));
        when(prestamoRepository.findById(id)).thenReturn(Optional.of(esperado));

        Prestamo resultado = prestamoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(prestamoRepository).findById(id);
    }

    @Test
    void cuandoBuscarPorIdNoExiste_entoncesLanzaExcepcion() {
        Long id = 1L;
        when(prestamoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PrestamoNoEncontradoException.class,
                () -> prestamoService.buscarPorId(id));
    }
}