package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.exception.LibroNoEncontradoException;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.EstadoLibro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.repository.LibroRepository;
import com.juanalejop.biblioteca.biblioteca_spring_boot.repository.LibroRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la implementación de servicio de libros (LibroServiceImpl).
 * <p>
 * Evalúa lógica de negocio: búsqueda, guardado, actualización y eliminación de libros,
 * manejando casos de éxito y excepciones.
 */
@ExtendWith(MockitoExtension.class)
class LibroServiceImplTest {
    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    /**
     * Cuando se busca un libro existente por ISBN, retorna el objeto libro.
     */
    @Test
    void cuandoBuscarPorIsbnExiste_entoncesRetornaLibro() {
        String isbn = "979-8888771389";
        Libro esperado = new Libro(1L, isbn, "The Fragrant Flower Blooms with Dignity 1", "Saka Mikami", EstadoLibro.DISPONIBLE);
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.of(esperado));

        Libro resultado = libroService.buscarPorIsbn(isbn);

        assertNotNull(resultado);
        assertEquals(isbn, resultado.getIsbn());
        verify(libroRepository).findByIsbn(isbn);
    }


    /**
     * Cuando se busca un libro inexistente por ISBN, lanza LibroNoEncontradoException.
     */
    @Test
    void cuandoBuscarPorIsbnNoExiste_entoncesLanzaExcepcion() {
        String isbn = "979-8888771389";
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.empty());

        assertThrows(LibroNoEncontradoException.class,
                () -> libroService.buscarPorIsbn(isbn));
    }

    /**
     * Verifica que guardar delegue en el repositorio y retorne el objeto guardado.
     */
    @Test
    void guardar_delegaEnRepository() {
        Libro input = new Libro(null, "979-8888771389", "The Fragrant Flower Blooms with Dignity 1", "Saka Mikami", EstadoLibro.DISPONIBLE);
        Libro guardado = new Libro(1L, "979-8888771389", "The Fragran Flower Blooms with Dignity 1", "Saka Mikami", EstadoLibro.DISPONIBLE);
        when(libroRepository.save(input)).thenReturn(guardado);

        Libro resultado = libroService.guardar(input);

        assertEquals(1L, resultado.getId());
        verify(libroRepository).save(input);
    }

    /**
     * Cuando se actualiza un libro con ID existente, retorna el libro con datos actualizados.
     */
    @Test
    void actualizar_conIdExistente_retornaLibroActualizado() {
        Long id = 2L;
        Libro existente = new Libro(id, "979-8888771396", "Kaoru Hana wa Rin to Saku 2", "Saka Mikami", EstadoLibro.DISPONIBLE);
        Libro updateData = new Libro(null, "979-8888771396", "The Fragrant Flower Blooms with Dignity 2", "Saka Mikami", EstadoLibro.DISPONIBLE);
        when(libroRepository.existsById(id)).thenReturn(true);
        when(libroRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Libro resultado = libroService.actualizar(id, updateData);

        assertEquals(id, resultado.getId());
        assertEquals("The Fragrant Flower Blooms with Dignity 2", resultado.getTitulo());
        verify(libroRepository).existsById(id);
        verify(libroRepository).save(updateData);
    }

    /**
     * Cuando se actualiza un libro con ID inexistente, lanza LibroNoEncontradoException.
     */
    @Test
    void actualizar_conIdInexistente_lanzaExcepcion() {
        when(libroRepository.existsById(3L)).thenReturn(false);

        assertThrows(LibroNoEncontradoException.class,
                () -> libroService.actualizar(3L, new Libro()));
        verify(libroRepository).existsById(3L);
    }

    /**
     * Verifica que eliminar delegue en el repositorio y no lance excepciones.
     */
    @Test
    void eliminar_delegaYNoLanza() {
        doNothing().when(libroRepository).deleteById(5L);

        assertDoesNotThrow(() -> libroService.eliminar(5L));
        verify(libroRepository).deleteById(5L);
    }
}