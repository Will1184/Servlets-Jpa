package org.springboot.apiservlet.webapp.headers.services;

import org.springboot.apiservlet.webapp.headers.models.entity.Categoria;
import org.springboot.apiservlet.webapp.headers.models.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);

    List<Categoria> listarCategoria();

    Optional<Categoria> porIdCategoria(Long id);
}
