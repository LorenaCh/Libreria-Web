/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rocio.LibreriaWeb.repositorios;

import com.rocio.LibreriaWeb.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rocio
 */

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    
}
