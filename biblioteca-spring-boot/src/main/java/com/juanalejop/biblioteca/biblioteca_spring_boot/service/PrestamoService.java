package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Prestamo;

import java.util.List;

public interface PrestamoService {
    List<Prestamo> obtenerTodos();
    Prestamo buscarPorId(Long id);
    Prestamo guardar(Prestamo prestamo);
    Prestamo actualizar(Long id, Prestamo prestamo);
    void eliminar(Long id);
}