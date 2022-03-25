package com.rocio.LibreriaWeb.controladores;

import com.rocio.LibreriaWeb.entidades.Autor;
import com.rocio.LibreriaWeb.entidades.Editorial;
import com.rocio.LibreriaWeb.servicios.AutorServicio;
import com.rocio.LibreriaWeb.servicios.EditorialServicio;
import com.rocio.LibreriaWeb.servicios.LibroServicio;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class ControladorInicio {
    
    
    
    @GetMapping("/")
    public String index(Model model){
        log.info("Ejecutando el controlador spring MVC");
        //var autores = autorService.listarAutores();
        //model.addAttribute("autores", autores);
        return "index";
    }
    
}
