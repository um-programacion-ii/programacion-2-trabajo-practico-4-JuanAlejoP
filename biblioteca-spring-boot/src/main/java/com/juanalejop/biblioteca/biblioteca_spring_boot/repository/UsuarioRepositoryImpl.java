package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementación en memoria del {@link UsuarioRepository}.
 * <p>
 * Utiliza una lista interna y un contador atómico para asignar IDs.
 */
@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository{
    private final List<Usuario> usuarios = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(usuarios);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setId(nextId.getAndIncrement());
        } else {
            deleteById((usuario.getId()));
        }
        usuarios.add(usuario);
        return usuario;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        usuarios.removeIf(u -> u.getId().equals(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsById(Long id) {
        return usuarios.stream()
                .anyMatch(u -> u.getId().equals(id));
    }
}