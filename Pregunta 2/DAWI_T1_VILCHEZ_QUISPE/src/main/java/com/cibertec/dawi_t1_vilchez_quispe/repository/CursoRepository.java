package com.cibertec.dawi_t1_vilchez_quispe.repository;

import com.cibertec.dawi_t1_vilchez_quispe.entity.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Consulta JPQL para buscar cursos por nombre parcial
    @Query("SELECT c FROM Curso c WHERE c.nombre LIKE %:nombre%")
    List<Curso> findByNombreParcial(@Param("nombre") String nombre);

    // Versión con paginación
    @Query("SELECT c FROM Curso c WHERE c.nombre LIKE %:nombre%")
    Page<Curso> findByNombreParcial(@Param("nombre") String nombre, Pageable pageable);

    // Consulta para buscar por créditos
    @Query("SELECT c FROM Curso c WHERE c.creditos >= :minCreditos")
    Page<Curso> findByCreditos(@Param("minCreditos") int minCreditos, Pageable pageable);
}
