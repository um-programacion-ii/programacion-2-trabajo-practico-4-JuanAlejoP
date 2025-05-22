/**
 * Pruebas unitarias para el controlador de préstamos (PrestamoController).
 * <p>
 * Utiliza MockMvc para simular peticiones HTTP y Mockito para simular dependencias de servicio.
 */
package com.juanalejop.biblioteca.biblioteca_spring_boot.controller;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.EstadoLibro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Prestamo;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Usuario;
import com.juanalejop.biblioteca.biblioteca_spring_boot.service.PrestamoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrestamoController.class)
class PrestamoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PrestamoService prestamoService;

    /**
     * Verifica que GET /api/prestamos retorne una lista vacía cuando no hay préstamos.
     * @throws Exception en caso de error de petición.
     */
    @Test
    void getPrestamos_retornaListaVacia() throws Exception {
        when(prestamoService.obtenerTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/prestamos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    /**
     * Verifica que GET /api/prestamos/{id} retorne el préstamo correspondiente.
     * @throws Exception en caso de error de petición.
     */
    @Test
    void getPrestamoPorId_existe_retornaJson() throws Exception {
        Libro libro = new Libro(1L,"979-8888771389","The Fragrant Flower Blooms with Dignity 1","Saka Mikami", EstadoLibro.DISPONIBLE);
        Usuario usuario = new Usuario(2L,"Juan","juan@ejemplo.com",true);
        Prestamo p = new Prestamo(5L, libro, usuario, LocalDate.of(2025,1,1), null);
        when(prestamoService.buscarPorId(5L)).thenReturn(p);

        mockMvc.perform(get("/api/prestamos/5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.libro.id").value(1));
        verify(prestamoService).buscarPorId(5L);
    }

    /**
     * Verifica que POST /api/prestamos cree un nuevo préstamo y retorne el JSON con los datos.
     * @throws Exception en caso de error de petición.
     */
    @Test
    void postCrearPrestamo_retornaOkYJson() throws Exception {
        Prestamo out = new Prestamo(7L,null,null,LocalDate.now(),LocalDate.now());
        when(prestamoService.guardar(any())).thenReturn(out);

        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fechaPrestamo\":\"2025-01-01\",\"fechaDevolucion\":\"2025-01-05\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7));
        verify(prestamoService).guardar(any());
    }

    /**
     * Verifica que DELETE /api/prestamos/{id} elimine un préstamo existente.
     * @throws Exception en caso de error de petición.
     */
    @Test
    void deletePrestamo_retornaOk() throws Exception {
        doNothing().when(prestamoService).eliminar(3L);

        mockMvc.perform(delete("/api/prestamos/3"))
                .andExpect(status().isOk());
        verify(prestamoService).eliminar(3L);
    }
}