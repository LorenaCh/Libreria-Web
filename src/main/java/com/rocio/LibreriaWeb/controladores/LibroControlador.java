package com.rocio.LibreriaWeb.controladores;

import com.rocio.LibreriaWeb.entidades.Autor;
import com.rocio.LibreriaWeb.entidades.Editorial;
import com.rocio.LibreriaWeb.entidades.Libro;
import com.rocio.LibreriaWeb.servicios.AutorServicio;
import com.rocio.LibreriaWeb.servicios.EditorialServicio;
import com.rocio.LibreriaWeb.servicios.LibroServicio;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author Rocio
 */
@Controller
@Slf4j
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private LibroServicio libroServicio;
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    /*****LIBROS*****/
    @GetMapping("") //localhost:8080/libro
    public String libros(Model model){
        var libros = libroServicio.listarLibros();
        model.addAttribute("libros",libros);
        return "libros";
    }
    
    @GetMapping("/agregar")
    public String crearLibro(Libro libro, Model model){
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        model.addAttribute("editoriales", editoriales);
        model.addAttribute("autores",autores);
        //libro.setAlta(true);
        //model.addAttribute("libro", libro);
        return "crearLibro";
    }
    
    @PostMapping("/guardar")
    public String guardarLibro(@RequestParam String titulo,@RequestParam Integer anio,@RequestParam Integer ejemplares,@RequestParam Integer ejemplaresPrestados,@RequestParam Integer ejemplaresRestantes,@RequestParam Integer autorId,@RequestParam Integer editorialId){
        Autor autor=new Autor();
        autor.setId(autorId);
        autor = autorServicio.buscarPorId(autor);
        Editorial editorial = new Editorial();
        editorial.setId(editorialId);
        editorial = editorialServicio.buscarPorId(editorial);
        Libro l = libroServicio.crearLibro(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
        libroServicio.guardar(l);
        return "redirect:";
    }
    
    @GetMapping("/darBaja") //localhost:8080/libro/darBaja8
    public String darBaja(Libro libro){
        libro = libroServicio.buscarPorISBN(libro);
        libroServicio.darBaja(libro);
        libroServicio.guardar(libro);
        return "redirect:";
    }
    
    @GetMapping("/darAlta")
    public String darAlta(Libro libro){
        libro = libroServicio.buscarPorISBN(libro);
        libroServicio.darAlta(libro);
        libroServicio.guardar(libro);
        return "redirect:";
    }
    
    @GetMapping("/aumentarLibro")
    public String aumentarEjemplares(Libro libro){
        libro = libroServicio.buscarPorISBN(libro);
        libroServicio.aumentarEjemplar(libro);
        libroServicio.aumentarEjemplarRestante(libro);
        libroServicio.guardar(libro);
        return "redirect:";
    }
    
    @GetMapping("/disminuirLibro")
    public String disminuirEjemplares(Libro libro,Model model){
        String error = null;
        libro = libroServicio.buscarPorISBN(libro);
        if(libro.getEjemplaresRestantes()<=0 && libro.getEjemplares()<=0){
            if(libro.getEjemplares()<=0){
                error = "Error! No hay ejemplares disponibles";
                model.addAttribute("error", error);
            }else{
                error = "Error! No hay ejemplares restantes disponibles";
                model.addAttribute("error", error);
                //return "/errorSinEjemplaresRestantes";
            }
            model.addAttribute("libro", libro);
        }else{
            libroServicio.disminuirEjemplar(libro);
            libroServicio.disminuirEjemplarRestante(libro);
            libroServicio.guardar(libro);
        }
        var libros = libroServicio.listarLibros();
        model.addAttribute("libros",libros);
        return "libros";
    }
    
}
