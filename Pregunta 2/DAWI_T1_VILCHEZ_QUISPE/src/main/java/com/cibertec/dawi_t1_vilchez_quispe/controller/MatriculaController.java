package com.cibertec.dawi_t1_vilchez_quispe.controller;

import com.cibertec.dawi_t1_vilchez_quispe.entity.Curso;
import com.cibertec.dawi_t1_vilchez_quispe.entity.Matricula;
import com.cibertec.dawi_t1_vilchez_quispe.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    // Buscar Matriculas Todas
    @GetMapping("/Matricula/lista")
    public ResponseEntity<List<Matricula>> ListarMatriculas() {

        List<Matricula> Matriculas = matriculaService.obtenerTodasLasMatriculas();
        return ResponseEntity.ok(Matriculas);
    }
    // Buscar MAtriculas PAginadas
    @GetMapping("/Matricula/listapaginada")
    public ResponseEntity<Page<Matricula>> ListarMatriculasPaginadas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Page<Matricula> Matricula = matriculaService.obtenerTodasLasMatriculasPaginadas(
                 page, size, sortBy, sortDir);
        return ResponseEntity.ok(Matricula);
    }

    // Buscar matrículas por Id
    @GetMapping("/matriculas/porId")
    public ResponseEntity<Optional<Matricula>> BuscarMatriculaPorId(
            @RequestParam Long id) {
        Optional<Matricula> matriculas = matriculaService.obtenerMatriculaPorId(id);

        return ResponseEntity.ok(matriculas);
    }
    // Buscar matrículas por email con paginación
    @GetMapping("/matriculas/por-email")
    public ResponseEntity<List<Matricula>> buscarMatriculasPorEmail(
            @RequestParam String email) {

        List<Matricula> matriculas = matriculaService.buscarMatriculasPorEmail(email);
        return ResponseEntity.ok(matriculas);
    }

}
