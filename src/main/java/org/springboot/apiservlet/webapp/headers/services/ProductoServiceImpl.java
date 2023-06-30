package org.springboot.apiservlet.webapp.headers.services;

import jakarta.inject.Inject;
import org.springboot.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import org.springboot.apiservlet.webapp.headers.configs.Service;
import org.springboot.apiservlet.webapp.headers.interceptors.TransactionalJpa;
import org.springboot.apiservlet.webapp.headers.models.entity.Categoria;
import org.springboot.apiservlet.webapp.headers.models.entity.Producto;
import org.springboot.apiservlet.webapp.headers.repositories.CrudRepository;
import org.springboot.apiservlet.webapp.headers.repositories.ProductoRepositoryJpaImpl;
import org.springboot.apiservlet.webapp.headers.repositories.RepositoryJpa;
import org.springboot.apiservlet.webapp.headers.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@ProductoServicePrincipal
@TransactionalJpa
public class ProductoServiceImpl implements ProductoService{
    @Inject
    @RepositoryJpa
    private CrudRepository<Producto> crudRepositoryJdbc;

    private ProductoRepositoryJpaImpl productoRepositoryJpa;
    @Inject
    @RepositoryJpa
    private CrudRepository<Categoria> crudRepositoryCategoriaJdbc;

    @Inject

    public ProductoServiceImpl(@RepositoryJpa ProductoRepositoryJpaImpl productoRepositoryJpa) {
        this.productoRepositoryJpa= productoRepositoryJpa;
    }

    @Override
    public List<Producto> listar() {
        try {
            return crudRepositoryJdbc.listar();
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(crudRepositoryJdbc.porId(id));
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            crudRepositoryJdbc.guardar(producto);
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            crudRepositoryJdbc.eliminar(id);
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return crudRepositoryCategoriaJdbc.listar();
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(crudRepositoryCategoriaJdbc.porId(id));
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
