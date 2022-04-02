package com.rocio.LibreriaWeb.controladores;

import com.rocio.LibreriaWeb.entidades.Autor;
import com.rocio.LibreriaWeb.entidades.Editorial;
import com.rocio.LibreriaWeb.entidades.Libro;
import com.rocio.LibreriaWeb.servicios.AutorServicio;
import com.rocio.LibreriaWeb.servicios.EditorialServicio;
import com.rocio.LibreriaWeb.servicios.LibroServicio;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Rocio
 */
@Validated
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

    /**
     * ***LIBROS****
     */
    @GetMapping("") //localhost:8080/libro
    public String libros(Model model) {
        List<Libro> libros = libroServicio.listarLibros();
        model.addAttribute("libros", libros);
        return "libros";
    }

    @GetMapping("/agregar")
    public String crearLibro(Libro libro, Model model) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        model.addAttribute("editoriales", editoriales);
        model.addAttribute("autores", autores);
        //libro.setAlta(true);
        //model.addAttribute("libro", libro);
        return "crearLibro";
    }

    @PostMapping("/guardar")
    public String guardarLibro(/*@Valid @RequestParam String titulo,
                                BindingResult result,
                               @RequestParam Integer anio,
                               @RequestParam Integer ejemplares,
                               @RequestParam Integer ejemplaresPrestados,
                               @RequestParam Integer ejemplaresRestantes,
                               @RequestParam Integer autorId,
                               @RequestParam Integer editorialId*/@Valid Libro libro,Errors errores) {
        
        /*Autor autor = new Autor();
        autor.setId(autorId);
        autor = autorServicio.buscarPorId(autor);
        Editorial editorial = new Editorial();
        editorial.setId(editorialId);
        editorial = editorialServicio.buscarPorId(editorial);
        Libro l = libroServicio.crearLibro(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
        libroServicio.guardar(l);*/
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(libro.getEjemplares());
        if(libro.getAutor().getId()==null){
            autorServicio.guardar(libro.getAutor());
            Autor a = autorServicio.buscarPorNombre(libro.getAutor());
            libro.setAutor(a);
        }
        
        if(libro.getEditorial().getId()==null){
            editorialServicio.guardar(libro.getEditorial());
            Editorial e = editorialServicio.buscarPorNombre(libro.getEditorial());
            libro.setEditorial(e);
        }
        System.out.println(libro);
        libroServicio.guardar(libro); 
        return "redirect:";
    }

    @GetMapping("/darBaja") //localhost:8080/libro/darBaja8
    public String darBaja(Libro libro) {
        libro = libroServicio.buscarPorISBN(libro);
        libroServicio.darBaja(libro);
        libroServicio.guardar(libro);
        return "redirect:";
    }

    @GetMapping("/darAlta")
    public String darAlta(Libro libro) {
        libro = libroServicio.buscarPorISBN(libro);
        libroServicio.darAlta(libro);
        libroServicio.guardar(libro);
        return "redirect:";
    }

    @GetMapping("/aumentarLibro")
    public String aumentarEjemplares(Libro libro) {
        libro = libroServicio.buscarPorISBN(libro);
        libroServicio.aumentarEjemplar(libro);
        libroServicio.aumentarEjemplarRestante(libro);
        libroServicio.guardar(libro);
        return "redirect:";
    }

    @GetMapping("/disminuirLibro")
    public String disminuirEjemplares(Libro libro, Model model) {
        libro = libroServicio.buscarPorISBN(libro);
        libroServicio.disminuirEjemplar(libro);
        libroServicio.disminuirEjemplarRestante(libro);
        libroServicio.guardar(libro);
        var libros = libroServicio.listarLibros();
        model.addAttribute("libros", libros);
        return "redirect:";
    }

}
