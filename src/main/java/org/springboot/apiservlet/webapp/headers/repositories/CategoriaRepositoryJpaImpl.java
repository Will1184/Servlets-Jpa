package org.springboot.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.springboot.apiservlet.webapp.headers.configs.Repository;
import org.springboot.apiservlet.webapp.headers.models.entity.Categoria;

import java.util.List;

@Repository
public class CategoriaRepositoryJpaImpl implements CrudRepository<Categoria> {

    @Inject
    private EntityManager manager;
    @Override
    public List<Categoria> listar() throws Exception {
        return manager.createQuery("from Categoria ",Categoria.class).getResultList();
    }

    @Override
    public Categoria porId(Long id) throws Exception {
        return manager.find(Categoria.class,id);
    }

    @Override
    public void guardar(Categoria categoria) throws Exception {
        if (categoria.getId()!= null && categoria.getId()>0){
            manager.merge(categoria);
        }else {
            manager.persist(categoria);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        Categoria categoria= porId(id);
        manager.remove(categoria);
    }
}
