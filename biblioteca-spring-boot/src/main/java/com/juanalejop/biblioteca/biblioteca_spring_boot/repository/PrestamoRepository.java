package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Prestamo;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {
    List<Prestamo> findAll();
    Optional<Prestamo> findById(Long id);
    Prestamo save(Prestamo prestamo);
    void deleteById(Long id);
}