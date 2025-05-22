package com.juanalejop.biblioteca.biblioteca_spring_boot.model;

import java.time.LocalDate;

/**
 * Representa el registro de un préstamo de un libro a un usuario.
 */
public class Prestamo {
    /** Identificador único del préstamo */
    private Long id;
    /** Libro asociado al préstamo */
    private Libro libro;
    /** Usuario que solicita el préstamo. */
    private Usuario usuario;
    /** Fecha de inicio del préstamo */
    private LocalDate fechaPrestamo;
    /** Fecha de devolución prevista */
    private LocalDate fechaDevolucion;

    /**
     * Constructor vacío para frameworks y serialización.
     */
    public Prestamo() {}

    /**
     * Crea un nuevo registro de préstamo con todos sus atributos.
     *
     * @param id              Identificador del préstamo.
     * @param libro           Libro asociado al préstamo.
     * @param usuario         Usuario que realiza el préstamo.
     * @param fechaPrestamo   Fecha de inicio del préstamo.
     * @param fechaDevolucion Fecha de devolución prevista.
     */
    public Prestamo(Long id, Libro libro, Usuario usuario, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Obtiene el ID del préstamo.
     *
     * @return ID del préstamo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna un nuevo ID al préstamo.
     *
     * @param id Nuevo identificador.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el libro prestado.
     *
     * @return Instancia de Libro.
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Asigna el libro asociado al préstamo.
     *
     * @param libro Libro a prestar.
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Obtiene el usuario que realizó el préstamo.
     *
     * @return Instancia de Usuario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Asigna el usuario que realizará el préstamo.
     *
     * @param usuario Usuario solicitante.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la fecha de inicio del préstamo.
     *
     * @return Fecha de préstamo.
     */
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Asigna la fecha de inicio del préstamo.
     *
     * @param fechaPrestamo Fecha en que inicia el préstamo.
     */
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * Obtiene la fecha prevista de devolución.
     *
     * @return Fecha de devolución estimada.
     */
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Asigna la fecha prevista de devolución del préstamo.
     *
     * @param fechaDevolucion Fecha estimada para devolver el libro.
     */
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Representación en texto con todos los detalles del préstamo.
     *
     * @return Cadena con ID, libro, usuario, fecha de préstamo y devolución.
     */
    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", libro=" + libro +
                ", usuario=" + usuario +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }
}