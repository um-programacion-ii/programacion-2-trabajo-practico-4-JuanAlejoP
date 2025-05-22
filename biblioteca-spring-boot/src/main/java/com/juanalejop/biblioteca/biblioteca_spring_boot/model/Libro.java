package com.juanalejop.biblioteca.biblioteca_spring_boot.model;

/**
 * Representa un libro disponible en la biblioteca.
 * Incluye información bibliográfica y su estado de disponibilidad.
 */
public class Libro {
    /** Identificador único del libro */
    private Long id;
    /** Código ISBN del libro */
    private String isbn;
    /** Título del libro */
    private String titulo;
    /** Nombre del autor o autores del libro. */
    private String autor;
    /** Estado actual del libro (disponible, prestado, reservado, etc.). */
    private EstadoLibro estado;

    /**
     * Constructor vacío requerido por frameworks y bibliotecas de serialización.
     */
    public Libro() {}

    /**
     * Crea una nueva instancia de Libro con todos sus atributos.
     *
     * @param id     Identificador único del libro.
     * @param isbn   Código ISBN del libro.
     * @param titulo Título del libro.
     * @param autor  Autor del libro.
     * @param estado Estado actual del libro.
     */
    public Libro(Long id, String isbn, String titulo, String autor, EstadoLibro estado) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = estado;
    }

    /**
     * Obtiene el identificador único del libro.
     *
     * @return ID del libro.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna un nuevo identificador al libro.
     *
     * @param id Nuevo ID del libro.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el código ISBN asociado al libro.
     *
     * @return ISBN del libro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Asigna un código ISBN al libro.
     *
     * @param isbn Nuevo código ISBN.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return Nombre del autor.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Asigna el nombre del autor del libro.
     *
     * @param autor Nombre del autor.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return Título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Asigna un título al libro.
     *
     * @param titulo Nuevo título del libro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el estado de disponibilidad del libro.
     *
     * @return Estado actual del libro.
     */
    public EstadoLibro getEstado() {
        return estado;
    }

    /**
     * Asigna un estado de disponibilidad al libro.
     *
     * @param estado Nuevo estado del libro.
     */
    public void setEstado(EstadoLibro estado) {
        this.estado = estado;
    }

    /**
     * Genera una representación en cadena de texto con todos los atributos del libro.
     *
     * @return Cadena con ID, isbn, título, autor y estado.
     */
    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", estado=" + estado +
                '}';
    }
}