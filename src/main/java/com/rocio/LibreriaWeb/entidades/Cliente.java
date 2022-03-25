package com.rocio.LibreriaWeb.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
/**
 *
 * @author Rocio
 */
@Data
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable =false)
    private Long dni;
    @Column(nullable =false)
    private String apellido;
    @Column(nullable =false)
    private String nombre;
    private String telefono;

}
