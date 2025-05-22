/**
 * Pruebas unitarias para el controlador de usuarios (UsuarioController).
 * <p>
 * Utiliza MockMvc para simular peticiones HTTP y Mockito para simular dependencias de servicio.
 */
package com.juanalejop.biblioteca.biblioteca_spring_boot.controller;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Usuario;
import com.juanalejop.biblioteca.biblioteca_spring_boot.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    /**
     * Verifica que GET /api/usuarios retorne una lista vacía cuando no hay usuarios.
     * @throws Exception en caso de error de petición.
     */
    @Test
    void getUsuarios_retornaListaVacia() throws Exception {
        when(usuarioService.obtenerTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/usuarios")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }


    /**
     * Verifica que GET /api/usuarios/{id} retorne el usuario correspondiente.
     * @throws Exception en caso de error de petición.
     */
    @Test
    void getUsuarioPorId_existe_retornaJson() throws Exception {
        when(usuarioService.buscarPorId(1L))
                .thenReturn(new Usuario(1L, "Juan", "juan@ejemplo.com", true));

        mockMvc.perform(get("/api/usuarios/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
        verify(usuarioService).buscarPorId(1L);
    }

    /**
     * Verifica que POST /api/usuarios cree un nuevo usuario y retorne el JSON con los datos.
     * @throws Exception en caso de error de petición.
     */
    @Test
    void postCrearUsuario_retornaOkYJson() throws Exception {
        Usuario out = new Usuario(10L, "Jose", "jose@ejemplo.com", false);
        when(usuarioService.guardar(any())).thenReturn(out);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Jose\",\"email\":\"jose@ejemplo.com\",\"activo\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.activo").value(false));
        verify(usuarioService).guardar(any());
    }

    /**
     * Verifica que DELETE /api/usuarios/{id} elimine un usuario existente.
     * @throws Exception en caso de error de petición.
     */
    @Test
    void deleteUsuario_retornaNoContent() throws Exception {
        doNothing().when(usuarioService).eliminar(2L);

        mockMvc.perform(delete("/api/usuarios/2"))
                .andExpect(status().isOk());
        verify(usuarioService).eliminar(2L);
    }
}