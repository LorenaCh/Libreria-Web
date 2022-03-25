/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rocio.LibreriaWeb.servicios;

import com.rocio.LibreriaWeb.entidades.Autor;
import com.rocio.LibreriaWeb.entidades.Editorial;
import com.rocio.LibreriaWeb.entidades.Libro;
import com.rocio.LibreriaWeb.repositorios.LibroRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rocio
 */

@Service
public class LibroServicio {
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @Transactional(readOnly = true)
    public List<Libro> listarLibros(){
        return libroRepositorio.findAll();
    }
    @Transactional
    public void guardar (Libro libro){
        libroRepositorio.save(libro);
    }
    @Transactional
    public void eliminar (Libro libro){
        libroRepositorio.delete(libro);
    }
    @Transactional(readOnly = true)
    public Libro buscarPorISBN(Libro libro){
        return libroRepositorio.findById(libro.getIsbn()).orElse(null);
    }
    public Libro crearLibro(String titulo,Integer anio,Integer ejemplares,Integer ejemplaresPrestados,Integer ejemplaresRestantes, Autor autor, Editorial editorial){
        Libro l = new Libro();
        l.setTitulo(titulo);
        l.setAnio(anio);
        l.setEjemplares(ejemplares);
        l.setEjemplaresPrestados(ejemplaresPrestados);
        l.setEjemplaresRestantes(ejemplaresRestantes);
        l.setAlta(true);
        l.setAutor(autor);
        l.setEditorial(editorial);
        return l;
    }
    
    public void darBaja(Libro libro){
        libro.setAlta(Boolean.FALSE);
    }
    
    public void darAlta(Libro libro){
        libro.setAlta(Boolean.TRUE);
    }
    
    public void aumentarEjemplar(Libro libro){
        libro.setEjemplares(libro.getEjemplares()+1);
    }
    
    public void disminuirEjemplar(Libro libro){
        libro.setEjemplares(libro.getEjemplares()-1);
    }
    
    public void aumentarEjemplarRestante(Libro libro){
        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()+1);
    }
    
    public void disminuirEjemplarRestante(Libro libro){
        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-1);
    }
}
