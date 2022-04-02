package com.rocio.LibreriaWeb.servicios;

import com.rocio.LibreriaWeb.entidades.Autor;
import com.rocio.LibreriaWeb.repositorios.AutorRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Transactional(readOnly = true)
    public List<Autor> listarAutores(){
        return autorRepositorio.findAll();
    }
    @Transactional
    public void guardar (Autor autor){
        autorRepositorio.save(autor);
    }
    @Transactional
    public void eliminar (Autor autor){
        autorRepositorio.delete(autor);
    }
    @Transactional(readOnly = true)
    public Autor buscarPorId(Autor autor){
        return autorRepositorio.findById(autor.getId()).orElse(null);
    }
    
    @Transactional(readOnly=true)
    public Autor buscarPorNombre(Autor autor){
        return autorRepositorio.buscarPorNombre(autor.getNombre());
    }
}
