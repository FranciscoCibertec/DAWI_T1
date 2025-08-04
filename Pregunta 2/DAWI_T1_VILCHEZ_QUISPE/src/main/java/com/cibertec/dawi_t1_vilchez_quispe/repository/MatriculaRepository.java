package com.cibertec.dawi_t1_vilchez_quispe.repository;

import com.cibertec.dawi_t1_vilchez_quispe.entity.Matricula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    // Consulta JPQL para listar todas matrículas
    @Query("SELECT m FROM Matricula m JOIN m.alumno a ")
    List<Matricula> findAll();

    // Consulta JPQL para listar todas matrículas paginacion
    @Query("SELECT m FROM Matricula m JOIN m.alumno a ")
    Page<Matricula> findAll(Pageable pageable);

    // Consulta JPQL para listar matrículas por email del alumno
    @Query("SELECT m FROM Matricula m JOIN m.alumno a WHERE a.email = :email")
    List<Matricula> findByAlumnoEmail(@Param("email") String email);

    // Versión con paginación
    @Query("SELECT m FROM Matricula m JOIN m.alumno a WHERE a.email = :email")
    Page<Matricula> findByAlumnoEmail(@Param("email") String email, Pageable pageable);

    // Consulta para obtener matrículas con información completa
    @Query("SELECT m FROM Matricula m " +
            "JOIN FETCH m.alumno " +
            "JOIN FETCH m.curso " +
            "WHERE m.alumno.email = :email")
    List<Matricula> findByAlumnoEmailWithDetails(@Param("email") String email);

    // Consulta para contar matrículas por curso
    @Query("SELECT c.nombre, COUNT(m) FROM Matricula m " +
            "JOIN m.curso c " +
            "GROUP BY c.id, c.nombre")
    List<Object[]> countMatriculasByCurso();
}
