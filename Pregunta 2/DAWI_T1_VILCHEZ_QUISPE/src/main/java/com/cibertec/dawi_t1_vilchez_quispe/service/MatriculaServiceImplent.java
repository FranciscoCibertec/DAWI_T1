package com.cibertec.dawi_t1_vilchez_quispe.service;

import com.cibertec.dawi_t1_vilchez_quispe.entity.Alumno;
import com.cibertec.dawi_t1_vilchez_quispe.entity.Curso;
import com.cibertec.dawi_t1_vilchez_quispe.entity.Matricula;
import com.cibertec.dawi_t1_vilchez_quispe.repository.AlumnoRepository;
import com.cibertec.dawi_t1_vilchez_quispe.repository.CursoRepository;
import com.cibertec.dawi_t1_vilchez_quispe.repository.MatriculaRepository;
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
public class MatriculaServiceImplent implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public Matricula guardarMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Matricula> obtenerMatriculaPorId(Long id) {
        return matriculaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Matricula> obtenerTodasLasMatriculas() {
        return matriculaRepository.findAll();
    }

    @Override
    public void eliminarMatricula(Long id) {
        matriculaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Matricula> buscarMatriculasPorEmail(String email) {
        return matriculaRepository.findByAlumnoEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Matricula> buscarMatriculasPorEmailPaginado(String email, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return matriculaRepository.findByAlumnoEmail(email, pageable);
    }

    @Override
    public Matricula matricularAlumno(Long alumnoId, Long cursoId) {
        Optional<Alumno> alumno = alumnoRepository.findById(alumnoId);
        Optional<Curso> curso = cursoRepository.findById(cursoId);

        if (alumno.isPresent() && curso.isPresent()) {
            Matricula matricula = new Matricula(alumno.get(), curso.get());
            return matriculaRepository.save(matricula);
        }
        throw new RuntimeException("Alumno o Curso no encontrado");
    }

    @Override
    @Transactional(readOnly = true)
    public boolean estaMatriculado(Long alumnoId, Long cursoId) {
        // Implementar lógica para verificar si ya está matriculado
        return matriculaRepository.findAll().stream()
                .anyMatch(m -> m.getAlumno().getId().equals(alumnoId) &&
                        m.getCurso().getId().equals(cursoId));
    }

    @Override
    @Transactional(readOnly = true)
    public long contarMatriculasPorCurso(Long cursoId) {
        return matriculaRepository.findAll().stream()
                .filter(m -> m.getCurso().getId().equals(cursoId))
                .count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> obtenerEstadisticasMatriculas() {
        return matriculaRepository.countMatriculasByCurso();
    }

    @Override
    public Page<Matricula> obtenerTodasLasMatriculasPaginadas( int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return matriculaRepository.findAll( pageable);
    }
}
