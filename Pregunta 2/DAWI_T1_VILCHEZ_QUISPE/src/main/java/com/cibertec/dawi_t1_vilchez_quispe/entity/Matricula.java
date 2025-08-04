package com.cibertec.dawi_t1_vilchez_quispe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matricula")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Alumno alumno;
    @ManyToOne
    private Curso curso;

    public Matricula(Alumno alumno, Curso curso) {
        this.alumno = alumno;
        this.curso = curso;
    }

}
