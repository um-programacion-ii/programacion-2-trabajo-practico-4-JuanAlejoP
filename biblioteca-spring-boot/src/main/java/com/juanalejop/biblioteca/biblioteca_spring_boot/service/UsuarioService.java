package com.juanalejop.biblioteca.biblioteca_spring_boot.service;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Usuario;

import java.util.List;

/**
 * Interfaz de servicio para operaciones de gestión de usuarios.
 */
public interface UsuarioService {

    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return Lista de {@link Usuario} existentes.
     */
    List<Usuario> obtenerTodos();

    /**
     * Busca un usuario por su ID.
     *
     * @param id Identificador del usuario.
     * @return Instancia de {@link Usuario} encontrada.
     * @throws com.juanalejop.biblioteca.biblioteca_spring_boot.exception.UsuarioNoEncontradoException Si no existe un usuario con el ID proporcionado.
     */
    Usuario buscarPorId(Long id);

    /**
     * Guarda un nuevo usuario en el sistema.
     *
     * @param usuario Objeto {@link Usuario} a persistir.
     * @return La instancia de {@link Usuario} guardada.
     */
    Usuario guardar(Usuario usuario);

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id      Identificador del usuario a actualizar.
     * @param usuario Objeto {@link Usuario} con la información modificada.
     * @return La instancia de {@link Usuario} actualizada.
     * @throws com.juanalejop.biblioteca.biblioteca_spring_boot.exception.UsuarioNoEncontradoException Si no existe un usuario con el ID proporcionado.
     */
    Usuario actualizar(Long id, Usuario usuario);

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id Identificador del usuario a eliminar.
     */
    void eliminar(Long id);
}