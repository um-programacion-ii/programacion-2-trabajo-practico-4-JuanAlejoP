package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Prestamo;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad {@link Prestamo}.
 * <p>
 * Define operaciones CRUD básicas para gestionar préstamos.
 */
public interface PrestamoRepository {

    /**
     * Obtiene todos los préstamos almacenados.
     *
     * @return lista de todos los préstamos, vacía si no hay ninguno.
     */
    List<Prestamo> findAll();

    /**
     * Busca un préstamo por su identificador.
     *
     * @param id identificador único del préstamo.
     * @return {@link Optional} con el préstamo si existe, o vacío en caso contrario.
     */
    Optional<Prestamo> findById(Long id);

    /**
     * Guarda un préstamo en el repositorio. Si el préstamo no tiene ID, se asigna uno nuevo.
     * Si ya existe, reemplaza la entrada previa.
     *
     * @param prestamo instancia de {@link Prestamo} a guardar.
     * @return el préstamo guardado, con ID asignado.
     */
    Prestamo save(Prestamo prestamo);

    /**
     * Elimina un préstamo por su identificador.
     *
     * @param id identificador único del préstamo a eliminar.
     */
    void deleteById(Long id);

    /**
     * Comprueba si existe un préstamo con el ID dado.
     *
     * @param id identificador único a verificar.
     * @return {@code true} si existe, {@code false} en caso contrario.
     */
    boolean existsById(Long id);
}