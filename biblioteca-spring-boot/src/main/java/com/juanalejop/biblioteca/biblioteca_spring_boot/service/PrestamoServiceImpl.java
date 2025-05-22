package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.exception.PrestamoNoEncontradoException;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Prestamo;
import com.juanalejop.biblioteca.biblioteca_spring_boot.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de {@link PrestamoService} que interactúa con el repositorio de préstamos.
 */
@Service
public class PrestamoServiceImpl implements PrestamoService{
    private final PrestamoRepository prestamoRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param prestamoRepository Repositorio JPA para operaciones CRUD de {@link Prestamo}.
     */
    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNoEncontradoException(id));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prestamo guardar(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prestamo actualizar(Long id, Prestamo prestamo) {
        if (!prestamoRepository.existsById(id)) {
            throw new PrestamoNoEncontradoException(id);
        }
        prestamo.setId(id);
        return prestamoRepository.save(prestamo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(Long id) {
        prestamoRepository.deleteById(id);
    }
}