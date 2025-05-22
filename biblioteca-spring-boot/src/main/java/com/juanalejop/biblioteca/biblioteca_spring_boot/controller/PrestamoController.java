package com.juanalejop.biblioteca.biblioteca_spring_boot.controller;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Prestamo;
import com.juanalejop.biblioteca.biblioteca_spring_boot.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones CRUD de préstamos de libros.
 */
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
    private final PrestamoService prestamoService;

    /**
     * Constructor que inyecta el servicio de préstamos.
     *
     * @param prestamoService instancia de PrestamoService
     */
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    /**
     * Obtiene la lista de todos los préstamos.
     *
     * @return lista de préstamos
     */
    @GetMapping
    public List<Prestamo> obtenerTodos() {
        return prestamoService.obtenerTodos();
    }

    /**
     * Busca un préstamo por su ID.
     *
     * @param id identificador del préstamo
     * @return préstamo encontrado
     */
    @GetMapping("/{id}")
    public Prestamo obtenerPorId(@PathVariable Long id) {
        return prestamoService.buscarPorId(id);
    }

    /**
     * Crea un nuevo préstamo.
     *
     * @param prestamo objeto Prestamo con los datos para crear
     * @return préstamo creado
     */
    @PostMapping
    public Prestamo crear(@RequestBody Prestamo prestamo) {
        return prestamoService.guardar(prestamo);
    }

    /**
     * Actualiza un préstamo existente.
     *
     * @param id       identificador del préstamo a actualizar
     * @param prestamo datos actualizados del préstamo
     * @return préstamo actualizado
     */
    @PutMapping("/{id}")
    public Prestamo actualizar(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        return prestamoService.actualizar(id, prestamo);
    }

    /**
     * Elimina un préstamo por su ID.
     *
     * @param id identificador del préstamo a eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        prestamoService.eliminar(id);
    }
}