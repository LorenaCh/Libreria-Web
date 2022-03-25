package com.rocio.LibreriaWeb.controladores;

import com.rocio.LibreriaWeb.entidades.Editorial;
import com.rocio.LibreriaWeb.servicios.EditorialServicio;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Rocio
 */
@Controller
@Slf4j
@RequestMapping("/editorial")
public class EditorialControlador {
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    /*****EDITORIALES*****/
    @GetMapping("")
    public String editoriales(Model model){
        var editoriales = editorialServicio.listarEditoriales();
        model.addAttribute("editoriales", editoriales);
        return "editoriales";
    }
    
    @GetMapping("/agregar")
    public String crearEditorial(Editorial editorial, Model model){
        editorial.setAlta(true);
        model.addAttribute("editorial",editorial);
        return "modificarEditorial";
    }
    
    @PostMapping("/guardar")
    public String guardarEditorial(@Valid Editorial editorial, Errors errores ){
        
        if(editorial.getNombre() == null)
            editorial.setAlta(true);
        log.info(editorial.toString());
        if(errores.hasErrors()){
            return "modificarEditorial";
        }
        editorialServicio.guardar(editorial);
        return "redirect:";
    }
    
    @GetMapping("/editar")
    public String editarEditorial(Editorial editorial, Model model){
        editorial = editorialServicio.buscarPorId(editorial);
        model.addAttribute("editorial",editorial);
        return "modificarEditorial";
    }
    
    @GetMapping("/eliminar")
    public String eliminarEditorial(Editorial editorial){
        editorialServicio.eliminar(editorial);
        return "redirect:";
    }
}
