package com.cibertec.dawi_t1_vilchez_quispe.service;


import com.cibertec.dawi_t1_vilchez_quispe.entity.Matricula;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface MatriculaService {

    // Operaciones CRUD básicas
    Matricula guardarMatricula(Matricula matricula);
    Optional<Matricula> obtenerMatriculaPorId(Long id);
    List<Matricula> obtenerTodasLasMatriculas();
    void eliminarMatricula(Long id);

    // Búsquedas específicas
    List<Matricula> buscarMatriculasPorEmail(String email);
    Page<Matricula> buscarMatriculasPorEmailPaginado(String email, int page, int size, String sortBy, String sortDir);

    // Métodos de negocio
    Matricula matricularAlumno(Long alumnoId, Long cursoId);
    boolean estaMatriculado(Long alumnoId, Long cursoId);
    long contarMatriculasPorCurso(Long cursoId);
    List<Object[]> obtenerEstadisticasMatriculas();

    Page<Matricula> obtenerTodasLasMatriculasPaginadas( int page, int size, String sortBy, String sortDir);
}
