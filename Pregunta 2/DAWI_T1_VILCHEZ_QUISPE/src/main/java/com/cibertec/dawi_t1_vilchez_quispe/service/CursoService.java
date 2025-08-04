package com.cibertec.dawi_t1_vilchez_quispe.service;

import com.cibertec.dawi_t1_vilchez_quispe.entity.Curso;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
public interface CursoService {

    // Operaciones CRUD básicas
    Curso guardarCurso(Curso curso);
    Optional<Curso> obtenerCursoPorId(Long id);
    List<Curso> obtenerTodosLosCursos();
    void eliminarCurso(Long id);

    // Búsquedas específicas
    List<Curso> buscarCursosPorNombre(String nombre);
    Page<Curso> buscarCursosPorNombrePaginado(String nombre, int page, int size, String sortBy, String sortDir);

    // Paginación
    Page<Curso> obtenerCursosPaginados(int page, int size);
    Page<Curso> buscarCursosPorCreditos(int minCreditos, int page, int size);

    // Métodos de negocio
    boolean existeCursoPorNombre(String nombre);
    long contarCursosPorCreditos(int creditos);
}
