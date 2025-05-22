package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad {@link Usuario}.
 * <p>
 * Define operaciones CRUD básicas para gestionar usuarios.
 */
public interface UsuarioRepository {

    /**
     * Obtiene todos los usuarios almacenados.
     *
     * @return lista de todos los usuarios, vacía si no hay ninguno.
     */
    List<Usuario> findAll();

    /**
     * Busca un usuario por su identificador.
     *
     * @param id identificador único del usuario.
     * @return {@link Optional} con el usuario si existe, o vacío en caso contrario.
     */
    Optional<Usuario> findById(Long id);

    /**
     * Guarda un usuario en el repositorio. Si el usuario no tiene ID, se asigna uno nuevo.
     * Si ya existe, reemplaza la entrada previa.
     *
     * @param usuario instancia de {@link Usuario} a guardar.
     * @return el usuario guardado, con ID asignado.
     */
    Usuario save(Usuario usuario);

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id identificador único del usuario a eliminar.
     */
    void deleteById(Long id);

    /**
     * Comprueba si existe un usuario con el ID dado.
     *
     * @param id identificador único a verificar.
     * @return {@code true} si existe, {@code false} en caso contrario.
     */
    boolean existsById(Long id);
}