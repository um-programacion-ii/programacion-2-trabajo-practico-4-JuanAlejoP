package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.exception.LibroNoEncontradoException;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.EstadoLibro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceImplTest {
    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

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

    @Test
    void cuandoBuscarPorIsbnNoExiste_entoncesLanzaExcepcion() {
        String isbn = "979-8888771389";
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.empty());

        assertThrows(LibroNoEncontradoException.class,
                () -> libroService.buscarPorIsbn(isbn));
    }
}