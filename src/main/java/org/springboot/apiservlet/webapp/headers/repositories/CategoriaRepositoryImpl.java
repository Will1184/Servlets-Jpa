package org.springboot.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import org.springboot.apiservlet.webapp.headers.configs.MysqlConn;
import org.springboot.apiservlet.webapp.headers.configs.Repository;
import org.springboot.apiservlet.webapp.headers.models.entity.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@RepositoryJdbc
@Repository
public class CategoriaRepositoryImpl implements CrudRepository<Categoria> {

    private Connection conn;

    @Inject
    public CategoriaRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws Exception {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from categorias")){
            while (rs.next()) {
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }

        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws Exception {
        Categoria categoria = null;
        try (PreparedStatement stmt = conn.prepareStatement("select * from categorias as c where c.id=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws Exception {

    }

    @Override
    public void eliminar(Long id) throws Exception {

    }

    private Categoria getCategoria(ResultSet rs) throws Exception {
        Categoria categoria = new Categoria();
        categoria.setNombre(rs.getString("nombre"));
        categoria.setId(rs.getLong("id"));
        return categoria;
    }
}
