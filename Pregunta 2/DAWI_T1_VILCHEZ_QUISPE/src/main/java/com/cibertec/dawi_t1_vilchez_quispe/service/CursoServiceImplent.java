package com.cibertec.dawi_t1_vilchez_quispe.service;

import com.cibertec.dawi_t1_vilchez_quispe.entity.Curso;
import com.cibertec.dawi_t1_vilchez_quispe.repository.CursoRepository;
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
public class CursoServiceImplent implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> buscarCursosPorNombre(String nombre) {
        return cursoRepository.findByNombreParcial(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Curso> buscarCursosPorNombrePaginado(String nombre, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return cursoRepository.findByNombreParcial(nombre, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Curso> obtenerCursosPaginados(int page, int size) {
        // Ordenamiento múltiple: por créditos descendente, luego por nombre ascendente
        Sort sort = Sort.by(
                Sort.Order.desc("creditos"),
                Sort.Order.asc("nombre")
        );

        Pageable pageable = PageRequest.of(page, size, sort);
        return cursoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Curso> buscarCursosPorCreditos(int minCreditos, int page, int size) {
        Sort sort = Sort.by("creditos").descending()
                .and(Sort.by("nombre").ascending());

        Pageable pageable = PageRequest.of(page, size, sort);
        return cursoRepository.findByCreditos(minCreditos, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeCursoPorNombre(String nombre) {
        return !cursoRepository.findByNombreParcial(nombre).isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public long contarCursosPorCreditos(int creditos) {
        return cursoRepository.findByCreditos(creditos, Pageable.unpaged()).getTotalElements();
    }
}