package org.springboot.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.springboot.apiservlet.webapp.headers.configs.Repository;
import org.springboot.apiservlet.webapp.headers.models.entity.Producto;

import java.util.List;
@RepositoryJpa
@Repository
public class ProductoRepositoryJpaImpl implements CrudRepository<Producto>{
    @Inject
    private EntityManager manager;
    @Override
    public List<Producto> listar() throws Exception {
        return manager.createQuery("SELECT p FROM Producto p LEFT " +
                "OUTER JOIN FETCH  p.categoria",Producto.class).getResultList();
    }

    @Override
    public Producto porId(Long id) throws Exception {
        return manager.find(Producto.class,id);
    }

    @Override
    public void guardar(Producto producto) throws Exception {
        if (producto.getId()!= null && producto.getId()>0){
            manager.merge(producto);
        }else {
            manager.persist(producto);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        Producto producto = porId(id);
        manager.remove(producto);
    }
}
