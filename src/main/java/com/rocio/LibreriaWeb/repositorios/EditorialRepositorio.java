package com.rocio.LibreriaWeb.repositorios;

import com.rocio.LibreriaWeb.entidades.Autor;
import com.rocio.LibreriaWeb.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rocio
 */
@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, Integer>{
    @Query("SELECT a FROM Editorial a WHERE a.nombre = :nombre")
    public Autor buscarPorNombre(@Param("nombre") String nombre);
}
