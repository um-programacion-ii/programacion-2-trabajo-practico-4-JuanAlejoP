package com.juanalejop.biblioteca.biblioteca_spring_boot.controller;

import com.juanalejop.biblioteca.biblioteca_spring_boot.service.PrestamoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrestamoController.class)
class PrestamoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PrestamoService prestamoService;

    @Test
    void getPrestamos_retornaListaVacia() throws Exception {
        when(prestamoService.obtenerTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/prestamos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}