package com.juanalejop.biblioteca.biblioteca_spring_boot.controller;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.EstadoLibro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.service.LibroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibroController.class)
class LibroControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LibroService libroService;

    @Test
    void getLibros_retornaListaVacia() throws Exception {
        when(libroService.obtenerTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/libros")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getLibroPorId_existe_retornaJson() throws Exception {
        when(libroService.buscarPorIsbn("1"))
                .thenReturn(new Libro(1L, "979-8888771389", "The Fragrant Flower Blooms with Dignity 1", "Saka Mikami", EstadoLibro.DISPONIBLE));

        mockMvc.perform(get("/api/libros/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("The Fragrant Flower Blooms with Dignity 1"));
        verify(libroService).buscarPorIsbn("1");
    }

    @Test
    void postCrearLibro_retornaOkYJson() throws Exception {
        Libro output = new Libro(5L, "979-8888771426", "The Fragrant Flower Blooms with Dignity 5", "Saka Mikami", EstadoLibro.DISPONIBLE);
        when(libroService.guardar(any())).thenReturn(output);

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"979-888771426\",\"titulo\":\"The Fragrant Flower Blooms with Dignity 5\",\"autor\":\"Saka Mikami\",\"estado\":\"DISPONIBLE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.isbn").value("979-8888771426"));
        verify(libroService).guardar(any());
    }

    @Test
    void putActualizarLibro_retornaJsonActualizado() throws Exception {
        Libro actualizado = new Libro(2L, "979-8888771396", "The Fragrant Flower Blooms with Dignity 2", "Saka Mikami", EstadoLibro.PRESTADO);
        when(libroService.actualizar(eq(2L), any())).thenReturn(actualizado);

        mockMvc.perform(put("/api/libros/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"979-8888771396\",\"titulo\":\"The Fragrant Flower Blooms with Dignity 2\",\"autor\":\"Saka Mikami\",\"estado\":\"PRESTADO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath(".estado").value("PRESTADO"));
        verify(libroService).actualizar(eq(2L), any());
    }

    @Test
    void deleteLibro_retornaNoContent() throws Exception {
        doNothing().when(libroService).eliminar(3L);

        mockMvc.perform(delete("/api/libros/3"))
                .andExpect(status().isOk());
        verify(libroService).eliminar(3L);
    }
}