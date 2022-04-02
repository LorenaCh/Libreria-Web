
package com.rocio.LibreriaWeb.controladores;

import com.rocio.LibreriaWeb.entidades.Autor;
import com.rocio.LibreriaWeb.servicios.AutorServicio;
import java.util.List;
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
@RequestMapping("/autor") //localhost:8080/autor
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorService;
    
    /*****AUTORES*****/
    @GetMapping("") //localhost:8080/autor
    public String autores(Model model){
        List<Autor> autores = autorService.listarAutores();
        model.addAttribute("autores", autores);
        return "autores";
    }
    
    @GetMapping("/agregar") //localhost:8080/autor/agregar
    public String crearAutor(Autor autor, Model model){
        autor.setAlta(true);
        model.addAttribute("autor",autor);
        return "modificarAutor";
    }
    
    @PostMapping("/guardar")
    public String guardarAutor(@Valid Autor autor, Errors errores ){
        
        if(autor.getNombre() == null)
            autor.setAlta(true);
        log.info(autor.toString());
        if(errores.hasErrors()){
            return "modificarAutor";
        }
        System.out.println(autor);
        autorService.guardar(autor);
        return "redirect:";
    }
    
    @GetMapping("/editar") //localhost:8080/autor/editar15
    public String editarAutor(Autor autor, Model model){
        autor = autorService.buscarPorId(autor);
        model.addAttribute("autor",autor);
        return "modificarAutor";
    }
    /*
    @GetMapping("/editar/{id}")
    public String editarAutor2(Autor autor,Model model){
        autor = autorService.buscarPorId(autor);
        model.addAttribute("autorEdit", autor);
        return "editarAutor";
    }*/
    
    @GetMapping("/eliminar") //localhost:8080/autor/eliminar
    public String eliminarAutor(Autor autor){
        autorService.eliminar(autor);
        return "redirect: ";
    }
}
