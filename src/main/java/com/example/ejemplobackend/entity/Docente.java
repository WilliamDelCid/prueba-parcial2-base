package com.example.ejemplobackend.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "docente")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_docente")
    private Long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    private String numeroUnico;

    @Column(name = "especialidad", length = 50)
    private String especialidad;

    @Column(name = "institucion", length = 50)
    private String institucion;

    @Column(name = "correo", length = 50)
    private String correo;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @OneToOne(mappedBy = "docente",cascade = CascadeType.ALL)
    @JsonBackReference
    private Usuario usuario;

    public Docente(String nombre, String numeroUnico, String especialidad, String institucion, String correo, String telefono) {
        this.nombre = nombre;
        this.numeroUnico = numeroUnico;
        this.especialidad = especialidad;
        this.institucion = institucion;
        this.correo = correo;
        this.telefono = telefono;
    }
}
