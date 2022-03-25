package com.rocio.LibreriaWeb.repositorios;

import com.rocio.LibreriaWeb.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Integer>{
    @Query("SELECT a FROM Autor a WHERE a.nombre = :cadena")
    public Autor buscarPorNombre(@Param("cadena") String cadena);
    
}
