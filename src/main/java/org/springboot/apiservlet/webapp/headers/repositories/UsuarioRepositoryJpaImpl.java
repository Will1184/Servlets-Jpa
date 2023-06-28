package org.springboot.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.springboot.apiservlet.webapp.headers.configs.Repository;
import org.springboot.apiservlet.webapp.headers.models.entity.Usuario;

import java.util.List;
@RepositoryJpa
@Repository
public class UsuarioRepositoryJpaImpl implements UsuarioRepository{

    @Inject
    private EntityManager manager;
    @Override
    public List<Usuario> listar() throws Exception {
        return manager.createQuery("FROM Usuario ",Usuario.class).getResultList();
    }

    @Override
    public Usuario porId(Long id) throws Exception {
        return manager.find(Usuario.class,id);
    }

    @Override
    public void guardar(Usuario usuario) throws Exception {
        if (usuario.getId()!=null && usuario.getId()>0) {
            manager.merge(usuario);
        }else {
            manager.persist(usuario);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        manager.remove(porId(id));

    }

    @Override
    public Usuario porUsername(String username) throws Exception {
        return manager.createQuery("SELECT u FROM Usuario u WHERE" +
                        " u.username=:username",Usuario.class)
                .setParameter("username",username)
                .getSingleResult();
    }
}
