package com.juanalejop.biblioteca.biblioteca_spring_boot.controller;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Libro;
import com.juanalejop.biblioteca.biblioteca_spring_boot.service.LibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones CRUD de libros.
 */
@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService libroService;

    /**
     * Constructor que inyecta el servicio de libros.
     *
     * @param libroService instancia de LibroService
     */
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    /**
     * Obtiene la lista de todos los libros.
     *
     * @return lista de libros
     */
    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroService.obtenerTodos();
    }

    /**
     * Busca un libro por su ID.
     *
     * @param id identificador del libro
     * @return libro encontrado
     */
    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {
        return libroService.buscarPorIsbn(id.toString());
    }

    /**
     * Crea un nuevo libro.
     *
     * @param libro objeto Libro con los datos para crear
     * @return libro creado
     */
    @PostMapping
    public Libro crear(@RequestBody Libro libro) {
        return libroService.guardar(libro);
    }

    /**
     * Actualiza un libro existente.
     *
     * @param id    identificador del libro a actualizar
     * @param libro datos actualizados del libro
     * @return libro actualizado
     */
    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizar(id, libro);
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param id identificador del libro a eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
    }
}