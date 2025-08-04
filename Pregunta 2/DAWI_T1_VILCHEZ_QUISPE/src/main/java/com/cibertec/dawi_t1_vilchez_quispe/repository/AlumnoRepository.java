package com.cibertec.dawi_t1_vilchez_quispe.repository;

import com.cibertec.dawi_t1_vilchez_quispe.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Optional<Alumno> findByEmail(String email);
}
