package org.springboot.apiservlet.webapp.headers.services;

import org.springboot.apiservlet.webapp.headers.models.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
}
