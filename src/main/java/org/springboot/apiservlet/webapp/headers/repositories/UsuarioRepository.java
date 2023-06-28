package org.springboot.apiservlet.webapp.headers.repositories;

import org.springboot.apiservlet.webapp.headers.models.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario> {
    Usuario porUsername(String username) throws Exception;
}
