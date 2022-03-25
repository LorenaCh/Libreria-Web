package com.rocio.LibreriaWeb.servicios;

import com.rocio.LibreriaWeb.entidades.Editorial;
import com.rocio.LibreriaWeb.repositorios.EditorialRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rocio
 */
@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales(){
        return editorialRepositorio.findAll();
    }
    @Transactional
    public void guardar (Editorial editorial){
        editorialRepositorio.save(editorial);
    }
    @Transactional
    public void eliminar (Editorial editorial){
        editorialRepositorio.delete(editorial);
    }
    @Transactional(readOnly = true)
    public Editorial buscarPorId(Editorial editorial){
        return editorialRepositorio.findById(editorial.getId()).orElse(null);
    }
    
}
