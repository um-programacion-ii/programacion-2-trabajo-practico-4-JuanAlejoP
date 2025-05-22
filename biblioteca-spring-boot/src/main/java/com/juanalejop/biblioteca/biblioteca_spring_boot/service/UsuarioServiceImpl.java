package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.exception.PrestamoNoEncontradoException;
import com.juanalejop.biblioteca.biblioteca_spring_boot.exception.UsuarioNoEncontradoException;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Usuario;
import com.juanalejop.biblioteca.biblioteca_spring_boot.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de {@link UsuarioService} que interactúa con el repositorio de usuarios.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param usuarioRepository Repositorio JPA para operaciones CRUD de {@link Usuario}.
     */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoEncontradoException(id);
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}