package com.cibertec.dawi_t1_vilchez_quispe.service;

import com.cibertec.dawi_t1_vilchez_quispe.entity.Alumno;
import com.cibertec.dawi_t1_vilchez_quispe.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlumnoServiceImplent implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Alumno guardarAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alumno> obtenerAlumnoPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> obtenerTodosLosAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public void eliminarAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alumno> buscarAlumnoPorEmail(String email) {
        return alumnoRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Alumno> obtenerAlumnosPaginados(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return alumnoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeAlumnoPorEmail(String email) {
        return alumnoRepository.findByEmail(email).isPresent();
    }
}
