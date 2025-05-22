package com.juanalejop.biblioteca.biblioteca_spring_boot.model;

/**
 * Representa un usuario registrado en el sistema de la biblioteca.
 */
public class Usuario {
    /** Identificador único del usuario */
    private Long id;
    /** Nombre del usuario */
    private String nombre;
    /** Email del usuario */
    private String email;
    /** Indicador de si está activo */
    private Boolean activo;

    /**
     * Constructor vacío para frameworks y serialización.
     */
    public Usuario() {}

    /**
     * Crea un nuevo usuario con todos sus atributos.
     *
     * @param id     Identificador del usuario.
     * @param nombre Nombre completo del usuario.
     * @param email  Correo electrónico.
     * @param activo Indicador de estado activo.
     */
    public Usuario(Long id, String nombre, String email, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.activo = activo;
    }

    /**
     * Obtiene el identificador del usuario.
     *
     * @return ID del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna un nuevo identificador al usuario.
     *
     * @param id Nuevo ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * Asigna el nombre completo del usuario.
     *
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return Email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Asigna el correo electrónico del usuario.
     *
     * @param email Email del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Indica si el usuario está activo para realizar operaciones.
     *
     * @return true si activo, false en caso contrario.
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * Establece el estado de actividad del usuario.
     *
     * @param activo Booleano que representa si el usuario está activo.
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * Representación en texto con los atributos del usuario.
     *
     * @return Cadena con ID, nombre, email y estado.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", activo=" + activo +
                '}';
    }
}