package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Prestamo;

import java.util.List;

/**
 * Interfaz de servicio para operaciones de gestión de préstamos.
 */
public interface PrestamoService {

    /**
     * Obtiene todos los préstamos registrados.
     *
     * @return Lista de {@link Prestamo} existentes.
     */
    List<Prestamo> obtenerTodos();

    /**
     * Busca un préstamo por su ID.
     *
     * @param id Identificador del préstamo.
     * @return Instancia de {@link Prestamo} encontrada.
     * @throws com.juanalejop.biblioteca.biblioteca_spring_boot.exception.PrestamoNoEncontradoException Si no existe un préstamo con el ID proporcionado.
     */
    Prestamo buscarPorId(Long id);

    /**
     * Registra un nuevo préstamo.
     *
     * @param prestamo Objeto {@link Prestamo} a persistir.
     * @return La instancia de {@link Prestamo} guardada.
     */
    Prestamo guardar(Prestamo prestamo);

    /**
     * Actualiza los datos de un préstamo existente.
     *
     * @param id       Identificador del préstamo a actualizar.
     * @param prestamo Objeto {@link Prestamo} con información modificada.
     * @return La instancia de {@link Prestamo} actualizada.
     * @throws com.juanalejop.biblioteca.biblioteca_spring_boot.exception.PrestamoNoEncontradoException Si no existe un préstamo con el ID proporcionado.
     */
    Prestamo actualizar(Long id, Prestamo prestamo);

    /**
     * Elimina un préstamo por su identificador.
     *
     * @param id Identificador del préstamo a eliminar.
     */
    void eliminar(Long id);
}