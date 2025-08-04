package com.cibertec.dawi_t1_vilchez_quispe.service;

import com.cibertec.dawi_t1_vilchez_quispe.entity.Alumno;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    // Operaciones CRUD básicas
    Alumno guardarAlumno(Alumno alumno);
    Optional<Alumno> obtenerAlumnoPorId(Long id);
    List<Alumno> obtenerTodosLosAlumnos();
    void eliminarAlumno(Long id);

    // Búsquedas específicas
    Optional<Alumno> buscarAlumnoPorEmail(String email);
    Page<Alumno> obtenerAlumnosPaginados(int page, int size, String sortBy, String sortDir);

    // Métodos de negocio
    boolean existeAlumnoPorEmail(String email);
}
