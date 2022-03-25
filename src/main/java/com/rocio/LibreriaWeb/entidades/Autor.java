/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rocio.LibreriaWeb.entidades;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
/**
 *
 * @author Rocio
 */
@Data // Te crea los constructores y los getters y setters
@Entity // Es tabla de la base de datos
@Table(name = "autor") 
public class Autor implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotEmpty //NotEmpty valida que no sea vacio. Si se usa NotNUll solo valida si no es null pero puede ser vacio
    private String nombre;
    private boolean alta;
}
