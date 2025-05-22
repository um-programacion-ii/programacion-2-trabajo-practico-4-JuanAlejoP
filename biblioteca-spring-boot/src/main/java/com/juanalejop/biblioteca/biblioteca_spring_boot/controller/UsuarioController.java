package com.juanalejop.biblioteca.biblioteca_spring_boot.controller;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Usuario;
import com.juanalejop.biblioteca.biblioteca_spring_boot.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones CRUD de usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    /**
     * Constructor que inyecta el servicio de usuarios.
     *
     * @param usuarioService instancia de UsuarioService
     */
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Obtiene la lista de todos los usuarios.
     *
     * @return lista de usuarios
     */
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id identificador del usuario
     * @return usuario encontrado
     */
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuario objeto Usuario con los datos para crear
     * @return usuario creado
     */
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id      identificador del usuario a actualizar
     * @param usuario datos actualizados del usuario
     * @return usuario actualizado
     */
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizar(id, usuario);
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id identificador del usuario a eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}