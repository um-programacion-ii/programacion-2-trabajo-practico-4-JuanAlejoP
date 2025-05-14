package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;

import java.util.List;

public interface LibroService {
    List<Libro> obtenerTodos();
    Libro buscarPorIsbn(String isbn);
    Libro guardar(Libro libro);
    Libro actualizar(Long id, Libro libro);
    void eliminar(Long id);
}