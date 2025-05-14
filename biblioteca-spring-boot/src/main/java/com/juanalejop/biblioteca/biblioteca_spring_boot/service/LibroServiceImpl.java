package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.exception.LibroNoEncontradoException;
import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService{
    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    @Override
    public Libro buscarPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new LibroNoEncontradoException(isbn));
    }

    @Override
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro actualizar(Long id, Libro libro) {
        if (!libroRepository.existsById(id)) {
            throw new LibroNoEncontradoException(id);
        }
        libro.setId(id);
        return libroRepository.save(libro);
    }

    @Override
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
}