package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.exception.UsuarioNoEncontradoException;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Usuario;
import com.juanalejop.biblioteca.biblioteca_spring_boot.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void cuandoBuscarPorIdExiste_entoncesRetornaUsuario() {
        Long id = 1L;
        Usuario esperado = new Usuario(id, "Juan", "juan@ejemplo.com", true);
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(esperado));

        Usuario resultado = usuarioService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(usuarioRepository).findById(id);
    }

    @Test
    void cuandoBuscarPorIdNoExiste_entoncesLanzaExcepcion() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class,
                () -> usuarioService.buscarPorId(id));
    }

    @Test
    void guardar_delegaEnRepository() {
        Usuario input = new Usuario(null, "Juan", "juan@ejemplo.com", true);
        Usuario guardado = new Usuario(10L, "Juan", "juan@ejemplo.com", true);
        when(usuarioRepository.save(input)).thenReturn(guardado);

        Usuario resultado = usuarioService.guardar(input);
        assertEquals(10L, resultado.getId());
        verify(usuarioRepository).save(input);
    }

    @Test
    void actualizar_conIdExistente_devuelveActualizado() {
        Long id = 7L;
        Usuario actualizado = new Usuario(null, "Marcelo", "marcelo@ejemplo.com", false);
        when(usuarioRepository.existsById(id)).thenReturn(true);
        when(usuarioRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Usuario resultado = usuarioService.actualizar(id, actualizado);
        assertEquals(id, resultado.getId());
        assertFalse(resultado.getActivo());
        verify(usuarioRepository).existsById(id);
        verify(usuarioRepository).save(actualizado);
    }

    @Test
    void eliminar_noLanza() {
        doNothing().when(usuarioRepository).deleteById(2L);
        assertDoesNotThrow(() -> usuarioService.eliminar(2L));
    }
}