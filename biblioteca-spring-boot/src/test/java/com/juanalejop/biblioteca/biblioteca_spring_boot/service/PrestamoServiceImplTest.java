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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la implementación de servicio de préstamos (PrestamoServiceImpl).
 * <p>
 * Asegura la lógica de negocio para búsqueda, guardado, actualización y eliminación de préstamos,
 * incluyendo manejo de excepciones.
 */
@ExtendWith(MockitoExtension.class)
class PrestamoServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(PrestamoServiceImplTest.class);
    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    /**
     * Cuando se busca un préstamo existente por ID, retorna el objeto préstamo.
     */
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

    /**
     * Cuando se busca un préstamo inexistente, lanza PrestamoNoEncontradoException.
     */
    @Test
    void cuandoBuscarPorIdNoExiste_entoncesLanzaExcepcion() {
        Long id = 1L;
        when(prestamoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PrestamoNoEncontradoException.class,
                () -> prestamoService.buscarPorId(id));
    }

    /**
     * Verifica que guardar delegue correctamente en el repositorio.
     */
    @Test
    void guardar_delegaCorrectamente() {
        Prestamo input = new Prestamo(null, null, null, LocalDate.now(), null);
        when(prestamoRepository.save(input)).thenReturn(input);

        Prestamo resultado = prestamoService.guardar(input);
        assertNotNull(resultado);
        verify(prestamoRepository).save(input);
    }


    /**
     * Cuando se actualiza un préstamo con ID inexistente, lanza PrestamoNoEncontradoException.
     */
    @Test
    void actualizar_conIdInexistente_lanzaExcepcion() {
        when(prestamoRepository.existsById(9L)).thenReturn(false);
        assertThrows(PrestamoNoEncontradoException.class,
        () -> prestamoService.actualizar(9L, new Prestamo()));
    }

    /**
     * Verifica que eliminar delegue en el repositorio sin lanzar excepciones.
     */
    @Test
    void eliminar_delegaYNoLanza() {
        doNothing().when(prestamoRepository).deleteById(4L);
        assertDoesNotThrow(() -> prestamoService.eliminar(4L));
    }
}